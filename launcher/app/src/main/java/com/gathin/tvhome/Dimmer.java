package com.gathin.tvhome;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

/**
 * Fork (Sébastien's Shield): a black veil over whatever is on screen once the remote has been idle for a while,
 * modelled on SmartTube's screen dimming but for every app, drawn by HomeKeyService as an accessibility overlay (no
 * overlay permission). The app behind keeps running, so a paused film is not reloaded the way it is when the system
 * screensaver takes over.
 *
 * <ul>
 * <li>Never while a video plays: a started player whose audio says CONTENT_TYPE_MOVIE (Nuvio, SmartTube and Canal+
 *     all do, measured on the Shield). Music (Spotify says CONTENT_TYPE_UNKNOWN), a paused film, menus and Home are
 *     veiled. A video that starts under the veil lifts it; one that stops starts the delay again.</li>
 * <li>The first key lifts it and still reaches the app, so Play resumes at once.</li>
 * <li>No polling: one timer per idle period, plus Android's playback and screen on/off callbacks.</li>
 * <li>Settings live in their own preferences ("dimmer"), set from Launcher settings, so a theme push keeps them:
 *     on/off, delay in 5-second steps, opacity. Defaults come from config.xml (dimmer_enabled, dimmer_delay_seconds,
 *     dimmer_opacity).</li>
 * </ul>
 */
final class Dimmer implements SharedPreferences.OnSharedPreferenceChangeListener {
    static final String PREFS = "dimmer";
    static final String KEY_ENABLED = "enabled";
    static final String KEY_DELAY = "delay_seconds";
    static final String KEY_OPACITY = "opacity";
    static final int DELAY_STEP = 5;
    static final int DELAY_MIN = 5;
    static final int DELAY_MAX = 30 * 60;
    static final int OPACITY_STEP = 5;
    static final int OPACITY_MIN = 50;
    static final int OPACITY_MAX = 100;

    private static final long FADE_IN_MS = 900;

    private final AccessibilityService service;
    private final SharedPreferences prefs;
    private final AudioManager audio;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable dim = this::dim;
    private final AudioManager.AudioPlaybackCallback playback = new AudioManager.AudioPlaybackCallback() {
        @Override
        public void onPlaybackConfigChanged(List<AudioPlaybackConfiguration> players) {
            boolean now = videoPlaying(players);
            if (now == video) return;
            video = now;
            restart();
        }
    };
    private final BroadcastReceiver screen = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            screenOn = Intent.ACTION_SCREEN_ON.equals(intent.getAction());
            restart();
        }
    };
    private boolean enabled;
    private long delayMs;
    private int opacity;
    private boolean video;
    private boolean screenOn = true;
    private View veil;

    Dimmer(AccessibilityService service) {
        this.service = service;
        prefs = prefs(service);
        audio = service.getSystemService(AudioManager.class);
        PowerManager power = service.getSystemService(PowerManager.class);
        if (power != null) screenOn = power.isInteractive();
        if (audio != null) {
            video = videoPlaying(audio.getActivePlaybackConfigurations());
            audio.registerAudioPlaybackCallback(playback, handler);
        }
        IntentFilter screenChanges = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        screenChanges.addAction(Intent.ACTION_SCREEN_ON);
        service.registerReceiver(screen, screenChanges);
        prefs.registerOnSharedPreferenceChangeListener(this);
        reload();
    }

    static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    static boolean enabled(Context context) {
        return prefs(context).getBoolean(KEY_ENABLED, context.getResources().getBoolean(R.bool.dimmer_enabled));
    }

    static int delaySeconds(Context context) {
        return clampDelay(prefs(context).getInt(KEY_DELAY, context.getResources().getInteger(R.integer.dimmer_delay_seconds)));
    }

    static int opacity(Context context) {
        return clampOpacity(prefs(context).getInt(KEY_OPACITY, context.getResources().getInteger(R.integer.dimmer_opacity)));
    }

    static int clampDelay(int seconds) {
        int stepped = Math.round(seconds / (float) DELAY_STEP) * DELAY_STEP;
        return Math.max(DELAY_MIN, Math.min(DELAY_MAX, stepped));
    }

    static int clampOpacity(int percent) {
        int stepped = Math.round(percent / (float) OPACITY_STEP) * OPACITY_STEP;
        return Math.max(OPACITY_MIN, Math.min(OPACITY_MAX, stepped));
    }

    /** "45 s", "1 min", "2 min 05 s". */
    static String formatDelay(int seconds) {
        int minutes = seconds / 60;
        int rest = seconds % 60;
        if (minutes == 0) return rest + " s";
        if (rest == 0) return minutes + " min";
        return minutes + " min " + (rest < 10 ? "0" : "") + rest + " s";
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences changed, String key) {
        reload();
    }

    private void reload() {
        enabled = enabled(service);
        delayMs = delaySeconds(service) * 1000L;
        opacity = opacity(service);
        restart();
    }

    /** Every key from HomeKeyService: lifts the veil and starts the delay again. The key is never consumed. */
    void onKey(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) restart();
    }

    void stop() {
        handler.removeCallbacks(dim);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        if (audio != null) audio.unregisterAudioPlaybackCallback(playback);
        try {
            service.unregisterReceiver(screen);
        } catch (RuntimeException alreadyGone) {
            // Unregistered with the service.
        }
        hide();
    }

    private void restart() {
        hide();
        handler.removeCallbacks(dim);
        if (enabled && screenOn && !video) handler.postDelayed(dim, delayMs);
    }

    private void dim() {
        if (enabled && screenOn && !video && veil == null) show();
    }

    /**
     * A player that is playing a film. Apps cannot read a player's state directly (getPlayerState is a system API),
     * but AudioPlaybackConfiguration.toString() includes it ("state:started"); where it does not, a film player
     * counts while any media is audible.
     */
    private boolean videoPlaying(List<AudioPlaybackConfiguration> players) {
        if (players == null) return false;
        for (AudioPlaybackConfiguration player : players) {
            AudioAttributes attributes = player.getAudioAttributes();
            if (attributes == null || attributes.getContentType() != AudioAttributes.CONTENT_TYPE_MOVIE) continue;
            String described = player.toString();
            if (described.contains("state:")) {
                if (described.contains("state:started")) return true;
            } else if (audio != null && audio.isMusicActive()) {
                return true;
            }
        }
        return false;
    }

    private void show() {
        WindowManager windows = service.getSystemService(WindowManager.class);
        if (windows == null) return;
        View view = new View(service);
        view.setBackgroundColor(Color.argb(Math.round(opacity * 255 / 100f), 0, 0, 0));
        view.setAlpha(0f);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        try {
            windows.addView(view, params);
        } catch (RuntimeException unavailable) {
            return;
        }
        veil = view;
        view.animate().alpha(1f).setDuration(FADE_IN_MS).start();
    }

    private void hide() {
        if (veil == null) return;
        View view = veil;
        veil = null;
        view.animate().cancel();
        WindowManager windows = service.getSystemService(WindowManager.class);
        try {
            if (windows != null) windows.removeView(view);
        } catch (RuntimeException alreadyGone) {
            // The window went with the service.
        }
    }
}
