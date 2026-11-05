# Projectivy 4.73 : tous les réglages, et ce que je conseille

Pour la Shield TV Pro 2019 de Sébastien, branchée sur la LG OLED G5 de 77", le 05/11/2026.

**D'où vient cette liste.** J'ai décompilé l'APK 4.73 installé, avec l'accord de Sébastien. Le code
décompilé est dans `~/multimedia/projectivy` (dépôt privé). Les noms
viennent de la traduction française de l'appli, et l'ordre des menus vient du registre des réglages dans le
code. Je n'ai pas encore parcouru les écrans de la Shield. Deux limites en découlent :
- Si un intitulé ou un emplacement diffère à l'écran, c'est l'écran qui a raison.
- Plusieurs menus n'apparaissent que sur les appareils compatibles, en général les TV et les projecteurs
  Xiaomi, MediaTek ou Amlogic. Je les signale par **(pas sur Shield ?)**.

Certaines options portent une marque à l'écran : « Premium requis », « Service d'accessibilité requis » ou
« Expérimental ». Je n'ai pas pu relever lesquelles dans le code.

**Ce qui guide mes conseils.**
- **Un écran OLED** : rien de fixe et lumineux qui reste longtemps à l'écran.
- **La légèreté** gagnée avec CleanYourTV : pas de vidéo en fond, pas de préchargement.
- **La vie privée** : pas de statistiques envoyées.
- **Rien ne doit doublonner ce que la Shield fait déjà** : veille, écran de veille Aerial Views,
  allègement par CleanYourTV.

Légende : ✅ activer · ❌ laisser désactivé · 🎨 question de goût (valeur suggérée) · — sans objet ici.

## À changer par rapport aux valeurs d'origine

Ces valeurs d'origine sont lues dans le code, fichier `defpackage/mj3.java` de `~/multimedia/projectivy`.
Tout le reste peut garder sa valeur d'origine, sauf question de goût.

| Réglage | D'origine | À mettre | Pourquoi |
|---|---|---|---|
| Général › Remplacer le launcher actuel | activé | **désactivé** | Projectivy est déjà l'Accueil par adb. |
| Accessibilité › Afficher rappel | activé | désactivé | Le service est actif. |
| Accessibilité › Remappage des boutons | activé | **désactivé** | D'après l'appli, le couper évite les soucis de volume et empêche le système de fermer le service. Il ne sert qu'au remplacement du launcher, au contrôle parental et à la détection d'inactivité, que tu n'utilises pas. |
| Mode inactif › Conserver l'horloge | activé | **désactivé** | Une horloge fixe sur OLED. |
| Barre d'état › Afficher la batterie | activé | désactivé | Pas de batterie. |
| Fond d'écran › Largeur minimale acceptée | 800 px | **1920 px** | Fonds nets sur la 4K. |
| Fond d'écran › Fournisseur | source par défaut | Programme sélectionné | Effet Apple TV (si les chaînes sont actives). |
| Barre d'état › Format de l'heure | vide | `HH:mm` / `EEEE d MMMM` | Heure et date en clair. |

Déjà bien réglés d'origine :
- **Rapports anonymes (usage et plantages) désactivés.**
- Vérification des mises à jour activée.
- Mode inactif après 2 min, à 0 % d'opacité.
- Économiseur d'écran système laissé libre.
- Détection d'inactivité interne coupée.
- Fond changé toutes les 60 min, sans changement pendant une vidéo.
- Volume du fond vidéo à 0.

---

## 1. Général

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Remplacer le launcher actuel | Intercepte la touche Accueil par l'accessibilité, pour les appareils dont on ne peut pas changer le lanceur. | ❌ Projectivy est déjà l'Accueil par adb. Cette méthode a lâché sur des Shield en 9.2, et elle doublonnerait. |
| Activer les rapports d'utilisation anonymes | Statistiques d'usage envoyées à Google Firebase Analytics. Désactivé d'origine. | ❌ Le DNS AdGuard bloque aussi leur adresse (vérifié). |
| Activer les rapports de crash anonymes | Rapports de plantage envoyés à Firebase Crashlytics, que le DNS ne bloque pas. Désactivé d'origine. | ❌ Ou ✅ si tu veux aider l'auteur : rien ne part tant que l'appli ne plante pas. |
| Vérifier les mises à jour | Cherche les nouvelles versions sur GitHub. | ✅ Indispensable, puisque tu l'as installé hors Play Store. |
| Corriger les erreurs Google Play sur installation/mise à jour | Contournement pour les appareils où le Play Store échoue. | ❌ Ton Play Store marche. |
| Changer la langue de l'application | | Français (suit la Shield). |
| Changer le launcher par défaut | Ouvre le choix du lanceur Android. | — Je m'en charge par adb, ça tient mieux. |
| Activer les chaînes | Les rangées de contenus fournies par tes applis (« Continuer à regarder », recommandations). L'appli prévient que les couper accélère le démarrage et économise de la mémoire. | ✅ si tu veux la rangée « Continuer à regarder » et des fonds tirés de tes programmes ; sinon ❌, c'est le plus léger. Mon conseil : ✅, en ne gardant que les chaînes utiles (§ 9). |

## 2. Entrées **(pas sur Shield ?)**

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Activer entrées / raccourcis HDMI 1-4, AV, Popup des entrées | Raccourcis vers les entrées d'une TV Android. | — La Shield n'a pas d'entrée HDMI : à masquer s'ils apparaissent. |
| Préférer la vue TV interne | Affiche une entrée HDMI dans Projectivy. | — Idem. |

## 3. Contrôle parental

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Définir un nouveau code parental | Un code en flèches et chiffres pour bloquer des applis choisies par appui long. | ❌ Sauf si tu veux verrouiller une appli. |
| Activer la protection seulement entre … et … | Plage horaire du blocage. | — |
| Ne plus demander de code pendant 15 minutes | | — |
| Protéger les paramètres et changements d'UI du launcher | Code exigé pour toucher aux réglages. | ❌ Intéressant seulement si un invité tripote la Shield. |

## 4. Alimentation

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Lancement automatique au démarrage (+ « …et à la sortie du mode veille », délai) | Ouvre une appli ou une entrée quand la Shield démarre ou se réveille. | ❌ Tu passes d'une appli à l'autre. À la rigueur Nuvio, si c'est toujours ta première appli du soir. |
| Extinction auto lors de l'arrêt du signal HDMI | Pour une TV qui reçoit un boîtier. | — Sans objet sur la Shield. |
| Empêcher l'arrêt automatique du système en cas d'inactivité | Simule une activité pour que l'appareil ne s'endorme jamais. | ❌ Il faut au contraire que la Shield se mette en veille, et qu'elle éteigne la LG par HDMI-CEC. |
| Activer la détection interne d'inactivité (délai, action : arrêt écran / économiseur / extinction ; « Ignorer pendant la lecture de media ») | Une seconde minuterie de veille, propre à Projectivy. | ❌ La Shield a déjà la sienne (écran de veille à 20 min). Deux minuteries se contrediraient. |
| Action d'extinction (Menu d'extinction / + Ok / double appel) | Ce que fait le bouton d'arrêt de Projectivy. | 🎨 « Menu d'extinction » : il laisse le choix entre veille et redémarrage. |

## 5. Télécommande

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Afficher des infos au double appui sur « Menu » | Infos sur l'appli au premier plan. | 🎨 Sans risque. La touche ≡ de la Shield se règle aussi dans les Paramètres de la Shield. |
| « Mode Ambiant » au double appui sur « Retour » | Lance l'écran de veille tout de suite. | ✅ Lance Aerial Views à la demande, pratique sur un OLED quand tu fais une pause. **À tester.** |
| Basculer la réduction d'input lag à l'appui long sur « Menu » | Pour les TV MediaTek. | — Sans effet sur la Shield. La latence se règle sur la LG (mode Jeu). |
| Lier le bouton « Assistant vocal » à l'Assistant Google | Force le bouton micro vers l'Assistant. | ❌ Le micro de la télécommande Shield va déjà vers l'Assistant. Active-le seulement si le micro ne répond plus depuis Projectivy. |

## 6. Service d'accessibilité (menu Système)

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Afficher rappel (si le service est désactivé) | | ❌ Le service est actif, et le raccourci de la Shield vers cette page est cassé. |
| Configuration alternative | Contournement pour certains appareils. | ❌ Seulement en cas de souci de touches. |
| Activer le remappage des boutons | Laisse Projectivy intercepter toutes les touches pour les personnaliser. D'après l'appli, le couper évite des soucis de volume aux appuis longs. | ❌ Sauf si tu veux réaffecter un bouton précis. Si tu l'actives, active aussi le suivant. |
| Correction des boutons de volume | Coupe le remappage pendant un appui sur Volume. | ✅ seulement si le remappage est actif. |

## 7. Apparence › Général

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Charger les cartes pendant le défilement | Plus réactif, moins fluide. | ❌ La fluidité d'abord sur un 77". |
| Couleur d'accentuation | Couleur de sélection et des curseurs. | 🎨 Laisse les couleurs dynamiques (§ 8) décider, ou une teinte sobre. |
| Police : choisir dans la liste / Gras / Italique / Taille | Police Google Fonts ou personnelle. | 🎨 La police par défaut est lisible. Taille 1,0 à 1,1 à 3 m d'un 77". |
| Thèmes › Préréglages / Appliquer ce thème | Des ensembles de réglages tout faits. Un thème écrase ton apparence actuelle. | 🎨 Bon point de départ : choisis un préréglage, puis affine. **Exporte d'abord tes réglages** (§ 13). |
| Pack d'icônes | Remplace les icônes des applis. | ❌ « Aucun » : il faudrait installer une appli de plus. |

## 8. Apparence › Fond d'écran

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Afficher les fonds d'écran | | ✅ |
| Fournisseur de fonds d'écran | Plusieurs sources : la nouvelle source par défaut (Reddit va fermer son flux), Wikimedia Commons, un thème, **le programme sélectionné ou un programme aléatoire** (l'affiche du film ou de la série où tu es), un dossier, une image ou vidéo, une couleur, ou des modules externes. | ✅ **Programme sélectionné** si les chaînes sont actives : c'est l'effet Apple TV / Google TV, et l'image change sans cesse, ce qui est bon pour l'OLED. Sinon la source par défaut. |
| Fréquence de mise à jour (min) | Toutes les combien de minutes l'image change. | 🎨 30 à 60 min. |
| Ignorer la fréquence de mise à jour (attendre la fin de la vidéo) | | ✅ |
| Mode d'affichage | Automatique / Couper / Étirer / Center / Center + fond flouté / Flouter. | Automatique. |
| En cas de mauvais ratio/qualité | Que faire d'une image mal cadrée. | Center + fond flouté, ou Ignorer pour la sauter. |
| Largeur minimale acceptée (px) | Refuse les images trop petites. | **1920** : en dessous, l'image serait floue sur le 4K ; 3840 en rejetterait trop. |
| Transition d'images | Fondu / glissements / zoom / aléatoire. | 🎨 Fondu. |
| Couleurs dynamiques | L'interface prend les couleurs de l'image. | ✅ C'est ce qui rend Projectivy joli. |
| Luminosité / Contraste / Saturation / Teinte / Flou / Coloration | Retouches appliquées à l'image de fond. | 🎨 Luminosité un peu baissée (−10 à −20 %) : plus confortable le soir sur OLED, et le texte ressort mieux. Flou à 0. |
| Ombre superposée (longueur horizontale et verticale, opacité, teinte) | Le dégradé sombre sous les rangées, pour la lisibilité. | Garder les valeurs d'origine ; monter l'opacité si le texte se lit mal. |
| **Fond d'écran vidéo** : volume, rendu matériel (tunneling), décodeurs de repli, fréquence de la couleur dynamique | Pour un fond animé (GIF, vidéo, flux). | ❌ Pas de fond vidéo : mémoire, processeur et, sur certaines vidéos, passage en HDR de la LG avec écran noir. Si tu en veux un quand même : volume 0, tunneling ✅. |

## 9. Apparence › Cartes et programmes

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Afficher le titre | Nom sous chaque appli. | 🎨 ✅ au début, ❌ quand tu connais tes icônes (plus épuré). |
| Coins arrondis | | 🎨 Valeur d'origine. |
| Taille des cartes / Espacement des cartes | | 🎨 Valeurs d'origine, puis à l'œil. |
| Limite de boucle des icônes animées | Nombre de fois qu'une icône animée se rejoue. | 1 à 3 : pas d'animation sans fin. |
| Effet ripple à l'appui d'une carte | | 🎨 |
| Couleur d'arrière-plan / Teinte des icônes personnalisées | | 🎨 Vide (automatique). |
| Format des cartes Apps TV / Apps Mobile | 16/9, 4/3, 1/1, affiche… | Apps TV : 16/9 (leurs bannières). Apps mobiles : 1/1. |
| Jouer les vidéos de prévisualisation (+ « seulement quand aucun média ne joue ») | Bandes-annonces automatiques sur les cartes de programmes. | ❌ Réseau, mémoire et son intempestif. |
| Afficher la barre de progression | Avancement d'un épisode commencé. | ✅ |
| Afficher le badge | Logo de l'appli sur la carte d'un programme. | ✅ |
| Effet de zoom à la sélection | | ✅ |
| Programmes par app (« Continuer à regarder ») / Programmes par chaîne | Nombre de cartes par rangée. | 3 à 5 par appli ; 10 à 15 par chaîne. |

**Carte sélectionnée**

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Taille de la carte sélectionnée | | 🎨 1,1 à 1,15. |
| Effet de carte sélectionnée | Lueur / Ombre portée. | 🎨 Lueur, avec couleur dynamique. |
| Animation de lueur | Respiration / Fondu / Pulsation / Brillance. | 🎨 Aucune ou Respiration (discrète). |
| Taille de la lueur / Couleur de lueur | Vide = couleur de la carte. | 🎨 Couleur vide. |
| Remplacement de couleurs (Aucun / Intervertir fond-teinte / Personnalisé) + couleurs | | 🎨 Aucun. |

## 10. Apparence › Catégories / Chaînes (réglages communs)

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Afficher le titre / l'icône devant le titre | Le nom de chaque rangée. | ✅ titre, 🎨 icône. |
| Espacement vertical | | 🎨 Valeur d'origine. |
| Position de la catégorie sélectionnée fixe | La rangée active reste au même endroit, c'est la liste qui défile. | 🎨 ✅ Façon Apple TV. |
| Masquer les lignes au-dessus de la sélection | | 🎨 ✅ si tu veux un écran épuré, avec le fond bien visible. |
| Conserver la position de la carte sélectionnée | Revient sur la dernière appli choisie de chaque rangée. | ✅ |
| Opacité des lignes non sélectionnées | | 🎨 40 à 60 %. |
| Marges supérieure, début et fin ; padding du dock | | 🎨 Valeurs d'origine. |
| Teinter les icônes personnalisées | | — |

**Par rangée** (appui long sur une rangée, ou Paramètres › Catégories) : titre, icône, visible, **Grille**
ou **Ligne coulissante**, couleur, titres des cartes, taille, puis Paramètres avancés (cartes par ligne,
nombre de lignes, marges), tri, catégorie par défaut, applis incluses, suppression. Pour reproduire ton dock
CleanYourTV : une première rangée « Favoris » en ligne simple avec YouTube+, Nuvio, Canal+ et Spotify, en
**catégorie par défaut**. Dans **Chaînes**, garde « Continuer à regarder » et masque les chaînes
publicitaires ou vides.

## 11. Apparence › Mode inactif du launcher

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| Durée avant inactivité (min) | Délai avant que Projectivy assombrisse son interface quand tu ne touches à rien. | ✅ **2 min** : c'est la vraie protection OLED pendant les pauses, avant Aerial Views. |
| Opacité de l'interface | Ce qui reste visible en mode inactif. | **0 à 20 %** : seul le fond reste. |
| Conserver l'horloge | | ❌ Une horloge fixe sur OLED, non. |
| Empêcher l'économiseur d'écran système | | ❌ Il faut qu'Aerial Views prenne le relais. |

## 12. Apparence › En-têtes de gauche et barre d'état

| Réglage | Ce qu'il fait | Conseil |
|---|---|---|
| En-têtes de gauche : Activer / Afficher au lancement / icône / titre / réorganiser / paramètres | Le menu latéral des catégories. | 🎨 ✅ activé mais ❌ au lancement : l'écran reste épuré, et il s'ouvre avec ← . |
| Afficher la barre d'état système | La barre d'Android. | ❌ |
| Masquer la barre d'état au défilement | | ✅ |
| Taille, couleur et conteneur des icônes | | 🎨 Valeurs d'origine. |
| Afficher les paramètres | Raccourci vers les réglages de Projectivy. Sans lui, une séquence de touches les ouvre. | ✅ |
| Afficher la batterie | | ❌ La Shield n'a pas de batterie. |
| Afficher la connectivité (et le VPN) | Wi-Fi ou Ethernet ; l'icône VPN sert à Tailscale. | 🎨 ✅ |
| Afficher les notifications / Masquer l'icône si vide | Fonctionne grâce à l'accès aux notifications accordé. | ✅ / ✅ |
| Afficher l'heure, taille, couleur, ombre | | ✅ (le mode inactif la fera disparaître). |
| Format de l'heure, lignes 1 et 2 | Format de date Java. | Ligne 1 `HH:mm`, ligne 2 `EEEE d MMMM` (« jeudi 5 novembre »). |
| Gérer la permission de notifications | | Déjà accordée. |

## 13. Gérer paramètres (sauvegarde)

| Réglage | Conseil |
|---|---|
| Exporter (apparence, catégories par défaut, fond d'écran) › Sauvegarder localement ou Partager | ✅ **Une fois tout réglé** : exporte, et dis-le-moi, je copierai le fichier sur le Mac. |
| Importer (sauvegarde automatique, gestionnaire de fichiers) / Restaurer / Réinitialiser | En cas de besoin. |

## 14. Outils de Projectivy à ne pas utiliser sur ta Shield

| Outil | Pourquoi ne pas s'en servir |
|---|---|
| **Désactiver applications natives** (packages suggérés ou manuels) | ❌ **Surtout pas.** L'allègement est déjà fait par la liste CleanYourTV, qui garde ce qui te sert (télécommande SHIELD TV, Assistant, son). La liste de Projectivy pourrait le couper. |
| Bloquer les mises à jour Google Play | ❌ |
| Désinstaller les GApps / Supprimer le launcher d'origine | ❌ L'accueil Google est déjà désactivé, et c'est réversible. |
| Installer applications tierces | ❌ Des APK d'origine inconnue. Pour une appli, passe par moi. |
| Custom Build Props, Désactivateur d'extinction auto, props 4K | ❌ Exigent le root ou Magisk. |
| Profils d'affichage, Zoom, Délai d'entrée, Rendu 4K de l'interface, menus « ingénieur » | — Faits pour les TV et projecteurs Android. L'image se règle sur la LG. Le rendu 4K de l'interface coûterait de la mémoire pour rien. |
| Télécommande web | 🎨 Pratique pour taper du texte depuis le téléphone, sans risque sur ton réseau local. |
| **Mires de calibration** (4K, Dolby Vision, saccades) | ✅ **Utile** : une mire Dolby Vision aidera pour le test Dolby Vision qui reste à faire. |

## Premium

La version gratuite est complète et sans pub. Le premium s'achète par QR code puis clé de licence, sur
premium.projectivylauncher.com. Une clé se libère pour passer sur un autre appareil. Les options marquées
« Premium requis » sont visibles à l'écran.
