# Student Management API

API REST de gestion des étudiants construite avec Spring Boot, Spring Data JPA, Validation et Swagger UI.

## Prérequis

- Java 21
- Maven (le projet inclut Maven Wrapper)

## Démarrer l'application

```powershell
./mvnw.cmd spring-boot:run
```

Par défaut, l'application utilise H2 en mémoire afin de pouvoir démarrer sans installer MySQL. Les données sont effacées à l'arrêt.

- Swagger UI : http://localhost:8080/swagger-ui.html
- OpenAPI JSON : http://localhost:8080/v3/api-docs
- Console H2 : http://localhost:8080/h2-console

Pour utiliser MySQL, créer la base `studentdb`, puis définir les variables d'environnement avant le démarrage :

```powershell
$env:DB_URL = 'jdbc:mysql://localhost:3306/studentdb?serverTimezone=UTC'
$env:DB_USERNAME = 'root'
$env:DB_PASSWORD = 'votre-mot-de-passe'
./mvnw.cmd spring-boot:run
```

## Routes

| Méthode | Route | Action |
| --- | --- | --- |
| GET | `/api/students` | Lister les étudiants |
| GET | `/api/students/{id}` | Lire un étudiant |
| POST | `/api/students` | Créer un étudiant |
| PUT | `/api/students/{id}` | Modifier un étudiant |
| DELETE | `/api/students/{id}` | Supprimer un étudiant |

Exemple de corps JSON :

```json
{
  "firstName": "Rayan",
  "lastName": "Layhi",
  "email": "rayan@example.com"
}
```

## Tests

```powershell
./mvnw.cmd test
```
