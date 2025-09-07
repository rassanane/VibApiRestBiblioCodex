## Identité

Vous êtes un ingénieur logiciel de haut niveau, expert en Angular et Spring Boot.

## Instructions

* Veuillez créer SVP un dossier apirest, créer dans ce dossier une application Spring Boot pour exposer les services REST d'une application de gestion d'une bibliothèque.
* Package de l'application : fr.keltou.biblio
* Nom de l'application : BiblioApplication
* Utilisez cette base de données MySQL : url : jdbc:mysql://192.168.1.92:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris
* Utilisateur : appli
* Mot de passe : appli
* Utilisez le composant Spring Data JPA pour interagir avec la base de données.
* Sécurisez l'API avec une basic authentification : login : rachid, mot de passe : rachid123
* Cette API est composée de cinq modules : auteurs, livres, maisons d'édition, contacts et utilisateurs.
* Un auteur peut avoir plusieurs livres.
* Un livre est écrit par un ou plusieurs auteurs.
* Un livre peut avoir une seule maison d'édition.

# Objet Java

* Le POJO auteur est composé des propriétés suivantes : id, prenom, nom et livres
* Le POJO livre est composé des propriétés suivantes : id, titre, datePublication, maisonEdition et auteurs
* Le POJO maisonEdition est composé des propriétés suivantes : id, nom
* Le POJO contact est composé des propriétés suivantes : id, nom, email, message
* Le POJO utilisateur est composé des propriétés suivantes : id, nom, prenom, identifiant, motPasse

# Table base de données

* Le script SQL de création des tables est dans le répertoire base : bibliotheque.sql


# Services

* L'API doit exposer toutes les opérations de gestion des auteurs, des livres, des maisons d'édition et des utilisateurs (ajout, mise à jour, suppression, liste).
* L'API doit exposer les services d'ajout d'un contact, lister les contacts, voir le détail d'un contact et supprimer un contact
* Créer un service qui permet l'authentification des utilisateurs (vérification de l'identifiant et le mot de passe dans la table Utilisateurs)

# Exigences techniques

* J'utilise Eclipse pour le développement Java
* Utiliser Maven pour la gestion des dépendances.
* Externaliser toutes les variables dans le fichier de propriétés.
* Créer deux profils : dev et prod.
* Ajouter un bloc try-catch pour gérer les erreurs dans toutes les méthodes et créer des classes spécifiques pour gérer les différents types d'exceptions.
* Ajouter des commentaires dans toutes les méthodes.
* Ajouter des logs dans toutes les méthodes.
* Ajouter des tests unitaires pour les méthodes des contrôleurs et des services avec Mockito.
* Ajouter la possibilité de surveiller l'API avec le module Actuator.
* Documenter l'API avec Swagger.
* Créer un package utils afin de gérer les chaînes de caractères et les dates.

# Sécurité

* Autoriser l'origine "http://localhost:4200" à appeler l'application via les méthodes "GET", "POST", "PUT", "DELETE" et "OPTIONS".

# Manuel de déploiement

* Créer un fichier ou il y a toutes les instructions pour un déploiement rapide



Prendre votre temps pour réfléchir.







