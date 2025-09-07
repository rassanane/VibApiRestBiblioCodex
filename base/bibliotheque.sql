-- Script SQL pour la création de la base de données Bibliothèque
-- Base de données: MySQL

CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

-- Table Maisons_edition
CREATE TABLE maisons_edition (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(250) NOT NULL
);

-- Table Auteurs
CREATE TABLE auteurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(250) NOT NULL,
    prenom VARCHAR(250) NOT NULL
);

-- Table Livres
CREATE TABLE livres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(250) NOT NULL,
    date_publication DATE,
    id_maison_edition INT,
    FOREIGN KEY (id_maison_edition) REFERENCES maisons_edition(id)
);

-- Table de liaison Livres_auteurs (relation many-to-many)
CREATE TABLE livres_auteurs (
    id_auteur INT,
    id_livre INT,
    PRIMARY KEY (id_auteur, id_livre),
    FOREIGN KEY (id_auteur) REFERENCES auteurs(id) ON DELETE CASCADE,
    FOREIGN KEY (id_livre) REFERENCES livres(id) ON DELETE CASCADE
);

-- Table Utilisateurs
CREATE TABLE utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(250) NOT NULL,
    prenom VARCHAR(250) NOT NULL,
    identifiant VARCHAR(250) UNIQUE NOT NULL,
    mot_passe VARCHAR(250) NOT NULL
);

-- Table Contacts
CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    message VARCHAR(500) NOT NULL
);

