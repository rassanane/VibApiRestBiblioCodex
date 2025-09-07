# Bibliothèque – API REST (Spring Boot)

Ce projet implémente une API REST de gestion d'une bibliothèque (auteurs, livres, maisons d'édition, contacts, utilisateurs) avec Spring Boot, JPA, sécurité Basic Auth, Swagger et Actuator.

## Prérequis
- Java 17+
- Maven 3.8+
- MySQL accessible (base `testdb` sur `192.168.1.92:3306`)
- (Optionnel) Eclipse pour import Maven

## Structure
- `apirest/` : application Spring Boot
  - `src/main/java/fr/keltou/biblio/` : code (model, repository, service, controller, config, exception, utils)
  - `src/main/resources/` : `application.properties`, profils `dev` et `prod`
  - `base/bibliotheque.sql` : script SQL de création des tables
  - `src/test/java/...` : tests unitaires (Mockito / MockMvc)
- `.github/workflows/maven.yml` : workflow GitHub Actions (build + tests)

## Configuration
Par défaut, le profil actif est `dev` (voir `apirest/src/main/resources/application.properties`).
- `application-dev.properties` :
  - `spring.datasource.url=jdbc:mysql://192.168.1.92:3306/testdb?...`
  - `spring.datasource.username=appli` / `spring.datasource.password=appli`
  - `spring.jpa.hibernate.ddl-auto=update`
- `application-prod.properties` : `ddl-auto=none`, logs réduits

Sécurité (Basic Auth) :
- Utilisateur en mémoire: `rachid` / `rachid123`
- CORS autorisé: `http://localhost:4200` (GET, POST, PUT, DELETE, OPTIONS)

## Lancer l’application
1) (Optionnel) Créer le schéma/tables MySQL avec `apirest/base/bibliotheque.sql` (sinon laisser JPA gérer en `dev`).
2) Démarrer en développement:
```
cd apirest
mvn spring-boot:run
```
3) Packager le jar:
```
cd apirest
mvn -DskipTests package
java -jar target/biblio-0.0.1-SNAPSHOT.jar
```
4) Changer de profil:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
# ou
java -jar target/biblio-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## Endpoints principaux
- Auteurs: `GET/POST/PUT/DELETE /api/auteurs`
- Livres: `GET/POST/PUT/DELETE /api/livres`
- Maisons d’édition: `GET/POST/PUT/DELETE /api/maisons-edition`
- Utilisateurs: `GET/POST/PUT/DELETE /api/utilisateurs`
- Contacts: `POST /api/contacts`, `GET /api/contacts`, `GET/DELETE /api/contacts/{id}`
- Authentification applicative: `POST /api/auth/login` body `{ "identifiant": "...", "motPasse": "..." }`
- Swagger UI: `/swagger-ui.html`
- Actuator: `/actuator/health`

Toutes les routes (sauf Swagger/Actuator health) sont protégées par Basic Auth.

## Lancer les tests
- Tous les tests:
```
cd apirest
mvn test
```
- Test ciblé (exemples):
```
mvn -Dtest=UtilisateurServiceTest test
mvn -Dtest="*ControllerTest" test
```

Les tests couvrent:
- Services: scénarios de succès et erreurs (not found) avec Mockito
- Contrôleurs: statuts HTTP (200/204/400/404) avec MockMvc et advice global
- Authentification applicative: `/api/auth/login`
- Utilitaires: `StringUtils`, `DateUtils`
- Gestion d’erreurs: `GlobalExceptionHandler`

## Workflow GitHub Actions
Fichier: `.github/workflows/maven.yml`
- Déclencheurs: `push` et `pull_request`
- Environnement: `ubuntu-latest`, JDK 17 (Temurin)
- Étapes:
  - Checkout du dépôt
  - Setup Java 17 avec cache Maven
  - `mvn test` dans le dossier `apirest` (build + exécution des tests)
  - `mvn package -DskipTests` (packaging, même si les tests échouent, pour archivage rapide)

Résultats:
- L’onglet Actions affiche le statut du pipeline (succès/échec) et les logs des tests.

## Import Eclipse
- File > Import > Existing Maven Projects > sélectionner le dossier `apirest`

## Notes
- La config DB, Swagger et Actuator est externalisée.
- Les erreurs sont gérées via `GlobalExceptionHandler` avec logs.
- Les tests MockMvc désactivent les filtres de sécurité pour isoler les contrôleurs.

