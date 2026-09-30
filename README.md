# Notes Service

A runnable REST API for a shared note-taking service used by multiple small teams.

The service allows users to create, retrieve, update, delete, and filter notes by owner.

## Technology Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 in-memory database
- Jakarta Bean Validation
- Gradle
- JUnit / Spring Boot Test

## Architecture

The application follows a simple layered architecture:

```text
HTTP Request
     |
     v
NoteController
     |
     v
NoteService
     |
     v
NoteRepository
     |
     v
H2 Database

Layers

Controller

Exposes the REST API and handles request validation.

Service

Contains application/business logic and handles note lookup and not-found behavior.

Repository

Uses Spring Data JPA to persist and retrieve notes.

Entity

Represents the Note persisted in the database.

Exception Handler

Provides consistent JSON error responses for validation errors and missing notes.
API
Method	Endpoint	Description
POST	/api/notes	Create a note
GET	/api/notes	Get all notes
GET	/api/notes?owner=team-a	Get notes for an owner
GET	/api/notes/{id}	Get a note
PUT	/api/notes/{id}	Update a note
DELETE	/api/notes/{id}	Delete a note
Example Request
Create a note

curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Deployment Notes",
    "content": "Notes for the upcoming deployment.",
    "owner": "team-a"
  }'

Get notes

curl http://localhost:8080/api/notes

Get notes by owner

curl "http://localhost:8080/api/notes?owner=team-a"

Update

curl -X PUT http://localhost:8080/api/notes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Deployment Notes",
    "content": "Updated information.",
    "owner": "team-a"
  }'

Delete

curl -X DELETE http://localhost:8080/api/notes/1

Running the Application

Requirements:

    Java 21

    Git

Run:

./gradlew bootRun

The service starts on:

http://localhost:8080

Running Tests

Run:

./gradlew clean test

The test suite verifies:

    Note creation

    Owner-based retrieval

    Missing-note handling

    Request validation

Data Storage

The application uses an H2 in-memory database to keep the assessment self-contained and immediately runnable.

Data is intentionally ephemeral and is lost when the application stops.

For production, this would be replaced with a persistent relational database such as PostgreSQL or an AWS-managed database.
Error Handling

The service returns:

    201 Created when a note is created

    200 OK for successful reads and updates

    204 No Content after successful deletion

    400 Bad Request for invalid input

    404 Not Found when a note does not exist

Errors are returned as structured JSON responses.
Design Decisions
Why Spring Boot?

Spring Boot provides a lightweight way to build a production-style REST service with dependency injection, validation, persistence, and testing support.
Why H2?

The assessment asks for a runnable service. H2 removes the need for an external database and allows the reviewer to clone the repository and run the application immediately.
Why layered architecture?

Separating controller, service, and repository responsibilities keeps the implementation easy to understand, test, and extend.
What I Would Add or Change for Production
Add

    Authentication and authorization

    Team/user identity from an identity provider rather than accepting owner directly

    Persistent PostgreSQL/Aurora database

    Database migrations using Flyway or Liquibase

    OpenAPI/Swagger documentation

    Pagination and sorting

    Audit history for note changes

    Optimistic locking for concurrent updates

    Structured logging

    Metrics and distributed tracing

    CI pipeline with automated tests, static analysis, dependency scanning, and security scanning

    Containerization and deployment automation

Change

    Use request and response DTOs rather than exposing persistence entities directly

    Make ownership rules explicit and enforce authorization at the service layer

    Externalize environment-specific configuration

    Use production database connection pooling and resilience settings

    Add integration tests against a production-like database

Stop Doing

    Stop using an in-memory database for production workloads

    Stop trusting client-provided ownership information

    Stop relying only on application-level validation

    Stop exposing internal persistence models as the public API contract

Known Limitations

This implementation intentionally keeps the scope appropriate for the assessment.

    No authentication/authorization

    H2 is in-memory

    No pagination

    No audit trail

    Owner is supplied by the API client

    Production deployment configuration is not included

These are intentional areas for future enhancement rather than requirements needed for the basic runnable service.
