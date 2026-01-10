# Inventory Management System
A Java-based desktop application for managing inventory, customers, suppliers, and transactions. This application provides a graphical user interface for easy interaction.

## Features
Authentication: Secure login and signup for users.

Dashboard: A central hub for accessing different management panels.

Customer Management: Add, update, and view customer details.

Product Management: Manage product information, including adding, updating, and viewing products.

Supplier Management: Keep track of supplier information and the products they supply.

Transaction Management: Create and view transactions and generate invoices.

## Prerequisites
Before you begin, ensure you have the following installed:

- Java Development Kit (JDK): Version 21 or later.

- Git: For cloning the repository.

The project uses the Gradle wrapper, so you don't need to have Gradle installed separately.

## Installation & Setup
Clone the repository:

```
git clone https://github.com/vickrant-dev/java-oop-cw
cd java-oop-cw
```

Database Setup: This project uses a Supabase based PostgreSQL database.

Database is created in the cloud, but you could switch to local or cloud-based database systems and configure accordingly.

The database connection details are managed within the application. You may need to update the connection details in app/src/main/java/com/inventory/utils/DBConnection.java to match your database configuration.

## Running the Application
To run the application, execute the following command from the root directory of the project:

```
./gradlew run
```
This command will compile the source code and start the application.

## Running Tests
To run the tests, use the following command:

```
./gradlew test
```
This will execute all the tests in the app/src/test directory.

## Built With
- Java: Core programming language.
- Java Swing: For the graphical user interface.
- JDBC: For database connectivity.
- PostgreSQL(Supabase): Cloud-based Database Management System.
- Gradle: For dependency management and building the project.
- JUnit 5: For unit testing.
- Gson: For JSON processing.