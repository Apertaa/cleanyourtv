# L'accueil voulu par Sébastien (questionnaire du 06/11/2026)

Réponses données vers 1 h 30, après l'essai de dock-glass, de glass et de cinematic sur la Shield.

## Ce qu'il veut

| Sujet | Choix | Réglage |
|---|---|---|
| Base | **glass**, ajusté ; cinematic essayé puis écarté | `perso/themes/glass-shield/` |
| Dock | 5 tuiles, puis des rangées de 5 | `columns` 5, `fill_dock` |
| Rangées visibles sous le dock | 0 : place à l'image du haut | `rows_below_dock` 0 |
| Fond | celui de glass, avec l'image de l'appli sélectionnée en haut (force moyenne) | `cinematic_home`, `cinematic_art` 50 |
| Couleurs | halo derrière le dock, descendant du haut ; ciel qui suit l'heure (Rennes) | `ambient_glow`, `glow_style` horizon, `sky_intensity` 60 |
| Sélection | dalle de verre, liseré à la couleur de l'appli | `glass_style` glass, `app_glow` |
| Noms | le grand nom au-dessus du dock seulement | `dock_title` |
| En haut à gauche | heure, date, ligne de stats ; ni salutation ni prénom | `show_greeting` false, `show_stats` |
| En haut à droite | une roue crantée qui ouvre le panneau rapide (Paramètres, Affichage, Réseau, Applis, réglages du lanceur) | `settings_button_panel` (code du fork), `icon_only_buttons` |
| Bibliothèque d'applis | oui, bouton rond en haut | `app_library`, `library_button`, `show_library_pill` false |
| Mouvement | ressort et effets avancés | `focus_overshoot`, `advanced_effects` |
| Démarrage | l'accueil | rien |
| Bouton NETFLIX | Nuvio ; **appui long : YouTube+** | `app_keys` (config.xml) |
| Priorité | beau et vivant | |
| Code | sur mesure : on peut modifier le lanceur, en notant l'écart avec l'amont | |

## Add-ons retenus

Officiels, à lire avant d'installer : **Météo** (ville réglée sur la Shield seulement, jamais dans le fork),
**Minuteur de veille**, **Inspecteur TV**, **Statistiques** (Capacity Stats), **Accès à distance**, **Statut AirPlay**.
Aucun add-on de la communauté. Installés le 06/11 vers 1 h 35, sauf Statut AirPlay (pas de récepteur AirPlay sur la
Shield) ; vers 1 h 40, Sébastien n'a gardé que le **Minuteur de veille**.

## Le voile d'assombrissement (construit le 06/11)

Réglé dans Paramètres du lanceur ; fonctionnement et limites dans `README.md`. Il remplace l'écran de veille Aerial Views, sur le modèle de celui de SmartTube.

- Après un délai sans appui, réglable par pas de 5 s, un voile noir couvre l'écran à 95 % (opacité réglable).
- Il agit partout tant qu'aucune vidéo ne joue : l'accueil, Spotify en musique, une vidéo en pause dans Nuvio.
- L'appli reste ouverte derrière. Aujourd'hui, quand Aerial Views se lance pendant une pause de Nuvio,
  le film repart du début à la sortie.
- Au premier appui, le voile disparaît et la touche agit aussitôt : Play relance la lecture.
