# CLAUDE.md — NutriMC Project Context

## Project Overview
NutriMC is an intelligent meal planning assistant. Portfolio project showcasing Java 21,
Spring Boot 3.2, PostgreSQL, Flyway, Terraform, and Ansible.

## Tech Stack
- Java 21, Spring Boot 3.2, Maven
- PostgreSQL 16 (Docker Compose for local dev)
- Flyway for database migrations
- Testcontainers for integration tests
- GitHub Actions CI/CD with GitFlow branching

## Architecture
Three-tier: Controller → Service → Repository
- Controllers: HTTP handling, request validation, enum parsing
- Services: Business logic, constructor injection
- Repositories: Spring Data JPA interfaces

## Package Structure
com.maximpike.nutrimc/
├── config/        # Spring configuration
├── controller/    # REST endpoints
├── service/       # Business logic
├── repository/    # Data access
├── entity/        # JPA entities (BaseEntity, User, DietaryProfile)
│   └── enums/     # DietType, PlanStatus, MealType
├── dto/           # Data Transfer Objects
├── exception/     # Custom exceptions
└── util/          # Utilities

## Current State (Update this as you progress)
### Completed
- BaseEntity with audit fields (id, createdAt)
- User entity + UserRepository + UserService (6 unit tests, integration tests)
- DietaryProfile entity + Repository + Service (7 unit tests, integration tests)
- DietType enum (OMNIVORE, VEGETARIAN, VEGAN, PESCATARIAN, PALEO, KETO, GLUTEN_FREE)
- H2 → PostgreSQL migration with Flyway
- Docker Compose (PostgreSQL 16 + pgAdmin)
- Testcontainers for integration tests
- CI/CD: feature-ci, dev-ci, main-ci, deploy-production workflows

### In Progress
- OneToMany relationship refactor (User → DietaryProfiles)
- Frontend evaluation (React vs Vue.js)

### Roadmap
- REST API controllers with DTOs
- MealPlan + PlannedMeal entities and services
- Google OAuth 2.0 authentication
- Frontend (web)
- Terraform AWS deployment (EC2, RDS, ALB)
- Ansible configuration management
- MCP integration for USDA FoodData Central API

## Development Commands
```bash
# Start local database
docker compose up -d

# Run the app
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run with pgAdmin
docker compose --profile tools up -d
```

## Code Conventions
- Constructor injection (no field @Autowired)
- Enum over String for fixed value sets
- @Enumerated(EnumType.STRING) not ORDINAL
- Test naming: shouldDoSomethingWhenConditionMet()
- Given-When-Then test structure
- Conventional commits: feat:, fix:, test:, ci:, etc.
- Flyway migration naming: V{n}__{description}.sql

## Database
- Flyway manages schema (hibernate ddl-auto: validate)
- Migrations in: src/main/resources/db/migration/
- Current migration: V1__initial_schema.sql

## Key Design Decisions
- DietType parsing happens at Controller layer, not Service layer
- JSON columns for flexible lists (allergies, preferences) — avoid over-normalisation
- BaseEntity abstract class for shared audit fields
- Testcontainers over H2 to ensure test/prod database parity