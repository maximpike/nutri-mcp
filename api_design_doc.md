# NutriMC API Design Document

## Base URL
```
Production: https://api.nutrimc.com
Testing: https://test-api.nutrimc.com
Local: http://localhost:8080
```

## API Versioning
All endpoints are prefixed with `/api/v1`

## Authentication
- **OAuth 2.0** via Google Sign-In
- **JWT tokens** for subsequent API requests
- Header: `Authorization: Bearer <token>`

---

## API Endpoints (High-Level)

### Authentication
```
POST   /api/v1/auth/google          # Google OAuth login
POST   /api/v1/auth/logout          # Logout user
GET    /api/v1/auth/me              # Get current user info
```

### User Management
```
GET    /api/v1/users/{id}           # Get user profile
PUT    /api/v1/users/{id}           # Update user profile
DELETE /api/v1/users/{id}           # Delete user account
```

### Dietary Profile
```
GET    /api/v1/dietary-profile      # Get user's dietary profile
POST   /api/v1/dietary-profile      # Create dietary profile
PUT    /api/v1/dietary-profile/{id} # Update dietary profile
```

### Meal Plans
```
GET    /api/v1/meal-plans           # List user's meal plans
POST   /api/v1/meal-plans           # Create new meal plan
GET    /api/v1/meal-plans/{id}      # Get meal plan details
PUT    /api/v1/meal-plans/{id}      # Update meal plan
DELETE /api/v1/meal-plans/{id}      # Delete meal plan
POST   /api/v1/meal-plans/{id}/generate # AI-generate meals via MCP
```

### Meals
```
GET    /api/v1/meals                # Search meals
POST   /api/v1/meals                # Create custom meal
GET    /api/v1/meals/{id}           # Get meal details
PUT    /api/v1/meals/{id}           # Update meal
DELETE /api/v1/meals/{id}           # Delete meal
```

### Recipes
```
GET    /api/v1/recipes              # Search recipes
POST   /api/v1/recipes              # Create recipe
GET    /api/v1/recipes/{id}         # Get recipe details
PUT    /api/v1/recipes/{id}         # Update recipe
DELETE /api/v1/recipes/{id}         # Delete recipe
GET    /api/v1/recipes/{id}/nutrition # Calculate total nutrition
```

### Ingredients
```
GET    /api/v1/ingredients          # Search ingredients
POST   /api/v1/ingredients          # Add ingredient
GET    /api/v1/ingredients/{id}     # Get ingredient details
GET    /api/v1/ingredients/{id}/nutrition # Get nutrition info
```

### MCP Integration
```
POST   /api/v1/mcp/search-food      # Search USDA FoodData API
POST   /api/v1/mcp/get-nutrition    # Get nutrition for food item
POST   /api/v1/mcp/suggest-meals    # AI meal suggestions
```

---

## Standard Response Format

### Success Response
```json
{
  "status": "success",
  "data": { },
  "timestamp": "2025-10-06T10:30:00Z"
}
```

### Error Response
```json
{
  "status": "error",
  "message": "User not found",
  "code": "USER_NOT_FOUND",
  "timestamp": "2025-10-06T10:30:00Z"
}
```

---

## Common Query Parameters
- `page`: Page number (default: 0)
- `size`: Page size (default: 20)
- `sort`: Sort field and direction (e.g., `createdAt,desc`)
- `search`: Search term

---

## HTTP Status Codes
- `200 OK`: Successful GET/PUT
- `201 Created`: Successful POST
- `204 No Content`: Successful DELETE
- `400 Bad Request`: Validation error
- `401 Unauthorized`: Authentication required
- `403 Forbidden`: Insufficient permissions
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

---

## Detailed endpoint specifications will be added as we implement each controller.