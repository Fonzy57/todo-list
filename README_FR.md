# 📌 Application To-Do List - Gestion des Tâches et Utilisateurs

[🇬🇧 Read this document in English](README.md)

## 📖 Description

Cette application Java permet de gérer une liste de tâches et d'utilisateurs en utilisant les opérations CRUD (Créer,
Lire, Mettre à jour, Supprimer). Chaque utilisateur peut avoir plusieurs tâches associées.

## 🛠️ Fonctionnalités

- **Gestion des utilisateurs :** Ajouter, modifier, supprimer et afficher les utilisateurs.
- **Gestion des tâches :** Ajouter, modifier, supprimer et afficher les tâches.
- **Association utilisateur-tâche :** Chaque tâche est assignée à un utilisateur spécifique.
- **Stockage des données :** Les données peuvent être stockées dans une base de données ou un fichier.

## 📂 Structure du Projet

```
/todo-list
│── /src
│   ├── models         # Classes représentant les entités (Utilisateur, Tâche)
│   ├── services       # Gestion des opérations CRUD
│   ├── controllers    # Interface entre l'utilisateur et les services
│   ├── database       # Gestion du stockage des données
│── /tests             # Tests unitaires
│── README.md          # Documentation du projet
│── pom.xml            # (Si utilisation de Maven)
│── build.gradle       # (Si utilisation de Gradle)
```

## 🚀 Installation et Utilisation

1. **Cloner le projet :**
   ```bash
   git clone https://github.com/votre-utilisateur/todo-list-java.git
   cd todo-list-java
   ```
2. **Compiler et exécuter :**
    - Avec Maven :
      ```bash
      mvn clean install
      mvn exec:java -Dexec.mainClass="com.votrepackage.Main"
      ```
    - Avec Gradle :
      ```bash
      gradle build
      gradle run
      ```
    - En ligne de commande :
      ```bash
      javac -d bin src/*.java
      java -cp bin com.votrepackage.Main
      ```

## ✅ Prérequis

- Java 17+
- Maven ou Gradle (optionnel)

## 📜 Licence

Ce projet est sous licence MIT.

## ✨ Auteur

Développé par **[Votre Nom]**.
