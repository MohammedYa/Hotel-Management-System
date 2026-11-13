# 🏨 Hotel Management System

A Java-based console application designed to manage hotel operations, including rooms, customers, bookings, check-in/check-out, payments, and reports.

The project focuses on applying Object-Oriented Programming (OOP), layered architecture, separation of concerns, and reusable business logic to build a structured hotel management system.

**Repository:** [Hotel Management System](https://github.com/MohammedYa/Hotel-Management-System)

## 📌 Features

### 1. Room Management
- Add, update, search, and delete rooms.
- Display all rooms.
- Change room status: AVAILABLE, OCCUPIED, MAINTENANCE, and CLEANING.
- Manage room types, prices, and capacities.

### 2. Customer Management
- Register new customers.
- Search, update, and delete customer records.
- Display all customers.

### 3. Booking Management
- Create, search, update, and cancel bookings.
- Validate room and customer availability.
- Prevent overlapping bookings for the same room.
- Handle related bookings when a room or customer is deleted.

### 4. Check-in / Check-out Management
- Process guest check-in and check-out.
- Manage room and booking statuses.
- Apply payment requirements before check-in.

### 5. Payment Management
- Record and search payments.
- Calculate total payments per booking.
- Calculate remaining balances.
- Check minimum and full payment requirements.
- Support payment and refund types.

### 6. Reports
Generate reports for:
- Rooms by status, type, capacity, and price range.
- Customers and their booking history.
- Bookings by status, customer, room, and date range.
- Payments by booking, status, type, and date range.

## 🏗️ Project Architecture

The application follows a layered architecture to separate responsibilities and improve maintainability.

```text
Hotel Management System
│
├── Entities
├── Enums
├── Repositories
├── Services
├── Management
├── Reports
└── Validations
```

### Architecture Flow

```text
Management Layer
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
In-Memory Data Collections
```

- **Management Layer:** Handles console menus and user interaction.
- **Service Layer:** Implements business logic and coordinates operations.
- **Repository Layer:** Handles data storage and retrieval.
- **Entities:** Represent the main domain objects.
- **Validations:** Provides reusable validation utilities.

## 🛠️ Technologies & Concepts

- Java
- Object-Oriented Programming (OOP)
- Encapsulation, Inheritance, and Polymorphism
- Interfaces and Abstraction
- Java Collections Framework
- Stream API
- Lambda Expressions
- Exception Handling
- BigDecimal for monetary calculations
- LocalDate for date handling
- JavaDoc
- SOLID Principles and Clean Code practices

## 💾 Data Storage

The current implementation uses Java Collections to store and manage application data in memory through repository classes.

## ▶️ Getting Started

### Prerequisites
- A compatible Java Development Kit (JDK).
- IntelliJ IDEA or another Java IDE.

### Clone the Repository

```bash
git clone https://github.com/MohammedYa/Hotel-Management-System.git
```

1. Open the project in your Java IDE.
2. Configure the appropriate JDK.
3. Locate the main application entry point.
4. Run the application.
5. Navigate through the console menu to access the available modules.

## 🧪 Testing

Testing covers scenarios such as:

- Positive and negative test cases.
- Boundary conditions.
- Business rule validation.
- Integration between system modules.
- Edge cases and exception handling.

*Testing and final verification are ongoing.*

## 🎯 Project Goals

This project was developed to strengthen practical Java development skills and apply software engineering principles through a real-world management system.

Key objectives:
- Design a layered application architecture.
- Implement and enforce business rules.
- Manage relationships between domain entities.
- Write reusable and maintainable code.
- Apply SOLID principles and Clean Code practices.

## 👨‍💻 Author

**Mohamed Yasser**

- GitHub: [MohammedYa](https://github.com/MohammedYa)
- LinkedIn
