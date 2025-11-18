# User Management Service

User Management Service is a REST full API built with Spring Boot and PostgreSQL for managing users. The system supports creating, updating, deleting, retrieving users by ID, and fetching all users with pagination.

---

## Technologies

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Validation
* PostgreSQL
* Lombok
* Maven
* Docker & Docker Compose

---

## Features

* Create new users (Register)
* Update user details
* Get user by ID
* Paginated list of all users
* Delete users
* Custom exception handling
* Validation support

---

## Project Structure

```
src/main/java/org/example/usermanagementservice
 ├── controller
 ├── dto
 ├── exception
 ├── model
 ├── payload
 ├── repository
 ├── service
 └── UserManagementServiceApplication.java
```

---

## API Endpoints

### 1. Register User

**POST** `/api/auth/register`

**Body:**
```
{
  "name": "John Doe",
  "email": "john@gmail.com",
  "phoneNumber": 12345678,
  "role": "USER"
}
```

**Response:**

```
User create successfully
```

---

### 2. Get User by ID

**GET** `/api/auth/getId/{id}`

**Response:**
```
{
  "name": "John",
  "email": "john@gmail.com",
  "phoneNumber": 12345678,
  "role": "USER"
}
```

---

### 3. Get All Users

**GET** `/api/auth/all-users?page=0&size=10`

---

### 4. Update User

**PUT** `/api/auth/update/{id}`

---

### 5. Delete User

**DELETE** `/api/auth/delete/{id}`

---

## Validation

| Field       | Constraint | Error Message               |
| ----------- | ---------- | --------------------------- |
| name        | NotEmpty   | Name cannot be empty        |
| email       | Email      | Email format is incorrect   |
| phoneNumber | NotNull    | Phone number cannot be null |

---

## Exception Handling

Custom exceptions:

* AlreadyExistsException
* NotFoundException
* ControllerException

**Example error response:**
```
{
  "message": "email: Email format is incorrect;",
  "status": "BAD_REQUEST"
}
```

---

## Database Model

**User Entity**

| Field       | Type   |
| ----------- | ------ |
| id          | Long   |
| name        | String |
| email       | String |
| phoneNumber | Long   |
| role        | ENUM   |

---

## Running with Docker

### Docker Compose
```
version: '3.8'
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
      POSTGRES_DB: users_db
    ports:
      - "5432:5432"

  app:
    build: .
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/users_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: root
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
    ports:
      - "8080:8080"
    depends_on:
      - db
```

### Dockerfile
```
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -e -DskipTests package

FROM eclipse-temurin:17-jdk-ubi9-minimal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

### Run
```
docker-compose up --build
```

---

## Local Run
```
mvn spring-boot:run
```

---

## Environment Variables
```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/users_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=root
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
```

---

## Notes

This project is suitable for extending with JWT authentication, authorization, and role-based access control.
