# Déploiement rapide - BiblioApplication

- Prérequis: Java 17+, Maven 3.8+, MySQL accessible, Eclipse (optionnel).

- Base de données: Créez les tables avec `apirest/base/bibliotheque.sql` (ou laissez JPA créer/mettre à jour le schéma en profil `dev`).

- Configuration:
  - Fichier: `src/main/resources/application-dev.properties` (profil par défaut `dev`).
  - URL MySQL: `jdbc:mysql://192.168.1.92:3306/testdb?...`.
  - Identifiants: `appli` / `appli`.
  - Changez le profil via `-Dspring.profiles.active=prod` si nécessaire.

- Sécurité:
  - Basic Auth activée pour toutes les routes API.
  - Identifiants: `rachid` / `rachid123`.

- Démarrer en dev:
  - `mvn spring-boot:run` depuis le dossier `apirest`.

- Démarrer jar:
  - `mvn -DskipTests package` puis `java -jar target/biblio-0.0.1-SNAPSHOT.jar`.

- Points d’accès:
  - API: protégée par Basic Auth (ex: `GET /api/auteurs`).
  - Auth applicative: `POST /api/auth/login` body `{ "identifiant": "...", "motPasse": "..." }`.
  - Actuator: `/actuator/health` (public), autres selon profil.
  - Swagger UI: `/swagger-ui.html`.

- Import Eclipse:
  - File > Import > Existing Maven Projects > dossier `apirest`.

- Tests:
  - `mvn test`.

- CORS:
  - Autorise `http://localhost:4200` pour GET/POST/PUT/DELETE/OPTIONS.

