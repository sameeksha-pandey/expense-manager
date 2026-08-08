# Expense Manager

A full-stack expense management application built using React, Spring Boot, and PostgreSQL.

The application allows users to add expenses manually or upload expenses through a CSV file. Expenses are automatically categorized using predefined vendor-based rules, while unusually high expenses are detected using a rule-based anomaly detection mechanism. A dashboard provides spending summaries, vendor analysis, category-wise spending, charts, and anomaly information.

## Features

- Manual expense entry
- CSV expense upload
- Rule-based vendor categorization
- Rule-based anomaly detection
- Monthly spending analysis by category
- Top 5 vendors by spending
- Anomalous expense detection
- Interactive spending charts
- Automatic dashboard refresh after adding or uploading expenses
- PostgreSQL database for persistent storage
- REST APIs using Spring Boot

## Technologies Used

### Frontend

- React
- JavaScript
- Vite
- Axios
- Recharts
- CSS

### Backend

- Java
- Spring Boot
- Spring Data JPA
- Maven
- Apache Commons CSV

### Database

- PostgreSQL
- pgAdmin 4

## Application Architecture

```text
React Frontend
      |
      | Axios / REST API
      v
Spring Boot Backend
      |
      +----------------------+
      |                      |
      v                      v
Controller Layer       Service Layer
                             |
                             v
                      Repository Layer
                             |
                             v
                         PostgreSQL

The backend follows a layered architecture:

Controller Layer – handles HTTP requests and API responses
DTO Layer – handles API request and response data
Service Layer – contains business logic
Repository Layer – handles database operations using Spring Data JPA
Entity Layer – represents the database model
Mapper Layer – converts between entities and DTOs

## Project Structure

expense-manager/
│
├── backend/
│   ├── .mvn/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── services/
│   ├── package.json
│   └── vite.config.js
│
├── .gitignore
└── README.md

## Setup Instructions

#Prerequisites: 

Install the following before running the application:

Java JDK
PostgreSQL
pgAdmin 4
Node.js
npm
Git

1. Clone the Repository
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd expense-manager

2. Create the PostgreSQL Database

Open PostgreSQL using pgAdmin 4 and create a database named:expense-manager

3. Configure the Backend Database

Open:

backend/src/main/resources/application.properties

Configure the PostgreSQL connection using your local PostgreSQL credentials.

Example:

spring.datasource.url=jdbc:postgresql://localhost:5432/expense-manager
spring.datasource.username=YOUR_POSTGRES_USERNAME
spring.datasource.password=YOUR_POSTGRES_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Start the Backend

Open a terminal inside the backend directory:

cd backend
.\mvnw spring-boot:run

The backend runs on:

http://localhost:8081

Hibernate/JPA automatically creates or updates the required database table according to the configured entity model.

5. Install Frontend Dependencies

Open another terminal:

cd frontend
npm install

6. Start the Frontend

Run:

npm run dev

Vite will display the frontend URL in the terminal.

During development, the application was run on:

http://localhost:5174


## Rule-Based Categorization

Expenses are categorized using predefined vendor-based rules in the backend.

Examples include:

Swiggy       → Food
Zomato       → Food
Amazon       → Shopping
Uber         → Travel
Netflix      → Entertainment

If a vendor does not match any predefined rule, it is assigned to:

Others

Categorization is implemented in the backend so that both manually entered expenses and CSV-uploaded expenses use the same categorization logic.

Anomaly Detection

The application uses a rule-based approach to identify unusually large expenses.

For each expense category, the backend calculates the average expense amount.

An expense is marked as an anomaly when:

Expense Amount > 3 × Category Average

For example, if the average Food expense is ₹3,000, an expense greater than ₹9,000 is considered anomalous.

The anomaly result is stored with the expense in PostgreSQL and displayed on the dashboard.

CSV Upload

The application supports uploading multiple expenses using a CSV file.

Expected CSV format:

date,amount,vendorName,description
2026-08-08,300,Swiggy,Snacks
2026-08-08,900,Amazon,Headphones
2026-08-08,400,Uber,Cab ride

The CSV processing flow is:

CSV File
   |
   v
React Frontend
   |
   v
Spring Boot REST API
   |
   v
CSV Parser
   |
   v
Vendor Categorization
   |
   v
Anomaly Detection
   |
   v
PostgreSQL

The same categorization and anomaly detection rules are applied to CSV-uploaded expenses as manually entered expenses.

Dashboard

The dashboard provides:

Summary Information
Total spending
Number of top vendors
Number of categories
Number of anomalies
Visualizations
Spending by category
Top 5 vendors by spending
Tables
Top 5 vendors
Monthly category spending
Anomalous expenses

The dashboard automatically refreshes after a successful manual expense entry or CSV upload.

Assumptions
Vendor categorization is based on predefined vendor-to-category rules.
Vendors that do not match a predefined rule are categorized as Others.
Anomaly detection uses a threshold of three times the average expense amount within the corresponding category.
Valid dates and numeric amounts are expected in the CSV input.
PostgreSQL is assumed to be running locally during development.
The application is designed for a single local user and does not currently include authentication or authorization.
Duplicate CSV records are not automatically removed. Uploading the same CSV multiple times can therefore create duplicate expenses.
The application uses deterministic rule-based categorization rather than machine-learning-based categorization.
New vendors need to be added to the vendor mapping rules if they should belong to a specific category.

## Design note

1.Vendor categorization is implemented using predefined vendor-to-category rules in the backend.

2.This keeps categorization deterministic and ensures that manual and CSV-uploaded expenses follow the same rules.

3.Anomaly detection is based on the average expense amount within each category.

4.An expense is marked as anomalous when its amount exceeds three times the category average.

5.The anomaly result is stored with the expense so it can be queried directly by the dashboard.

6.The data model separates database entities from API request and response models using DTOs.

7.Spring Data JPA is used for persistence and category-level aggregate queries.

8.A trade-off of the rule-based approach is that new vendors require the vendor mapping to be updated manually.