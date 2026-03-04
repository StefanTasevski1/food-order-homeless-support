# Food Order – Homeless Support

A **Spring Boot** web application for food ordering in homeless support: browse meals, filter by name/type/provider, and place orders. Admins manage meals (CRUD); users view and order. Built with the same stack and structure as a typical LMS-style project (Java 21, Spring Security, JPA, Thymeleaf, Docker).

---

## Features

- **Meal catalog** – List meals with pagination and filters (name, meal type, provider).
- **Roles** – **Admin**: add, edit, delete meals. **User**: view and place orders.
- **Run locally** (H2) or with **Docker** (PostgreSQL).

---

## Tech stack

| Layer        | Technology                    |
|-------------|-------------------------------|
| Language    | Java 21                       |
| Framework   | Spring Boot 3.2               |
| Security    | Spring Security (form login)  |
| Data        | Spring Data JPA, H2 / PostgreSQL |
| Templates   | Thymeleaf                     |
| Build       | Maven                         |
| Deployment  | Docker, Docker Compose        |

---

## Quick start

### Run with Docker (recommended)

```bash
docker compose up --build
```

Then open **http://localhost:8080**.

### Run locally (no Docker)

**Requirements:** Java 21, Maven.

```bash
mvn spring-boot:run
```

Then open **http://localhost:8080**. Database: H2 in-memory (console at `/h2` if enabled).

---

## Default logins

| Role  | Username | Password |
|-------|----------|----------|
| Admin | `admin`  | `admin`  |
| User  | `user`   | `user`   |

---

## Project structure

```
├── docker-compose.yml    # PostgreSQL + app
├── Dockerfile            # Multi-stage (Maven → JRE)
├── pom.xml
├── src/main/
│   ├── java/mk/netcetera/foodorder/
│   │   ├── FoodOrderApplication.java
│   │   ├── config/       # Security, DataInitializer, PasswordEncoder
│   │   ├── model/        # Meal, Provider, MealType
│   │   ├── repository/   # MealRepository, ProviderRepository
│   │   ├── service/      # MealService, ProviderService, filtering
│   │   └── web/          # MealController
│   └── resources/
│       ├── application.properties
│       └── templates/    # list, form, access_denied
└── README.md
```

---

## Pushing to GitHub

1. **Create a new repository** on [GitHub](https://github.com/new).
   - Name it (e.g. `food-order-homeless-support`).
   - **Do not** add a README, .gitignore, or license (this project already has them).
   - Create the repository.

2. **From your machine**, in the project folder:

   ```bash
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
   git branch -M main
   git push -u origin main
   ```

   Replace `YOUR_USERNAME` and `YOUR_REPO_NAME` with your GitHub username and repo name.

3. If prompted for a password, use a **Personal Access Token** (not your GitHub password).

---

## License

MIT License – see [LICENSE](LICENSE).
