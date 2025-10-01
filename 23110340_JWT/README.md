# 23110340_JWT
Java 22 + Spring Boot 3.2 + Spring Security 6 + JWT + SQL Server Authentication.

## Run
1. Update `spring.datasource.*` in `src/main/resources/application.properties` to match your SQL Server Auth.
2. `mvn spring-boot:run`

## API
- POST /api/auth/register {username,email,password,role}
- POST /api/auth/login {username,password} -> {token}
- GET /api/users/me Authorization: Bearer <token>
- GET /api/products
- POST /api/products (ROLE_ADMIN) Authorization: Bearer <token>

## Web
- /, /hello with form login
