# Codeur - Outil de Reverse Engineering en Java

## Description

Le projet **Codeur** est un outil de **reverse engineering** développé en Java. Il permet de reconstruire automatiquement des classes métier à partir du schéma relationnel d'une base de données. Les fichiers générés sont compatibles avec plusieurs langages de programmation (Java, Python, etc.) et incluent des interfaces utilisateur, telles que Swing ou HTML.

Ce projet a été conçu pour offrir une expérience conviviale grâce à une interface graphique intuitive qui facilite la sélection des tables et des langages cibles.

## Fonctionnalités

- **Analyse du schéma relationnel :** Connecte-toi à une base de données et analyse automatiquement les tables et leurs relations.
- **Génération multi-langages :** Produit des classes métier en plusieurs langages, notamment Java et Python.
- **Interfaces utilisateur générées :** Crée des interfaces graphiques en Swing ou des interfaces web en HTML.
- **Interface graphique interactive :** Permet de sélectionner les tables et les options de génération.
- **Respect des contraintes d'intégrité référentielles :** Prend en compte les relations entre les tables (clés étrangères).

## Architecture

Le projet utilise l'architecture **MVC** (Modèle-Vue-Contrôleur) :
- **Modèle :** Représente les données de la base de données sous forme de classes Java.
- **Vue :** Fournit une interface graphique développée avec Swing.
- **Contrôleur :** Contient la logique métier et gère les interactions entre la vue et le modèle.

## Installation

### Prérequis

- Java 8 ou une version ultérieure.
- JDBC Driver pour la base de données cible (par exemple, MySQL, PostgreSQL).
- Un IDE comme Eclipse ou IntelliJ IDEA.
- Une base de données relationnelle.

### Étapes d'installation

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/JlassiaEya/Codeur.git
   cd Codeur
Auteur
Eya Jlassia
Étudiante en Informatique
