
# 🍎 Fruits API - (MySQL & Docker)

## 📝 Description

This project is an evolution of the Fruit Management API. In this level, we have migrated from an H2 in-memory database to a production-ready **MySQL 8.0** persistence layer. The entire environment is **Dockerized** to ensure consistent behavior across different development machines. We have also introduced **Providers** management and a relational data model.

---

## 🚀 Tech Stack

* **Java 17** & **Spring Boot 3.x**
* **Spring Data JPA**
* **MySQL 8.0** (Persistent Storage)
* **Docker & Docker Compose** (Containerization)
* **Maven** (Dependency Management)
* **Lombok** (Boilerplate reduction)

---

## 🏗️ Architecture & Database

The API implements a **One-to-Many** relationship:

* A **Provider** can supply multiple fruits.
* A **Fruit** is linked to exactly one provider.

### 🐳 Docker Configuration

The ecosystem is managed via **Docker Compose** with two main services:

1. `db`: MySQL Container (Exposed on local port `3308`).
2. `app`: Spring Boot API Container (Exposed on local port `8080`).

---

## 🛠️ Installation & Setup

1. **Clone the repository:**
```bash
git clone https://github.com/Rafadicandia/Tasca-S4.02-Api-Rest-amb-Spring-boot/tree/main/fruit-api-MySQL

```


2. **Launch the environment (Docker):**
   Ensure Docker Desktop is running and execute:
```bash
docker compose up -d

```


*The API will be reachable at `http://localhost:8080` and the Database at port `3308`.*

---

## 📖 User Stories (Acceptance Criteria)

### 1. Provider Management

* **Register:** `POST /providers`. Validates that the name is not empty and not duplicated (HTTP 409).
* **Update:** `PUT /providers/{id}`. Allows updating name and country (HTTP 200).
* **Delete:** `DELETE /providers/{id}`. **Restriction:** Cannot delete if the provider has associated fruits (HTTP 409).

### 2. Fruit Management

* **Add with Provider:** `POST /fruits`. Requires a valid `providerId` (HTTP 404 if not found).
* **Filter:** `GET /providers/{id}/fruits`. Retrieves all fruits supplied by a specific provider.

---

## 🛣️ Main Endpoints

### Providers

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/providers` | Register a new provider |
| `GET` | `/providers` | List all providers |
| `PUT` | `/providers/{id}` | Update provider details |
| `DELETE` | `/providers/{id}` | Delete a provider (if fruit-free) |
| `GET` | `/providers/{id}/fruits` | Get all fruits from a specific provider |

### Fruits

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/fruits` | Create a fruit linked to a provider |
| `GET` | `/fruits/{id}` | Get fruit details including provider info |
| `DELETE` | `/fruits/{id}` | Remove a fruit from stock |

---

## 🧪 Persistence Testing

To verify that data survives a container restart:

1. Populate data using Postman.
2. Run `docker compose down`.
3. Run `docker compose up -d`.
4. Verify via **MySQL Workbench** (Port 3308) or Postman that the data remains intact thanks to **Docker Volumes**.

---

## 📁 Project Structure

```text
src/main/java/cat/itacademy/s04/t02/n02/fruit/
├── fruit/         # Fruit logic (Controller, Service, Entity)
├── provider/      # Provider logic (Controller, Service, Entity)
├── exception/     # GlobalExceptionHandler and Custom Exceptions


```
📝 License
Distributed under the MIT License.

🤝 Contributions
Contributions are welcome! Please open an issue for bug reports or submit a Pull Request with your improvements.