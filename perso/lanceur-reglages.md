# Les réglages du lanceur CleanYourTV, au-delà des thèmes

Relevé le 06/11/2026 dans le dépôt (lanceur v3.3) : `docs/launcher.md`, le tableau des clés de
`themes/README.md` et `launcher/.../values/config.xml`. Rien n'a été essayé sur la Shield sauf mention.
Les noms de rangées à l'écran sont traduits de l'anglais : si l'écran dit autre chose, c'est l'écran qui a raison.

Il y a quatre façons de régler le lanceur, de la plus simple à la plus lourde :

1. **Sur la TV**, dans les réglages du lanceur : pas d'ordinateur.
2. **Par un thème** : un `config.json` poussé par `tools/theme.sh`. Toutes les clés ci-dessous s'y mettent,
   sauf celles marquées *config.xml*. C'est ce qu'on fait avec `perso/themes/glass-shield/`.
3. **Dans `config.xml`**, puis recompiler et réinstaller : quelques réglages seulement.
4. **À l'installation**, par des variables du script `launcher/build-and-install.sh`.

Légende : ★ intéressant pour toi · — sans objet sur la Shield (pas d'entrée HDMI ni de tuner) ·
*(glass)* valeur que le thème glass met déjà.

## 1. Sur la TV

Appui long sur OK sur le bouton Paramètres (la roue en haut à droite) :

- **Paramètres du lanceur** (détail ci-dessous).
- **Cartes modules** : les petites applis qui ajoutent une tuile (§ 7).
- **Modifier le dock** : choisir ses applis à la télécommande.
- **Réorganiser les applis** : OK prend une appli, les flèches la déplacent, Retour la pose.
- **Redémarrer…**, seulement si le service d'accessibilité `HomeKeyService` tourne.

Appui long sur OK sur une tuile : **Déplacer**, **Retirer de l'accueil** (avec la bibliothèque d'applis) et
**Infos sur l'appli**.

Les **paramètres du lanceur** eux-mêmes :

| Rangée | Choix |
|---|---|
| Applis par rangée | 4, 5, 6 ou 7 |
| Rangées sous le dock | 0 à 3 |
| Opacité du bandeau du dock | 100, 70, 50 ou 30 % |
| Horloge | Système, 12 h ou 24 h |
| Fond d'écran | une image du thème, Aucun, ou Celui du thème |
| Animations / Effets avancés | marche ou arrêt |
| Ouvrir au démarrage | seulement avec `open_on_start_choice` |
| Suivre le thème | efface tous les choix faits ici ; repousser un thème le fait aussi |
| Choisir l'accueil par défaut / Paramètres système | |

Ces choix passent avant le thème, jusqu'à « Suivre le thème » ou au prochain envoi de thème.

## 2. Par un thème (`config.json`)

### Disposition
| Clé | Ce qu'elle fait |
|---|---|
| `dock_apps` | Les applis du dock, dans l'ordre *(glass : tes 4)* |
| `fill_dock` | Remplit les places libres du dock avec les premières applis (oui d'origine) |
| `hidden_apps` | Applis jamais affichées en tuile ★ (cacher ce qui ne sert pas) |
| `columns` | 1 à 8 tuiles par rangée |
| `rows_below_dock` | 0 à 3 rangées visibles sous le dock |
| `dock_row` | Le dock comme rangée à part, avec son bandeau (oui d'origine) |
| `single_row` | Toutes les applis sur une seule rangée qui défile, sans grille dessous (dock-glass) |
| `dock_band_all` | Avec `single_row`, le bandeau court derrière toute la rangée |
| `dock_title` | Le nom de l'appli sélectionnée en grand au-dessus du dock |
| `always_show_names` | Le nom sous chaque tuile, pas seulement la sélectionnée |
| `uniform_tiles` | Toutes les tuiles au même modèle : l'icône sur une carte teintée, au lieu de la bannière de l'appli |
| `app_labels` | Renommer une tuile (`"paquet=Nom"`) |
| `app_banners` | Remplacer la vignette d'une appli par une image fournie avec le lanceur |
| `launch_actions` | Une tuile qui ouvre un écran précis de l'appli, ou une adresse (`paquet=view:uri`) |
| `icon_tiles` | *config.xml* : dessiner une appli avec son icône quand sa bannière est mal recadrée |

### Haut de l'écran
| Clé | Ce qu'elle fait |
|---|---|
| `show_greeting`, `greeting_name` | La salutation (« Bonne nuit ») et un prénom après ★ |
| `date_below_clock`, `header_white` | La date sous l'heure plutôt qu'à côté ; la date en blanc vif |
| `clock_format` | 12 h, 24 h ou celui du système |
| `icon_only_buttons` | Wi-Fi et Paramètres en boutons ronds sans texte *(glass)* |
| `show_network_pill` | Cacher le bouton Wi-Fi |
| `wifi_label_width` | Garder le nom du réseau à côté de l'icône, jusqu'à une largeur donnée |
| `wifi_status_color`, `wifi_status_warnings` | Icône Wi-Fi verte en ligne, orange sans Internet, rouge hors ligne ★ |
| `top_menu` | Entrées, Wi-Fi et Paramètres réunis dans une seule roue centrée |
| `launcher_settings_button` | La roue ouvre les réglages du lanceur plutôt que ceux de la Shield |
| `top_bar_apps` | Des applis en petits boutons ronds dans la barre du haut ★ |
| `reorder_button` | Un bouton « Réorganiser » dans la barre du haut |
| `show_stats` | Une ligne sous l'heure : processeur, mémoire libre, stockage libre |
| `show_inputs_pill`, `icon_only_inputs` | — Le bouton Entrées (la Shield n'en a pas, il ne s'affiche pas) |
| `shamsi_date`, `lunar_date`, `lunar_utc_offset` | — Calendriers persan et lunaire |
| `locale` | Forcer la langue du lanceur |

### Fond
| Clé | Ce qu'elle fait |
|---|---|
| `background_color` | La couleur de fond *(glass : bleu nuit `#090C14`)* |
| `wallpaper` | Afficher l'image du thème *(glass : oui)* |
| `wallpaper_rotation_minutes` | Faire tourner plusieurs images (dossier `wallpapers/`, jusqu'à 100) toutes les N minutes ★ |
| `wallpaper_order` | Au hasard ou dans l'ordre |
| `wallpaper_moods` | Des images pour le matin, le soir et la nuit, réglées sur le soleil |
| `wallpaper_captions` | Le nom de l'image sous les boutons |
| `wallpaper_dim` | Assombrir l'image de 0 à 90 % |
| `wallpaper_true_color` | Couleurs 32 bits : plus de bandes dans les dégradés sombres (2 Go de mémoire ou plus) ★ sur la G5 |
| `sky_intensity` | Un ciel sombre qui suit l'heure du jour, 0 à 100 |
| `dock_opacity` | Transparence du bandeau du dock, 30 à 100 % |
| `dock_glass`, `dock_band`, `dock_even_padding` | Bandeau en verre dépoli *(glass)*, sans bandeau, marges égales |
| `fade_rows` | Les rangées s'effacent en douceur en montant sous l'heure |

### Le mode « cinématique » (le plus proche de l'Apple TV)
| Clé | Ce qu'elle fait |
|---|---|
| `cinematic_home` | L'image de l'appli sélectionnée, adoucie, remplit le haut de l'écran *(glass)* |
| `cinematic_dots` | Les petits points de position sous le nom |
| `cinematic_white_focus` | Contour de sélection blanc plutôt qu'à la couleur de l'appli |
| `cinematic_scrim` | Force de l'ombre derrière le nom, 0 à 100 |
| `cinematic_art` | Opacité de l'image de l'appli, 0 à 100 (50 d'origine) ★ |

### Sélection, lueurs et mouvement
| Clé | Ce qu'elle fait |
|---|---|
| `glass_style` | Le cadre de sélection : `glass` (verre), `rim` (simple liseré) ou `halo` |
| `app_glow` | Le cadre prend la couleur de l'appli *(glass)* |
| `ambient_glow`, `glow_style`, `glow_snap` | Le halo de couleur derrière le dock : rond, ou `horizon` qui descend du haut *(glass)* |
| `focus_overshoot` | La sélection dépasse sa cible puis revient, façon ressort *(glass : non)* |
| `animations`, `advanced_effects` | Animations de base ; tuile soulevée, zoom de l'icône *(glass : effets avancés oui)* |
| `reduced_motion` | Mouvements courts et calmes, pour les TV lentes |

### Horloge plein écran (l'écran de veille du lanceur)
| Clé | Ce qu'elle fait |
|---|---|
| `home_idle_clock_minutes` | Afficher l'horloge après N minutes sans toucher à l'accueil |
| `back_opens_clock` | Retour sur l'accueil ouvre l'horloge, comme Menu sur une Apple TV ★ |
| `clock_wallpaper`, `clock_wallpaper_dim` | L'horloge sur le fond d'écran plutôt que sur du noir |
| `clock_glow` | Couper la lueur colorée : horloge sur noir pur, mieux sur OLED ★ |

Aerial Views reste ton écran de veille système ; ces clés-là n'y touchent pas.

### Bibliothèque d'applis
| Clé | Ce qu'elle fait |
|---|---|
| `app_library` | Un bouton « Bibliothèque » après la dernière rangée : toutes les applis, à ajouter à l'accueil ou à retirer ★ |
| `library_button`, `show_library_pill` | La bibliothèque en bouton rond dans la barre du haut |
| `app_library_selection`, `library_ok_opens` | Cocher et décocher d'un OK ; OK ouvre aussi les applis cachées |
| `library_apps`, `library_excluded_apps` | Applis ajoutées d'avance ; applis jamais proposées |

### Démarrage
| Clé | Ce qu'elle fait |
|---|---|
| `open_on_start_choice` | Choisir sur la TV une appli qui s'ouvre au premier accueil après l'allumage |
| `open_on_start_on_wake` | La même, à chaque sortie de veille |
| `open_on_start_network_wait` | Attendre que le réseau revienne avant d'ouvrir cette appli |
| `boot_to_live_tv`, `power_on_home`, `power_on_home_always` | — Pour les TV qui démarrent sur la télévision ou une entrée HDMI |

### Touches de la télécommande
Ces clés demandent un service d'accessibilité du lanceur, activé à l'installation (§ 4).

| Clé | Ce qu'elle fait |
|---|---|
| `app_keys` | Une touche ouvre une appli ou fait une action (couper le son, veille, horloge, Wi-Fi, bibliothèque, ne rien faire), avec un 2ᵉ choix en appui long (`hold:`) ★ (le bouton NETFLIX de la télécommande Shield vers Nuvio, par exemple : à relever d'abord sur la Shield) |
| `settings_key_keycodes`, `settings_key_panel` | Une touche ouvre les Paramètres, ou un petit panneau rapide (Affichage, Réseau, Applis) |
| `home_long_press_home` | Un appui long sur Accueil ramène aussi à l'accueil |
| `key_filter`, `gamepad_passthrough`, `consume_volume_keys` | Garde-fous si le service gêne le volume ou une manette |
| `input_key_keycodes`, `handle_view_inputs` | — Touche Entrées |
| `restore_key_services` | Remettre les services de touches après un arrêt forcé du lanceur |
| `stock_home_redirect` | Renvoyer ici si le firmware rallume l'accueil d'origine |

### Divers
| Clé | Ce qu'elle fait |
|---|---|
| `tile_menu` | Le menu de tuile (appui long sur OK) ; oui d'origine |
| `module_cards` | Chercher les cartes modules (§ 7) |
| `vpn_app`, `vpn_pill`, `icon_only_vpn` | Un bouton VPN vert ou rouge selon l'état |
| `race_calendar`, `game_day` | Week-ends de F1 ou matchs d'une équipe sous l'heure |
| `festival`, `seasonal_skin`, `festival_until`, `greeting`, `accent_color` | Habillages de fête (Halloween, Diwali…) |
| `show_home_settings_shortcut` | Une tuile pour choisir l'accueil par défaut |
| Entrées et sources : `live_tv_*`, `input_*`, `source_panel_*`, `coocaa_sources`, `inputs_*` | — Réservé aux TV |

## 3. Dans `config.xml` seulement (recompiler)

- `latitude`, `longitude` : le ciel et les images matin, soir et nuit suivent le vrai soleil de chez toi.
- `icon_tiles`, `settings_key_scancodes`, `settings_fallback_components`.

## 4. À l'installation (`launcher/build-and-install.sh`)

- `HOME_KEY_SERVICE=1` : le service qui lit `app_keys`, la touche Paramètres et l'appui long sur Accueil.
- `INPUT_KEY_SERVICE=1` : le service de la touche Entrées (sans objet ici).
- `RESTORE_KEY_SERVICES=1` : avec `restore_key_services`.
- `STOCK_LAUNCHER`, `SKIP_BUILD`, `FORCE_HOME` : l'installation elle-même (voir `perso/README.md`).

## 5. Écran de veille « Horloge » du lanceur

Il remplace Aerial Views si tu le choisis dans les réglages d'écran de veille de la Shield
(`com.gathin.tvhome/.ClockDream`).

## 6. Langue

Le lanceur suit la langue de la Shield (français), ou `locale` dans un thème.

## 7. Cartes modules (`addons/`)

Ce sont de petites applis à part, chacune à compiler et installer. Elles ajoutent une tuile à l'accueil
sans toucher au lanceur. Il y en a environ 33. Celles qui pourraient te servir :

- **Météo** (Open-Meteo, a besoin d'Internet).
- **Minuteur de mise en veille**.
- **Heures calmes** : un plafond de volume la nuit.
- **Statistiques** : mémoire et stockage.
- **Accès à distance** : l'état du débogage réseau.
- **Bloqueur de pubs** : pour Pi-hole ou AdGuard Home ; ton DNS AdGuard public n'en est pas un.
- **Écran de veille photo**, **Horloges du monde**, **Maison** (Home Assistant).

Liste complète, avec les permissions de chacune : `addons/README.md`.
