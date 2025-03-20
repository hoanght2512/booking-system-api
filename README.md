# Booking System API
Restful API for booking system.

## Steps for setup
**1.** Clone the repository
```bash
git clone https://github.com/hoanght2512/booking-system-api
```

**2.** Create database

```sql
CREATE DATABASE booking_system;
```
- run `src/main/resources/db.sql`

**3. Change mysql username and password as per your installation**
+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**
```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## SWAGGER UI
[Click here to view Swagger UI](http://localhost:8080/swagger-ui/index.html)

## POSTMAN
[Click here to view Postman collection](https://web.postman.co/workspace/12036c92-77df-43b1-8b56-239e98723fd8/collection/42759995-c49c6c3e-9a91-479d-a688-20021c359b93?action=share&source=copy-link&creator=42759995)
(Coming soon)
