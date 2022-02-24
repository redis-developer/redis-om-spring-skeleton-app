# 🍀 Redis OM Spring Skeleton App

Redis OM Spring provides powerful repository and custom object-mapping abstractions built on top of the powerful Spring Data Redis (SDR) framework. This app is a quick teaser of an application using Redis OM Spring to map a Spring Data model
using a RedisJSON document.

### 🚀 Launch Redis

Redis OM Spring relies on the power of the [RediSearch][redisearch-url] and [RedisJSON][redis-json-url] modules.
We have provided a docker compose YAML file for you to quickly get started. To launch the docker compose application, on the command line (or via Docker Desktop), clone this repository and run (from the root folder):

```bash
docker compose up
```

## Prerequisites

* Java 11 or higher
* Spring Boot 2.6.3

## Run the App

```bash
./mvnw spring-boot:run
```

### Interact with the API

You can interact with the API either directly through the [Swagger interface](http://localhost:8080/swagger-ui/).