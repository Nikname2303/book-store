# Book Shop Web Application

## Introduction

Welcome to the Book Shop Web Application! This project was inspired by the need for a comprehensive platform that allows users to browse, purchase, and manage books online. It aims to provide a seamless experience for both users and administrators. Users can browse books by categories, create shopping carts, and place orders, while administrators have the ability to manage the book inventory and categories.

## Technologies and Tools Used

This project leverages a range of modern technologies and tools to ensure robust performance and security:

- **Spring Boot**: For building the backend of the application.
- **Spring Security**: To handle authentication and authorization.
- **Spring Data JPA**: For database operations.
- **Swagger**: To document and test the APIs.
- **Liquibase**: For database version control.
- **Docker**: To containerize the application for easy deployment.
- **MapStruct**: For object mapping.

## Features

### User Functionalities
- **Create Shopping Carts**: Users can create and manage their shopping carts.
- **Place Orders**: Users can place orders based on the items in their shopping carts.
- **View Orders**: Users can view their own orders to track their purchases.

### Admin Functionalities
- Admins can create, update and delete books, categories and update orders

## Setup Instructions

To set up the project locally, follow these steps:

### Prerequisites

Ensure you have the following software installed:

- **Java 21**
- **Docker Version 4.30.0 (149282)**
- **Maven 3.9.5**

### Database Configuration

You can use either MySQL or H2 database. For simplicity, the following instructions will use H2. If you prefer MySQL, ensure you update the necessary configuration in `application.properties`.

### Environment Variables

Create a .env file in the root directory of your project with the following content:

- MYSQL_ROOT_PASSWORD=password
- MYSQL_DATABASE=example_database
- MYSQL_LOCAL_PORT=3305
- MYSQL_DOCKER_PORT=3306

- SPRING_LOCAL_PORT=8088
- SPRING_DOCKER_PORT=8080
- DEBUG_PORT=5005
- EXPIRATION_TIME=300000
- SECRET_KEY=IOFuh38wfnvo(&FH&$Yf3877hg9j0w9kef9r8g2hjfrg89vth92nr

### Building and Running the Project

1. **Clone the repository**:
    ```bash
    git clone https://github.com/Nikname2303/book-store
    cd book-store
    ```

2. **Build the project**:
    ```bash
    mvn clean package
    ```

3. **Build and start Docker containers**:
    ```bash
    docker-compose build
    docker-compose up
    ```

### Accessing the Application

- **Web Application**: Open your browser and go to `http://localhost:8088` to access the web application.
- **Swagger UI**: The API documentation is available at `http://localhost:8088/swagger-ui/index.html`.
### Authentication

The application uses basic authentication and JWT for secured access. Use the following credentials to log in as an admin:

    Login: admin@example.com
    Password: 12345678

### Testing the Setup

After starting the application, you can verify the setup by accessing the Swagger UI link. Ensure that the web application is running and the APIs are accessible.

## Postman Collection
To facilitate API testing, a Postman collection is provided. You can import this collection into Postman to test the various endpoints.

    Postman Collection Link: https://api.postman.com/collections/34471690-00770ea7-4571-4d5e-bcc0-a6ba67dd2d63?access_key=PMAT-01HZ1ZPY4PMYECB8XWVC0Z78X7

The collection includes pre-configured requests for all available endpoints, making it easier to test the application's functionalities.

## Challenges and Solutions

### MapStruct
One of the challenges faced during the development was dealing with unexpected behaviors of MapStruct's default methods. This was resolved by using annotations such as `@Mapping`, `@Named`, and `@AfterMapping` to ensure precise control over the mapping process.

### Spring Data JPA
Another challenge was fetching the necessary data efficiently. This was handled by using the `@EntityGraph` annotation for loading related entities and `@Query` annotation for custom SQL queries when needed.

## Conclusion.
This project demonstrates a robust web application for managing a bookstore, demonstrating the effective use of various Spring technologies and modern development tools. Whether you are a user looking to purchase books or an administrator managing inventory, this application offers a comprehensive solution for all your needs.

Feel free to explore the code and contribute to the project. If you have any questions or suggestions, please open a thread or contact me)

### Authors
