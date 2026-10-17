# Coloration de Graphes — Exploration Algorithmique (Java)

Application Java permettant de charger, visualiser et colorier des graphes à partir de jeux de données CSV. Le projet implémente et compare trois algorithmes classiques de coloration de sommets afin de minimiser le nombre de couleurs utilisées sans que deux sommets adjacents ne partagent la même couleur.

## Fonctionnalités et algorithmes

- **Importation et affichage (`GraphLoader`) :** Lecture de la topologie du graphe (coordonnées des sommets et liste des arêtes) depuis deux fichiers CSV distincts, puis rendu graphique à l'écran.
- **Trois algorithmes de coloration implémentés :**
  1. **Algorithme de Welsh & Powell :** Approche gloutonne triant les sommets par degré décroissant avant d'attribuer les couleurs.
  2. **6-coloration planaire :** Algorithme récursif s'appuyant sur la propriété qu'un graphe planaire possède toujours un sommet de degré inférieur ou égal à 5.
  3. **5-coloration planaire :** Optimisation garantissant la coloration de tout graphe planaire avec au plus 5 couleurs.
- **Validation et tests :** Suite de tests unitaires et tests d'intégration (sous JUnit 4) validant la conformité des colorations sur un jeu de 5 graphes de référence.

## Prérequis

- **Java JDK 21**
- **Apache Ant** ou **NetBeans**
- **JUnit 4.13.2** et **Hamcrest 1.3** (archives JAR déjà incluses dans le dossier `libs/`)

## Installation et lancement

```bash
git clone https://github.com/rafael-hocquel/PU-exploration-algorithmique.git
cd PU-exploration-algorithmique
