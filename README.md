# Kitchensink Application

This is a Spring Boot application for managing member registrations.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- MongoDB running on `localhost:27017`

## Building the Application

To build the application, run the following command:

```sh
mvn clean install
```

## Running the Application

To run the application, use the following command:
```sh
mvn spring-boot:run
```

The application will start and be accessible at http://localhost:8080.

## Running Tests
To run the tests, use the following command:

```sh
mvn test
```

## API Documentation

### Register a Member

**URL:** `/api/members`

**Method:** `POST`

**Request Body:**
```json
{
  "name": "Jane Doe",
  "email": "jane@mailinator.com",
  "phoneNumber": "2125551234"
}
```

**Response:**

```json
{ "status": "200 OK" }
