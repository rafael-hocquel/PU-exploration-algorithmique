# Exploration algorithmique - coloration de graphes

Application **Java** : l'utilisateur choisis un graphe à afficher, il peut ensuite applique trois algorithmes
de coloration à ce dernier (Welsh & Powell, 5 et 6 coloration planaire

**Auteurs :** Rafaël HOCQUEL · Lucas HAMPE GARRIDO  
**Version :** 1.0 - avril 2026

## Ce que fait le programme

| # | Fonction | Réalisation |
|---|----------|-------------|
| 1 | Charger un graphe | Lecture des sommets (position) et des arêtes depuis deux fichiers CSV |
| 2 | Afficher le graphe | Via la classe `GraphLoader` |
| 3 | Colorier le graphe | Trois algorithmes de coloration (Welsh & Powell, 5 et 6 coloration planaire) |
| 4 | Vérifier le résultat | Tests unitaires et test d'intégration sur 5 graphes |

## Prérequis

- **JDK 21**
- **NetBeans** (ou Ant) pour lancer l'application
- **JUnit 4.13.2** et **Hamcrest 1.3** pour les tests, dans `libs/`

## Installation

```
bash
git clone https://github.com/rafael-hocquel/PU-exploration-algorithmique.git
cd PU-exploration-algorithmique
```
