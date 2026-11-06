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

## Ce qui reste

1. Étape 2 : lire puis installer les add-ons Météo (ville sur la Shield seulement), Minuteur de veille, Inspecteur TV,
   Statistiques, Accès à distance (son bouton couperait le débogage réseau dont pilotage se sert), Statut AirPlay.
2. Étape 3 : coder le voile d'assombrissement (`accueil-cible.md`), puis désactiver Aerial Views.
3. Juger le ciel de jour et au coucher du soleil.
