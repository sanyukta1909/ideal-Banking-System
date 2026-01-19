
# Banking System-Spring Boot Rest API

This project is a Spring Boot–based RESTful API designed for banking system. It provides backend services for managing customers, accounts, and transactions such as deposit, withdrawal, and fund transfer.





## Features

- Customer creation
- Account creation
- Transactions
- Deposit money
- withdraw money
- Transfer money
- Account balance
- Transaction History
- Logging
- Exception handling
- Spring security authentication


## Tech Stack

- Java 8+
- Spring Boot
- Spring Data JPA / Hibernate
-  RESTful APIs using JSON
- MySQL
- Maven
- Postman for API testing
- Basic exception handling and logging



## Project Structure

com.example.demo
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── exception
 └── Security

## API Reference

#### POST  

### Customer API
| Method | Endpoint | Description |
|------|---------|-------------|
| POST | /api/customers | Create customer |

### Account APIs
| Method | Endpoint | Description |
|------|---------|-------------|
| POST | /api/accounts | Create account |
| GET | /api/accounts/{accountNumber}/balance | Get account balance |


### Transaction APIs
| Method | Endpoint | Description |
|------|---------|-------------|
| POST | /api/transactions/deposit | Deposit money |
| POST | /api/transactions/withdraw | Withdraw money |
| POST | /api/transactions/transfer | Transfer funds |
| GET | /api/transactions/{accountNumber} | Transaction history |


## Example

### Deposit Money

Request
```json
POST /api/transactions/deposit
{
  "accountNumber": "ACC12345",
  "amount": 5000
}



## Exception Handling

### Handled Exceptions
- ResourceNotFoundException → 404 Not Found
- InvalidRequestException → 400 Bad Request
- InsufficientBalanceException → 400 Bad Request
- MethodArgumentNotValidException → 400 Validation Error
- NoHandlerFoundException → 404 Invalid Endpoint
- HttpRequestMethodNotSupportedException → 405 Method Not Allowed
- Generic Exception → 500 Internal Server Error

All error responses follow a consistent JSON format with timestamp and status.



## Prerequisites
Before running the project, ensure the following are installed on your system:

- Java JDK 8 or above

- Maven 3.x

- MySQL Workbench

- IDE (Eclipse / STS)

- Postman (for testing APIs)

Setup Instructions (From ZIP File)

```bash
Extract the ZIP File
```

Import Project into IDE

```bash
Using Eclipse / STS:

File → Import → Existing Maven Project

Select the extracted project folder
```

Create a database in MySQL:

```bash
  CREATE DATABASE banking_db;
```

Update application.properties

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=your_username
spring.datasource.password=your_password


spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.security.user.name=<configured-username>
spring.security.user.password=<configured-password>

```
## Build and Run the Application

Navigate to the project directory:

```
  cd SpringBootDummyBank
```
Build the project:

```
mvn clean install
```
Run the application:

```
 mvn spring-boot:run
```
Using IDE

Locate the main class annotated with @SpringBootApplication

Right-click → Run as → Spring Boot Application

Application Access

Application runs on: http://localhost:8080

APIs can be tested using Postman


## Authors

- Sanyukta Lakhe


## License

This project is for learning and demonstration purposes.

