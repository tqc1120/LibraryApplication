# LibraryApp

LibraryApp is a Spring Boot application designed to manage a library system. It includes features for managing authors and books, and it provides a REST API to interact with the system.

## Table of Contents

- [Requirements](#requirements)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Exception Handling](#exception-handling)
- [Technologies Used](#technologies-used)

## Requirements

- Java 17
- Maven 3.6.0+
- PostgreSQL

## Setup

1. **DataBase Table Format:**

   Author table:

   | Column Name | Data Type                  | Constraints          |
   |-------------|----------------------------|----------------------|
   | author_id   | integer                    | [PK]                 |
   | name        | character varying (255)    |                      |
   Book table:

   | Column Name | Data Type                  | Constraints          |
   |-------------|----------------------------|----------------------|
   | book_id     | integer                    | [PK]                 |
   | title       | character varying (255)    |                      |
   BookAuthor table: 

   | Column Name | Data Type                  | Constraints          |
   |-------------|----------------------------|----------------------|
   | book_id     | integer                    |                      |
   | author_id   | integer                    |                      |
2. Configure the Database

   Update the `application.properties` file with your PostgreSQL database configurations:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:default_port/librarydb
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    ```

## API Endpoints Examples

### Get Author by ID

**Endpoint:** `/authors?id={id}`

**Method:** `GET`

**Description:** Returns the details of an author by their ID.

**Parameters:**
- `id` (required): The ID of the author.

**Responses:**
- `200 OK`: Successfully found the author. Returns the `AuthorDto` object.
- `404 Not Found`: Author not found.

**Example Request:**
   ```bash
   curl -X GET "http://localhost:port/authors?id=1"
   ```

### Get Authors by Name

**Endpoint:** `/authors?name={name}`

**Method:** `GET`

**Description:** Returns a list of authors that match the given name.

**Parameters:**
- `name` (required): The name of the authors to search for.

**Responses:**
- `200 OK`: Successfully found authors. Returns a list of AuthorDto objects.

**Example Request:**
   ```bash
   curl -X GET GET "http://localhost:port/authors?name=John"
   ```

### Create a New Author

**Endpoint:** `/authors`

**Method:** `POST`

**Description:** Creates a new author and returns the details of the created author.

**Request Body:**
- `AuthorDto` (required): The details of the author to be created.

**Responses:**
- `200 OK`: Successfully created the author. Returns the AuthorDto object of the created author.

**Example Request:**
   ```bash
   curl -X POST "http://localhost:port/authors" -H "Content-Type: application/json" -d '{"name":"New Author","books":["Book1","Book2"]}'
   ```

### Update Author Name

**Endpoint:** `/authors?id={id}`

**Method:** `PUT`

**Description:** Updates the name of an existing author by their ID.

**Parameters:**
- `id` (required): The ID of the author to be updated.
- `newName` (required in body): The new name for the author.

**Responses:**
- `200 OK`: Successfully updated the author. Returns the AuthorDto object of the updated author.

**Example Request:**
   ```bash
   curl -X PUT "http://localhost:port/authors?id=1" -H "Content-Type: application/json" -d '"Updated Name"'
   ```

## Testing
### Unit Tests

Unit tests are located in the src/test/java/com/example/LibraryApp/service directory. To run the tests, use the following command:
      ```bash
      mvn test
      ```
    
## API Documentation
API documentation is provided using Springdoc OpenAPI. To access the API documentation, run the application and navigate to:
   ```bash
   http://localhost:port/swagger-ui.html
   ```
To enable API documentation, include the following dependency in your pom.xml:
   ```bash
   <dependency>
       <groupId>org.springdoc</groupId>
       <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
       <version>2.2.0</version>
   </dependency>
   ```
Add API documentation annotations to your controller classes using @Tag, @Operation, and @ApiResponses as necessary.

## Exception Handling
Global exception handling is implemented using @ControllerAdvice and custom exception classes.

Define custom exceptions like ResourceNotFoundException and DuplicateResourceException.

Use @ExceptionHandler methods in a GlobalExceptionHandler class to handle these exceptions globally.

## Technologies Used
- Spring Boot: Framework for building Java-based web applications.
- Spring Data JPA: Abstraction layer to work with relational databases.
- Springdoc OpenAPI: Library to automate the generation of API documentation.
- JUnit 5: Testing framework.
- Mockito: Framework for creating test doubles.
- PostgreSQL: Relational database management system.
