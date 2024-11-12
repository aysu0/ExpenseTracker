# Expense Tracker

A basic RESTful expense tracker application built with Spring Boot. This application allows users to manage budgets and track expenses by category, with the ability to set monthly budgets and check if expenses exceed set limits.

## Features

- Creating Budgets
  - Create a new budget
  - Assign user to budget
  - Create budget for specific category, month, and year
- Creating Expenses 
  - Create new expense
  - Associate expense with a user
  - Check if expense exceeds the budget
- View and Manage Budgets and Expenses
  - View all budgets or expenses for a user
  - View a specific budget or expense by ID
  - Update a budget or expense
  - Delete budgets or expenses
- Enforcing Budget Limits

## Technologies Used

- **Spring Boot** - Core framework for building the REST API
- **Hibernate** - ORM framework to interact with the PostgreSQL database
- **PostgreSQL** - Relational database to store users, budgets, and expenses
- **Maven** - Dependency management
- **Postman** - API platform


## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java JDK 11 or higher
- Maven
- PostgreSQL

### Setup and Configuration

1. **Clone the Repository**
    ```bash
    git clone https://github.com/aysu0/ExpenseTracker.git
    cd expense-tracker
    ```

2. **Configure PostgreSQL Database**

   Create a PostgreSQL database and configure your `application.properties` file with your database credentials.

   ```properties
   # src/main/resources/application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

3. **Run the Application**

Use Maven to build and run the application:

bash
Copy code
mvn spring-boot:run


4. **Access the API**

The API will be accessible at http://localhost:8080.



API Endpoints

User Management
Create User: POST /users
Get All Users: GET /users
Get User by ID: GET /users/{id}
Update User: PUT /users/{id}
Delete User: DELETE /users/{id}


Budget Management
Create Budget: POST /{userId}/budgets
Get All Budgets for User: GET /{userId}/budgets
Get Budget by ID: GET /{userId}/budgets/{id}
Update Budget: PUT /{userId}/budgets/{id}
Delete Budget: DELETE /{userId}/budgets/{id}


Expense Management
Create Expense: POST /{userId}/expenses
Get All Expenses for User: GET /{userId}/expenses
Get Expense by ID: GET /{userId}/expenses/{id}
Update Expense: PUT /{userId}/expenses/{id}
Delete Expense: DELETE /{userId}/expenses/{id}


Sample Data
Sample JSON data for testing the API.


User

{
  "username": "john_doe",
  "password": "securepassword",
  "email": "john@example.com"
}

Budget

{
  "category": "Food",
  "amount": 500,
  "month": 11,
  "year": 2023
}

Expense

{
  "amount": 50,
  "category": "Food",
  "description": "Groceries",
  "month": 11,
  "year": 2023
}

