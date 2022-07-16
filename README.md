# Medical Record REST

## Overview

* Java 11
* Spring Boot 2.7.1
* Docker
* Dependency Management: Maven
* Unit Tests: JUnit 5 & Mockito
* Database: H2

### How to Build

```shell
mvn clean install -DskipTests
```

### How to up

```shell
docker-compose -f docker-compose.yml up -d
```

#### APIs:

| HTTP Method | Path                                    | Description                  |
|-------------|---------------------------------------- |------------------------------|
| POST        | `/api/v1/upload/`                       | Upload csv file in multipart |
| GET         | `/api/v1/fetch/{code}/`                 | Fetch medical record by code |
| GET         | `/api/v1/fetch/all/`                    | Fetch all medical records    |
| DELETE      | `/api/v1/delete/all/`                   | Delete all medical records   |