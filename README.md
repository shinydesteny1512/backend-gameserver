# backend-gameserver
A Spring Boot project for a game backend. It contains CRUD operations for the game frontend.

## The service contains 
- Item Domain
- (Not Implemented yet) Player Domain
- (Not Implemented yet) Enemy Domain

## Used Technologies
- Atlas Mongo
- Spring Boot 4.0.7
- Java 21
- Maven Wrapper

## Local Development
Install JDK 21 and make sure `JAVA_HOME` points to it before running Maven.

Start a local MongoDB:

```shell
docker compose up -d
```

Run tests:

```shell
./mvnw test
```

Run the application with the local profile:

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

The local server listens on port `9071`.

## Atlas Mongo
The default profile uses Atlas MongoDB and expects these environment variables:

```shell
MONGO_USER=...
MONGO_PASSWORD=...
MONGO_DB=...
```
