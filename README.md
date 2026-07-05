# DynamikaLibrary
Java 1.8, Spring Boot 2.7.18, Spring Web (Thymeleaf), PostgreSQL 14, Maven

## Compile
mvn clean package -DskipTests
## Quick start
docker-compose up (-d)
java -jar target/dynamikalibrary-1.0.0.jar

## Endpoints
base: http://localhost:8080
get json w/active borrowings: http://localhost:8080/borrowings/active
books: http://localhost:8080/books (homepage)
clients: http://localhost:8080/clients
borrowings: http://localhost:8080/borrowings
/add /edit/{id} /delete/{id} on all of them (except no edit on borrowings)

all tables are created automatically, all data in GetAll methods is paged