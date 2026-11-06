# Chantier : l'accueil de la Shield

But : un accueil sur mesure, proche de l'Apple TV, sans le superflu de Projectivy.

## Où on en est (06/11/2026)

- Décision de Sébastien : pas de code pour l'instant, on garde les réglages par défaut du lanceur
  et on prend le thème **dock-glass**, le plus proche de l'Apple TV à ses yeux.
- Essayé d'abord : `themes/dock-glass-shield/` (rangée unique de 6 tuiles sur un bandeau de verre ;
  les 2 places libres prenaient Aerial Views et Apple TV).
- **Appliqué ensuite, à la demande de Sébastien : `themes/glass-shield/`**, le thème `glass` avec son dock
  (YouTube+, Nuvio, Canal+, Spotify) et le fond bleu du thème. Mode `cinematic_home` : grand titre de l'appli,
  points de pagination, dock de 4 grandes tuiles sur verre, les autres applis en dessous.
- Repère tvOS : la disposition de tvOS 26-27 bouge peu. Le Liquid Glass est apparu en tvOS 26 (icônes à
  reflets, bandeau du haut translucide) ; ce qui manque ici, c'est la grande image du haut qui change selon
  l'appli sélectionnée (le « Top Shelf »).
- 06/11 vers 0 h 50 : ciel qui suit l'heure (60 %), position de Rennes, bouton NETFLIX → Nuvio, service des
  touches activé (détail dans `README.md`). Sébastien a aussi changé quelques réglages sur la TV.
- 06/11 vers 1 h 30 : essai du thème cinematic (avec son dock), écarté. Questionnaire complet :
  `accueil-cible.md`. **Étape 1 appliquée** vers 1 h 30 : `themes/glass-shield/` réécrit selon les réponses (5 colonnes,
  0 rangée sous le dock, halo, ciel, stats, roue = réglages du lanceur, bibliothèque, ressort), et appui long
  NETFLIX → YouTube+ (`config.xml`). Sébastien a testé : tout fonctionne. Capture de l'accueil à refaire (il était
  dans YouTube+).
- **Étape 2 faite** vers 1 h 35 : add-ons Météo, Minuteur de veille, Inspecteur TV, Statistiques et Accès à distance
  installés, cartes acceptées et ville de Météo réglée par Sébastien sur la TV (jamais dans le fork). Statut AirPlay
  écarté : la Shield n'a pas de récepteur AirPlay. Accès à distance installé **sans** `WRITE_SECURE_SETTINGS`, pour
  que son bouton ne puisse pas couper le débogage réseau dont pilotage se sert.
- Vers 1 h 40, après les avoir regardés, Sébastien garde **seulement le Minuteur de veille** : Statistiques, Météo,
  Accès à distance et Inspecteur TV désinstallés.
- Vers 1 h 50 : la molette en haut à droite ouvre le panneau rapide (nouveau réglage `settings_button_panel`, premier
  changement de code du fork). Installé ; Sébastien l'a ouvert aussitôt.
  Vers 2 h : lignes du panneau en français ; la ligne Affichage est retirée, la Shield n'ayant aucune entrée directe
  vers « Affichage et audio » (choix de Sébastien).

- **Étape 3 faite** vers 2 h 30 : le voile d'assombrissement (code du fork, `README.md`), installé avec l'accord de
  Sébastien ; Aerial Views coupé, mise en veille de la Shield gardée à 20 min. Essayé par Sébastien : tout marche
  (accueil et Spotify voilés, Nuvio en pause voilé sans repartir, pas de voile pendant une vidéo).

## Ce qui reste

1. Juger le ciel de jour et au coucher du soleil.
2. Régler délai et opacité du voile à l'usage, sur la TV (Paramètres du lanceur).
