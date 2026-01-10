
# 🍎 Fruit API (H2)

A robust RESTful API for managing fruit inventory, built with **Spring Boot** and an in-memory **H2 database**. This project implements high-standard architectural patterns and was developed following **Test-Driven Development (TDD)**.

---

## 🌟 Features

* **Standard CRUD Endpoints**: Full lifecycle management for fruit resources.
* **TDD Approach**: Developed using the Red-Green-Refactor cycle with **JUnit 5** and **Mockito**.
* **DTO Pattern**: Separation between Database Entities and API Contracts for enhanced security and maintenance.
* **Global Exception Handling**: Centralized error management providing meaningful JSON responses and correct HTTP status codes.
* **Bean Validation**: Strict input validation using `@NotBlank` and `@Positive` annotations.
* **Docker Optimized**: Implementation of a **multi-stage Dockerfile** for production-ready images.

---

## ⚙️ Database Configuration

The application uses an **H2 In-Memory Database**, which means no external database installation is required. The configuration is located in `src/main/resources/application.properties`:

```properties
# Application Name
spring.application.name=fruit-api-h2

# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Database Connection Settings
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Show SQL queries in console (Optional for debugging)
spring.jpa.show-sql=true

```

---

## 🛠️ Installation & Setup

### Prerequisites

* **Java 21** or higher.
* **Maven 3.9+**.
* **Docker** (Optional).

### Steps

1. **Clone the repository**:
```bash
git clone https://github.com/Rafadicandia/Tasca-S4.02-Api-Rest-amb-Spring-boot/tree/main/fruit-api-h2
cd fruit-api-h2

```


2. **Build the project**:
```bash
mvn clean install

```



---

## 🚀 Getting Started & Testing

### 1. Running Locally

```bash
java -jar target/fruit-api-h2-0.0.1-SNAPSHOT.jar

```

### 2. Manual Verification (Postman)

1. **POST** a new fruit to `http://localhost:8080/fruits` with a JSON body:
```json
{ "name": "Apple", "weightInKilos": 2 }

```


2. **GET** all fruits from `http://localhost:8080/fruits`.

### 3. Database Inspection (H2 Console)

1. Open your browser at `http://localhost:8080/h2-console`.
2. Use **JDBC URL**: `jdbc:h2:mem:testdb` and **User**: `sa`.
3. Execute `SELECT * FROM FRUIT;` to see your persisted data.

---

## 🐳 Docker Deployment

This project uses a **multi-stage build** to keep the image size small and secure:

```bash
# Build the image
docker build -t fruit-api-h2 .

# Run the container
docker run -p 8080:8080 fruit-api-h2

```

---

## 📖 API Endpoints

| Method | Endpoint | Description | Status Code |
| --- | --- | --- | --- |
| **GET** | `/fruits` | List all fruits | `200 OK` |
| **GET** | `/fruits/{id}` | Get fruit by ID | `200 OK` / `404` |
| **POST** | `/fruits` | Create fruit | `201 Created` |
| **PUT** | `/fruits/{id}` | Update fruit | `200 OK` / `404` |
| **DELETE** | `/fruits/{id}` | Remove fruit | `204 No Content` |

---

## 📝 License

Distributed under the MIT License.

## 🤝 Contributions
Contributions are welcome! Please open an issue for bug reports or submit a Pull Request with your improvements.