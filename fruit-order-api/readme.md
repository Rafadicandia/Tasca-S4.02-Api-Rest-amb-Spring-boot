
# 📦 Fruit Order API - MongoDB Edition

**RESTful API for managing fruit orders using Spring Boot 3 and MongoDB with embedded document patterns.**

**Level 3** | Spring Boot 3.x + MongoDB + Docker | Integration Testing | Swagger UI

---

## 🎯 Overview

This API manages fruit orders with strict business rules. Unlike traditional relational databases, this implementation uses **MongoDB** to store orders as single documents with embedded items, optimizing for read performance and atomic updates.

### Core Business Rules:

* **Client Name:** Mandatory and non-blank.
* **Delivery Date:** Must be a future date (validated via `@Future`).
* **Items:** Each order must contain at least one item with a positive quantity.

---

## 🌐 Endpoints

| Method | Endpoint | Description | Status |
| --- | --- | --- | --- |
| **POST** | `/orders` | Create a new order | `201 Created` |
| **GET** | `/orders` | Retrieve all orders | `200 OK` |
| **GET** | `/orders/{id}` | Get order by unique ID | `200 OK` / `404` |
| **PUT** | `/orders/{id}` | Update an existing order | `200 OK` / `404` |
| **DELETE** | `/orders/{id}` | Remove an order | `204 No Content` |
| **GET** | `/swagger-ui.html` | Interactive Documentation | `200 OK` |

---

## 📝 Data Model

```json
{
  "id": "6783f6f0f0000000000000001",
  "clientName": "Fulano",
  "deliveryDate": "2026-02-15",
  "items": [
    {
      "fruitName": "Apple",
      "quantityInKilos": 10
    }
  ]
}

```

---

## 🚀 Quick Start (Docker Compose)

The fastest way to deploy the API and the Database together.

```bash
# 1. Build and start services in the background
docker compose up --build -d

# 2. Check logs to confirm connection to MongoDB
docker compose logs -f fruit-app

# 3. Stop and remove containers
docker compose down

```

**Access Points:**

* **API:** `http://localhost:8080`
* **Swagger UI:** `http://localhost:8080/swagger-ui.html`
* **MongoDB:** `localhost:27017`

---

## 🧪 Testing Strategy

We utilized **Testcontainers** to ensure the API works with a real database instance rather than a mock.

* **Integration Tests:** Verify the full flow from Controller to MongoDB.
* **Isolation:** Each test runs against a fresh, disposable MongoDB container.
* **Reliability:** Guarantees that MongoDB-specific features (like ObjectIDs and embedded updates) behave as expected.

```bash
# Run all automated tests
mvn test

```

---


## 🏗️ Project Architecture

```text
src/main/java/.../fruit/
├── controllers/   # REST Endpoints
├── model/         # MongoDB Documents (Order, OrderItem)
├── repository/    # Spring Data MongoDB Repositories
├── services/      # Business Logic & CRUD implementation
└── exception/     # Global Error Handling

```

---

## 🔧 Technologies

* **Java 21**
* **Spring Boot 3.5.x**
* **Spring Data MongoDB**
* **Docker & Docker Compose**
* **SpringDoc OpenAPI (Swagger)**
* **Testcontainers**
* **Maven**

---

## 📘 API Usage Examples

### Create Order (POST)

```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "clientName": "Rafael",
    "deliveryDate": "2026-03-01",
    "items": [{"fruitName": "Banana", "quantityInKilos": 5}]
  }'

```

### Update Order (PUT)

```bash
curl -X PUT http://localhost:8080/orders/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "clientName": "Rafael Updated",
    "deliveryDate": "2026-03-05",
    "items": [{"fruitName": "Orange", "quantityInKilos": 12}]
  }'

```

---
