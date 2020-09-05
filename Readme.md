## Restful API by Spring Boot + PostgreSQL + JPA + Docker

## How to run?

Requires Docker + Maven

```
$ git clone https://github.com/A1tairai/xpmanagement.git
$ cd xpmanagement
$ mvn clean package
$ docker-compose up -d
```

## How to test?

Import Postman Collection named XP Management.postman_collection.json

## Postgre Database Configuration

Edit database.env and change the database name, username and password

Edit src/main/resources/application.properties apply the same set up as database.env