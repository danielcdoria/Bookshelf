# Bookshelf API

A REST API for managing your personal book collection. Each user has their own library — track what you're reading, what you've finished, and what's on your list.

**Live:** https://bookshelf-production-8fd8.up.railway.app

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security + JWT
- PostgreSQL
- Docker

## Features

- User registration and login with JWT authentication
- Each user can only see and manage their own books
- Filter books by reading status or genre
- Input validation and standardized error responses

## Getting Started (local)

**Prerequisites:** Java 21, Docker

```bash
# Start the database
docker compose up -d

# Run the application
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Authentication

All `/books` endpoints require a Bearer token in the `Authorization` header.

### Register
```
POST /auth/register
```
```json
{
  "name": "Daniel",
  "email": "daniel@email.com",
  "password": "yourpassword"
}
```

### Login
```
POST /auth/login
```
```json
{
  "email": "daniel@email.com",
  "password": "yourpassword"
}
```

Both return:
```json
{
  "token": "eyJhbGci..."
}
```

## Endpoints

All requests below require `Authorization: Bearer <token>`.

### List all books
```
GET /books
```

### Get book by ID
```
GET /books/{id}
```

### Add a book
```
POST /books
```
```json
{
  "title": "The Pragmatic Programmer",
  "author": "David Thomas",
  "pages": 352,
  "status": "READING",
  "genre": "NON_FICTION"
}
```

### Delete a book
```
DELETE /books/{id}
```

### Filter by status
```
GET /books/status?status=READING
```

Available values: `WANT_TO_READ` `READING` `FINISHED`

### Filter by genre
```
GET /books/genre?genre=FICTION
```

Available values: `FICTION` `NON_FICTION` `SCIENCE` `HISTORY` `FANTASY` `BIOGRAPHY`

## Environment Variables

| Variable | Description |
|---|---|
| `JWT_SECRET` | Secret key for signing JWT tokens |
| `PGHOST` | PostgreSQL host |
| `PGPORT` | PostgreSQL port |
| `PGDATABASE` | Database name |
| `PGUSER` | Database user |
| `PGPASSWORD` | Database password |
