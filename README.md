
# Camera Based Attendance System (Backend)

A backend application built with **Spring Boot** to demonstrate how an automated attendance system can be designed using REST APIs and database integration.

This project focuses on **clean backend architecture, API design, and persistence**, rather than UI.

---

## 🧠 What This Project Demonstrates
- Spring Boot application setup
- REST API design
- Entity–Repository–Service pattern
- Database integration using JPA & Hibernate
- Version control using Git & GitHub

---

## ⚙️ Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Git & GitHub

---

## 🏗️ Architecture
The application follows a layered architecture:

- **Controller** – Handles HTTP requests  
- **Service** – Business logic  
- **Repository** – Database operations  
- **Model (Entity)** – Data representation  

Flow:
Client / API Call → Controller → Service → Repository → Database

---

## 🔌 Available APIs

### Mark Attendance

POST /attendance/mark

### Get All Attendance Records

GET /attendance/all

---

## 🗄️ Database
- Uses **H2 in-memory database**
- Tables are auto-generated via Hibernate
- Ideal for development and testing

H2 Console:

---

## ▶️ Running the Project
1. Clone the repository
2. Open in STS / IntelliJ
3. Run the main Spring Boot application
4. Access APIs via browser or Postman

---

## 🚧 Current Limitations
- No frontend UI
- Camera input is simulated
- In-memory database (data resets on restart)

---

## 🔮 Future Improvements
- Real camera integration
- Face recognition module
- Persistent database (MySQL / PostgreSQL)
- Frontend dashboard

---

## 👤 Author
Built by **Golu Sharma**  
This repository represents my hands-on backend development work using Spring Boot.
