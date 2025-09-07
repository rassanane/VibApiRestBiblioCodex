-- Données de développement: utilisateur par défaut pour tests d'authentification applicative
INSERT INTO utilisateurs (nom, prenom, identifiant, mot_passe)
VALUES ('Rachid', 'User', 'rachid', 'rachid123')
ON DUPLICATE KEY UPDATE identifiant = identifiant;

