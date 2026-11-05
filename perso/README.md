# perso — la Shield de Sébastien

Ce dossier n'existe que dans le fork `Apertaa/cleanyourtv` : ce qui est propre à la Shield TV Pro 2019
de Sébastien. Le reste du dépôt suit le projet d'origine (`upstream` = `Gathin23/cleanyourtv`).

## Où on en est (05/11/2026, appliqué)

- Choix : le lanceur CleanYourTV (style Apple TV, code ouvert, à personnaliser plus tard) et son allègement,
  plutôt que Projectivy et ATV Optimizer. Audit préalable : `../../pilotage/kb/mesures/shield-audit-2026-11-05.md`.
- Lanceur v3.3 installé et Accueil (`com.gathin.tvhome`), accueil d'origine désactivé ; dock : YouTube+,
  Nuvio GitHub (`com.nuvio.tv`, choix de Sébastien), Canal+, Spotify (`launcher/.../values/config.xml`).
- Liste `shield-pro-2019/debloat.txt` appliquée (20 paquets) ; annulation : `tools/restore.sh` sur la même liste.
- Désinstallées : Molotov, Netflix, YouTube officiel, YouTube Music, Kodi, Plex Media Server, Downloader,
  Google Play Films, Google Play Jeux. Supprimés : la vidéo AirScreen (1,44 Go), un vieil APK de SmartTube.
- Réglages : animations à 0,5×, DNS privé `dns.adguard-dns.com`, écran de veille Aerial Views (Play Store),
  caches vidés. Débogage réseau laissé ouvert (pilotage s'en sert).
- Vérifié après redémarrage : Accueil, 22 paquets désactivés, sortie son forcée vers le DAC, services audio et
  affichage NVIDIA actifs, DNS (AIOStreams, TorBox, Stremio, TMDB, Trakt résolus ; pubs bloquées).

## Essai de Projectivy (05/11/2026)

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
  chaînes TV et stockage étaient déjà accordés. **Projectivy est l'Accueil depuis le 05/11/2026 au soir** (essai) ; retour :
  `cmd package set-home-activity com.gathin.tvhome/.MainActivity`.

- Revue de tous les réglages de Projectivy, avec conseils : `projectivy-reglages.md`. Elle est tirée de l'APK
  décompilé avec l'accord de Sébastien, et les écrans ne sont pas encore parcourus.

## Ce qui reste

1. Sébastien : essayer l'appli téléphone SHIELD TV, une lecture Nuvio (TorBox) en Dolby Vision, le micro.
2. Remesurer la mémoire après quelques jours (`dumpsys meminfo`) : juste après un redémarrage, les services
   Google Play gonflent et la comparaison avec l'audit ne vaut rien.
3. Après une mise à jour SHIELD : `tools/check.py perso/shield-pro-2019/debloat.txt`, puis réappliquer.
4. Rien n'est commité dans le fork (`perso/`, `config.xml`).

Les scripts passent par le serveur adb de Claude : `TV_ADB_BINARY=/opt/homebrew/bin/adb
ANDROID_ADB_SERVER_PORT=5038 TV_HOST=<ip de la Shield>:5555`, après `adb -P 5038 server nodaemon` (règle du démon
adb : `multimedia/CLAUDE.md`). Sans ces variables, `tools/tv.py` prend sa propre clé et la Shield redemande
« Autoriser le débogage ? ».
