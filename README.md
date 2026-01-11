# Order Project - Spring Boot with Kafka and MongoDB

A Spring Boot microservice application demonstrating integration with Apache Kafka and MongoDB.

## Prerequisites

- Java 21
- Docker and Docker Compose
- Maven

## Technologies Used

- **Spring Boot 4.0.1**
- **MongoDB** - NoSQL database for storing orders
- **Apache Kafka** - Message broker for event-driven architecture
- **Spring Data MongoDB** - Data access layer
- **Spring Kafka** - Kafka integration
- **Spring Web** - REST API
- **Spring Actuator** - Application monitoring

## Project Structure

```
src/main/java/com/order/orderproject/
├── OrderProjectApplication.java    # Main application class
├── controller/
│   └── OrderController.java        # REST API endpoints
├── model/
│   └── Order.java                  # Order entity/model
├── repository/
│   └── OrderRepository.java        # MongoDB repository
└── service/
    ├── OrderService.java           # Business logic
    ├── KafkaProducerService.java   # Kafka message producer
    └── KafkaConsumerService.java   # Kafka message consumer
```

## Getting Started

### 1. Start MongoDB and Kafka

```bash
docker-compose up -d
```

This will start:
- MongoDB on port 27017 (username: admin, password: password)
- Kafka on port 9092

### 2. Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on port 8080.

### 3. Verify Services are Running

Check application health:
```bash
curl http://localhost:8080/actuator/health
```

## API Endpoints

### Create Order
```bash
POST http://localhost:8080/api/orders
Content-Type: application/json

{
  "customerName": "John Doe",
  "product": "Laptop",
  "quantity": 2,
  "totalPrice": 2000.00
}
```

### Get All Orders
```bash
GET http://localhost:8080/api/orders
```

### Get Order by ID
```bash
GET http://localhost:8080/api/orders/{id}
```

### Get Orders by Customer
```bash
GET http://localhost:8080/api/orders/customer/{customerName}
```

### Get Orders by Status
```bash
GET http://localhost:8080/api/orders/status/{status}
```

### Update Order Status
```bash
PATCH http://localhost:8080/api/orders/{id}/status?status=COMPLETED
```

### Delete Order
```bash
DELETE http://localhost:8080/api/orders/{id}
```

## Kafka Integration

The application automatically sends messages to Kafka when:
- An order is created
- An order status is updated
- An order is deleted

The `KafkaConsumerService` listens to the "orders" topic and logs received messages.

## MongoDB Integration

Orders are stored in MongoDB in the `order_db` database under the `orders` collection.

## Configuration

All configuration can be found in `src/main/resources/application.properties`:
- MongoDB connection URI
- Kafka bootstrap servers
- Consumer group ID
- Serializers/Deserializers
- Server port
- Actuator endpoints

## Monitoring

Spring Boot Actuator provides the following endpoints:
- `/actuator/health` - Application health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics

## Stopping the Services

```bash
docker-compose down
```

## Next Steps

Consider adding:
- DTO classes with validation
- Exception handling with @ControllerAdvice
- JSON serialization for Kafka messages
- Integration tests
- API documentation with SpringDoc OpenAPI
- Logging configuration
- Security with Spring Security

