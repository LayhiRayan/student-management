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

## Endpoints affichés dans Swagger

Swagger UI regroupe également les routes exposées automatiquement par Spring Data REST. Les sections présentées dans l'interface sont les suivantes :

### `Étudiants` — API REST personnalisée

| Méthode | Route | Description |
| --- | --- | --- |
| GET | `/api/students` | Lister tous les étudiants |
| GET | `/api/students/{id}` | Obtenir un étudiant par son identifiant |
| POST | `/api/students` | Créer un étudiant |
| PUT | `/api/students/{id}` | Modifier un étudiant |
| DELETE | `/api/students/{id}` | Supprimer un étudiant |

### `student-entity-controller` — Spring Data REST

| Méthode | Route | Description |
| --- | --- | --- |
| GET | `/students` | Lister les étudiants (résultat paginé) |
| POST | `/students` | Créer un étudiant |
| GET | `/students/{id}` | Obtenir un étudiant |
| PUT | `/students/{id}` | Remplacer un étudiant |
| PATCH | `/students/{id}` | Modifier partiellement un étudiant |
| DELETE | `/students/{id}` | Supprimer un étudiant |

### `student-search-controller` — recherches Spring Data REST

| Méthode | Route | Description |
| --- | --- | --- |
| GET | `/students/search/existsByEmail` | Vérifier si une adresse e-mail existe |
| GET | `/students/search/findByEmail` | Rechercher un étudiant par adresse e-mail |

### `profile-controller` — liens de ressources

| Méthode | Route | Description |
| --- | --- | --- |
| GET | `/profile` | Consulter le profil des ressources REST |
| GET | `/profile/students` | Consulter le profil de la ressource étudiants |

Les routes `/api/students` appliquent la validation et les réponses d'erreur personnalisées de l'API. Les routes `/students` sont générées automatiquement par Spring Data REST et exposent directement le repository.

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
