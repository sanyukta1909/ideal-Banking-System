# Banking System Management
A full-stack banking application developed using Spring Boot for the backend and Angular for the frontend. It provides secure and efficient management of customers, accounts, and financial transactions through a RESTful architecture. The application demonstrates end-to-end integration, validation, and real-time user interaction.A full-stack banking application built using Spring Boot and Angular.

## Tech Stack

**Backend:** Spring Boot, JPA, MySQL 

**Frontend:** Angular,Bootstrap

**API:** REST

## Architecture
- Angular frontend consumes REST APIs from Spring Boot backend
- Backend handles validations, security, and business logic

## Modules
- Customer Management
- Account Management
- Transdaction Management

## How to Run
- Start backend
- Start frontend

## API Endpoints

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

## Prerequisites

Before running the application, ensure the following are installed:

- Node.js (v16 or above recommended)

- npm (comes with Node.js)

- Angular CLI

- Code editor (VS Code recommended)

## Setup Instructions

Install Angular CLI
```
npm install -g @angular/cli
```
Install Dependencies
```
Install required packages:
```
npm install
```
Run the Application

Start the Angular development server:
```
ng serve
```
Application runs on:
```
http://localhost:4200

## Example

### Deposit Money

## Request

POST /api/transactions/deposit

{
  "accountNumber": "ACC12345",
  "amount": 5000
}

## Setup Instructions (From ZIP File)

 Extract the ZIP File

- Import Project into IDE

- Using Eclipse / STS:

- File → Import → Existing Maven Project

- Update the MAVEN project 

- Select the extracted project folder

Create a database in MySQL:
```
CREATE DATABASE banking_db;
```
Update application.properties
```

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
Test all the below end point in Swggwer : add the swagger url 







