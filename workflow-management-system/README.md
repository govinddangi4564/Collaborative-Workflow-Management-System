# Collaborative Workflow Management System

Spring Boot REST API for a Jira-style workflow board system:
Projects -> Boards -> Tickets -> Comments, with users, ticket history, and optimistic locking.

**Stack:** Java 17, Spring Boot, Hibernate, Hibernate Envers, PostgreSQL

## Features

- Projects, boards, tickets, comments, and users are exposed through JSON REST endpoints.
- Ticket history is tracked with Hibernate Envers and exposed through a dedicated endpoint.
- Ticket updates use optimistic locking with `version` so stale writes return `409 Conflict`.
- Ticket and comment timestamps are filled automatically by the entity lifecycle callbacks.

## Run the project

Set these environment variables for your database connection:

- `URL`
- `USERNAME`
- `PWD`

Then start the app:

```bash
mvn spring-boot:run
```

Default base URL for Postman:

```text
http://localhost:8080
```

## API Endpoints

| Resource       | Method | Endpoint                                   | Notes                          |
| -------------- | ------ | ------------------------------------------ | ------------------------------ |
| Projects       | POST   | `/projects`                                | Create a project               |
| Projects       | GET    | `/projects`                                | Get all projects               |
| Projects       | GET    | `/projects/{id}`                           | Get a project by id            |
| Projects       | PUT    | `/projects/{id}`                           | Update a project               |
| Projects       | DELETE | `/projects/{id}`                           | Delete a project               |
| Boards         | POST   | `/boards?projectId={projectId}`            | Create a board under a project |
| Boards         | GET    | `/boards?projectId={projectId}`            | Get boards for a project       |
| Boards         | GET    | `/boards/{id}`                             | Get a board by id              |
| Boards         | PUT    | `/boards/{id}`                             | Update a board                 |
| Boards         | DELETE | `/boards/{id}`                             | Delete a board                 |
| Tickets        | POST   | `/tickets`                                 | Create a ticket                |
| Tickets        | GET    | `/tickets?boardId={boardId}`               | Get tickets for a board        |
| Tickets        | GET    | `/tickets/{id}`                            | Get a ticket by id             |
| Tickets        | PUT    | `/tickets/{id}`                            | Update a ticket with `version` |
| Tickets        | DELETE | `/tickets/{id}`                            | Delete a ticket                |
| Ticket history | GET    | `/tickets/{id}/history`                    | View ticket revision history   |
| Comments       | POST   | `/tickets/{ticketId}/comments`             | Add a comment to a ticket      |
| Comments       | GET    | `/tickets/{ticketId}/comments`             | Get comments for a ticket      |
| Comments       | DELETE | `/tickets/{ticketId}/comments/{commentId}` | Delete a comment               |
| Users          | POST   | `/users`                                   | Create a user                  |
| Users          | GET    | `/users`                                   | Get all users                  |
| Users          | GET    | `/users/{id}`                              | Get a user by id               |
| Users          | DELETE | `/users/{id}`                              | Delete a user                  |

## Request JSON Examples

All request bodies use `Content-Type: application/json`.

### 1. Create a Project

`POST /projects`

```json
{
  "name": "Website Redesign",
  "description": "UI refresh for the public site"
}
```

### 2. Create a Board

`POST /boards?projectId=1`

```json
{
  "name": "Sprint Backlog"
}
```

### 3. Create a Ticket

`POST /tickets`

Valid enum values:

- `type`: `EPIC`, `STORY`, `TASK`
- `priority`: `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`

```json
{
  "title": "Login page throws 500",
  "description": "Investigate server error on submit",
  "type": "TASK",
  "priority": "HIGH",
  "boardId": 1,
  "assigneeId": 2
}
```

### 4. Update a Ticket

`PUT /tickets/5`

Send the latest `version` value you received from `GET /tickets/5`.

```json
{
  "title": "Login page throws 500",
  "description": "Fix the validation flow and retry handler",
  "type": "TASK",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "assigneeId": 2,
  "version": 0
}
```

### 5. Add a Comment

`POST /tickets/5/comments`

```json
{
  "content": "I am working on this now.",
  "authorId": 2
}
```

### 6. Create a User

`POST /users`

```json
{
  "username": "jdoe",
  "email": "jdoe@example.com",
  "password": "secret123",
  "fullName": "John Doe"
}
```

## Response Examples

The API returns entity JSON directly from Spring Boot. Some nested relations are suppressed by Jackson annotations to avoid infinite loops.

### Project response

```json
{
  "id": 1,
  "name": "Website Redesign",
  "description": "UI refresh for the public site",
  "boards": []
}
```

### Ticket revision history response

`GET /tickets/5/history`

```json
[
  {
    "revisionNumber": 1,
    "revisionDate": "2026-08-14T10:15:30",
    "revisionType": "ADD",
    "title": "Login page throws 500",
    "status": "TODO",
    "priority": "HIGH",
    "assigneeId": 2
  }
]
```

## Error responses

### 404 Not Found

```json
{
  "timestamp": "2026-08-14T10:20:00",
  "status": 404,
  "message": "Ticket not found with id: 5"
}
```

### 409 Conflict

Returned when a ticket is updated with an outdated `version`.

```json
{
  "timestamp": "2026-08-14T10:20:00",
  "status": 409,
  "message": "This ticket was modified by someone else. Please reload and try again."
}
```

## Notes

- `GET /tickets/{id}/history` reads from the audited ticket revision table.
- `PUT /tickets/{id}` must include the current `version` value to avoid a lost update.
- `User.password` is ignored in JSON responses.
