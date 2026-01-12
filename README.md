
# 📦 Spring Boot REST API Series - Fruit Management

This repository contains the progressive implementation of a Fruit Management REST API across three levels of complexity, as defined in the **IT Academy S4.02 Curriculum**. The project evolves from a local in-memory database to a fully containerized microservice with NoSQL persistence.

---

## 🛠️ Project Levels & Evolution

### **Level 1: Basic REST with H2 (In-Memory)**

* **Focus**: Fundamentals of Spring Boot and RESTful architecture.
* **Database**: H2 (In-memory) for rapid development and testing.
* **Key Features**: Basic CRUD operations using `JpaRepository`.

### **Level 2: SQL Persistence with MySQL**

* **Focus**: Moving towards production-like environments.
* **Database**: **MySQL** (External relational database).
* **Key Features**:
* Separation of concerns between Controller, Service, and Repository.
* Database persistence beyond application restarts.
* Integration with Docker for database management.

### **Level 3: NoSQL & Containerization (MongoDB)**

* **Focus**: High scalability and modern deployment workflows.
* **Database**: **MongoDB** (NoSQL Document Store).
* **Key Features**:
* **Embedded Documents**: Handling fruit items inside order documents.
* **Testcontainers**: Integration testing with real MongoDB instances.
* **Multi-stage Docker Builds**: Optimized images for production.
* **Swagger/OpenAPI**: Automated interactive documentation.



---

## 🚀 Installation & Setup

### 1. Prerequisites

* **Java 21 (LTS)**
* **Maven 3.8+**
* **Docker Desktop** (Required for Level 2 & 3)

### 2. Launching Level 3 (Latest)

Navigate to the Level 3 directory and execute:

```bash
docker compose up --build -d

```

* **API**: `http://localhost:8080`
* **Swagger UI**: `http://localhost:8080/swagger-ui.html`

---

## 📡 API Endpoints Summary

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/orders` | Create a new fruit order |
| `GET` | `/orders` | List all orders |
| `GET` | `/orders/{id}` | Find order by ID |
| `PUT` | `/orders/{id}` | Update existing order |
| `DELETE` | `/orders/{id}` | Remove an order |

---

## 🧪 Testing Philosophy

Throughout the levels, we applied different testing strategies:

1. **Unit Testing**: Verifying service logic with Mockito.
2. **Integration Testing**: Testing endpoints with `MockMvc`.
3. **Container-based Testing**: Using **Testcontainers** in Level 3 to ensure real-world database compatibility.

---

## ⚠️ Key Validations

* **Entity Integrity**: `@NotBlank` and `@NotNull` on essential fields.
* **Business Logic**: Custom logic to ensure delivery dates are always in the future (`@Future`).
* **Error Handling**: Global exception handler to return clean JSON error responses for `400 Bad Request` and `404 Not Found`.

---

## 📊 SQL vs NoSQL (Lessons Learned)

Developing across these levels highlighted the structural differences between **Relational (MySQL)** and **Non-Relational (MongoDB)** approaches:

* **MySQL**: Enforced schema, useful for complex relations and ACID compliance.
* **MongoDB**: High flexibility, better for horizontal scaling and storing nested objects (Fruit Items) as a single document.

