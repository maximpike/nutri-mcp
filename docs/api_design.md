# NutriMC API Design Document

## Base URL


```
Production: https://api-nutrimc.com
Testing: https://test-api.nutrimc.com
Local: http://localhost:8080
```

## API versioning
All endpoints are prefixed with `/api/v1` -> why?

## Authentication
- **OAuth 2.0** via Google Sign-in
- **JWT tokens** for subsequent API requests
- Header: `Authorization: Bearer <token>`

---
## API Endpoints (High-Level)

### Authentication

### User Management

### Dietary Profile

### Meal Plans

### Meals

### Recipes

### Ingredients

### MCP Integration

---
## Standard Response Format

### Success Response

### Error Response

---
## Common Query Parameters

---

## HTTP Status Codes
