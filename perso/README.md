# perso — la Shield de Sébastien

Ce dossier n'existe que dans le fork `Apertaa/cleanyourtv` : ce qui est propre à la Shield TV Pro 2019
de Sébastien. Le reste du dépôt suit le projet d'origine (`upstream` = `Gathin23/cleanyourtv`).

## Où on en est (06/11/2026, appliqué)

- **Accueil : le lanceur CleanYourTV v3.3** (`com.gathin.tvhome`), dock YouTube+, Nuvio GitHub `com.nuvio.tv`,
  Canal+, Spotify (`launcher/.../values/config.xml`). Depuis le 06/11 il porte le thème **glass**
  (essayé après dock-glass) : copie dans `themes/glass-shield/`, seul le dock change (ses 4 applis). Poussé par
  `tools/theme.sh perso/themes/glass-shield` ; retour au réglage d'origine : `tools/theme.sh --reset`. Chantier de l'accueil : `STATUS.md` ; tous les réglages du lanceur : `lanceur-reglages.md`. Ce dépôt sert aussi pour l'**allègement**, plutôt
  qu'ATV Optimizer. Audit préalable : `../../pilotage/kb/mesures/shield-audit-2026-11-05.md`.
- Historique : lanceur CleanYourTV le 05/11 de 15 h à 20 h, Projectivy du 05/11 au soir au 06/11 (section
  suivante), puis retour au lanceur CleanYourTV le 06/11 vers 0 h 30 et **Projectivy désinstallé** : Sébastien le
  trouve trop complet pour ce qu'il apporte, et veut une interface sur mesure, codée dans ce fork.
  Réinstallation : `STOCK_LAUNCHER=none HOME_KEY_SERVICE=1` + les variables du serveur adb 5038 (bas de page)
  `bash launcher/build-and-install.sh` (`SKIP_BUILD=1` pour reprendre l'APK déjà compilé ; le script refuse sans
  `STOCK_LAUNCHER` quand l'Accueil est un lanceur qu'il ne connaît pas). **Toujours `HOME_KEY_SERVICE=1`** :
  Android coupe le service des touches à chaque réinstallation, et le bouton NETFLIX redeviendrait « Netflix ».
- Depuis le 06/11 vers 0 h 50, le lanceur est compilé avec, dans `config.xml` : le ciel qui suit l'heure
  (`sky_intensity` 60) et la position du centre de Rennes (choisie plutôt que la commune exacte, le fork étant
  public ; le soleil y diffère de moins d'une minute), et le bouton **NETFLIX → Nuvio** (`app_keys`
  `KEYCODE_BUTTON_12:com.nuvio.tv`). Le service des touches (`HomeKeyService`) est activé et lié ; essai au vrai
  bouton par Sébastien : Nuvio s'ouvre. Ces réglages sont dans `config.xml` et pas dans le thème, pour qu'une
  réinstallation garde les choix faits sur la TV (un envoi de thème les efface).
- Boutons de la télécommande (relevés le 06/11, `getevent`, fichier `Vendor_0955_Product_7217.kl`) : Menu `MENU`,
  croix `DPAD_*`, OK `DPAD_CENTER`, Retour `BACK`, Accueil `HOME`, lecture `MEDIA_FAST_FORWARD`/`PLAY_PAUSE`/
  `REWIND`, volume `VOLUME_DOWN`/`UP`, NETFLIX `BUTTON_12` (scan 393), micro `ASSIST` (scan 217), marche `POWER`.
- L'accueil d'origine reste désactivé. Seul lanceur : celui de CleanYourTV ; sans lui, Android affiche
  `FallbackHome` (écran vide) et adb reste joignable.
- Liste `shield-pro-2019/debloat.txt` appliquée (20 paquets) ; annulation : `tools/restore.sh` sur la même liste.
- Désinstallées : Molotov, Netflix, YouTube officiel, YouTube Music, Kodi, Plex Media Server, Downloader,
  Google Play Films, Google Play Jeux. Supprimés : la vidéo AirScreen (1,44 Go), un vieil APK de SmartTube.
- Réglages : animations remises à 1× le 05/11 au soir (à 0,5×, elles accéléraient aussi Nuvio et les autres applis), DNS privé `dns.adguard-dns.com`, écran de veille Aerial Views (Play Store),
  caches vidés. Débogage réseau laissé ouvert (pilotage s'en sert).
- Vérifié après redémarrage : Accueil, 22 paquets désactivés, sortie son forcée vers le DAC, services audio et
  affichage NVIDIA actifs, DNS (AIOStreams, TorBox, Stremio, TMDB, Trakt résolus ; pubs bloquées).

## Projectivy (Accueil du 05/11 au soir au 06/11/2026, désinstallé)

- Lanceur CleanYourTV jugé « vraiment pas mal » ; Sébastien veut comparer avec Projectivy.
- Installé depuis GitHub (choix de Sébastien) : `spocky/miproja1`, v4.73 `xda-release` (Play Store : 4.72),
  signée « Despesse Mickael », SHA-256 du certificat `f6697bf4…b0aadd`. Se met à jour seul depuis ce dépôt.
  Demande INTERNET et l'identifiant publicitaire (AD_ID), contrairement à CleanYourTV.
- L'installation d'un 2ᵉ lanceur fait perdre l'Accueil par défaut (sélecteur à la touche Accueil) :
  `cmd package set-home-activity com.gathin.tvhome/.MainActivity` l'a remis. Pour essayer Projectivy en
  Accueil : `cmd package set-home-activity com.spocky.projengmenu/.ui.home.MainActivity` (relevée sur la Shield).
  Service d'accessibilité de Projectivy activé par Sébastien (Paramètres de la Shield, 05/11 vers 20 h 17),
  lié et actif d'après `dumpsys accessibility`. Le bouton de Projectivy qui ouvre cette page tombe sur une page
  vide de la Shield (`ACCESSIBILITY_SETTINGS` → `frameworkpackagestubs`), puis sur les Paramètres principaux :
  passer par Paramètres › Préférences de l'appareil › Accessibilité. À revérifier après un redémarrage (des
  Shield en 9.2 perdent ce réglage). Droits accordés par adb avec l'accord de Sébastien (05/11) : accès aux
  notifications (`cmd notification allow_listener com.spocky.projengmenu/com.spocky.projengmenu.services.notification.NotificationListener`,
  à côté de celui de tvrecommendations) et installation de ses mises à jour (`appops set … REQUEST_INSTALL_PACKAGES allow`) ;
  chaînes TV et stockage étaient déjà accordés. **Projectivy est l'Accueil depuis le 05/11/2026 au soir.**

- Réglages appliqués par Claude le 05/11/2026 au soir, menu par menu : remplacement du launcher désactivé,
  remappage des boutons et rappel d'accessibilité désactivés, horloge effacée en mode inactif, date en
  ligne 2 (`EEEE d MMMM`), largeur minimale des fonds à 1900 px (curseur par pas de 100 : 1900 laisse passer
  1920), vidéos de prévisualisation « Jamais ». Laissés tels que Sébastien les a choisis : fonds Wikimedia
  Commons, transition Zoom, détection d'inactivité (1 min → économiseur, sauf pendant une lecture). Les deux
  raccourcis du menu Télécommande (double « Menu », double « Retour ») sont restés coupés : ils passent par le
  remappage des boutons.
- Mesuré le 06/11 vers 0 h 15 (Shield allumée depuis 8 h) : à l'accueil, 105 à 110 Mo (PSS), 85 Mo en arrière-plan
  (le lanceur CleanYourTV : 146 Mo juste après un redémarrage, conditions pas identiques). Au repos à
  l'accueil, Projectivy redessine en continu (313 images en 10 s) et prend environ 16 % d'un cœur : cause
  probable, l'animation de la carte sélectionnée, « Pulsation » par défaut (`appearance_cards_focused_animation`),
  pas encore vérifiée.
- Revue de tous les réglages de Projectivy, avec conseils : `projectivy-reglages.md`. Elle est tirée de l'APK
  décompilé avec l'accord de Sébastien, et les écrans ne sont pas encore parcourus.

## Ce qui reste

1. Sébastien : essayer l'appli téléphone SHIELD TV, une lecture Nuvio (TorBox) en Dolby Vision, le micro.
2. Remesurer la mémoire après quelques jours (`dumpsys meminfo`) : juste après un redémarrage, les services
   Google Play gonflent et la comparaison avec l'audit ne vaut rien.
3. Après une mise à jour SHIELD : `tools/check.py perso/shield-pro-2019/debloat.txt`, puis réappliquer.
4. Le fork vit sur la branche `shield` (`main` reste une copie du projet d'origine) ; dépôt public : aucune IP ni identifiant dans `perso/`.

Les scripts passent par le serveur adb de Claude : `TV_ADB_BINARY=/opt/homebrew/bin/adb
ANDROID_ADB_SERVER_PORT=5038 TV_HOST=<ip de la Shield>:5555`, après `adb -P 5038 server nodaemon` (règle du démon
adb : `multimedia/CLAUDE.md`). Sans ces variables, `tools/tv.py` prend sa propre clé et la Shield redemande
« Autoriser le débogage ? ».
