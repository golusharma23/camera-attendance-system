# Camera Based Attendance System (Backend)

A custom Spring Boot backend microservice designed to handle attendance automation based on student/personnel visibility metrics. It acts as the business logic and persistence engine that processes raw face-tracking data and classifies attendance status dynamically.

---

## 💡 Project Purpose & Learning Goals

Mainly built this project to master hands-on backend development concepts:
- Building clean RESTful APIs using **Spring Boot 4.x** and **Java 17**.
- Implementing dynamic business calculations before persisting data.
- Managing database layers using **Spring Data JPA** and **Hibernate**.
- Setting up **H2 In-Memory Database** for fast, local development testing.
- Designing a handoff architecture ready for **OpenCV / Python Computer Vision** scripts.

---

## 🏗️ System Architecture & Execution Flow

Here is how data flows through the application architecture:

```text
[ Camera / Webcam Capture ] 
            │
            ▼ (Calculates total visible minutes vs class duration)
  [ Python / OpenCV Client ] 
            │
            ▼ (Sends HTTP POST Request with JSON payload)
   [ AttendanceController ] ──► [ AttendanceService ] (Applies 60% / 30% Logic Rules)
                                         │
                                         ▼ (Saves output to DB)
                               [ AttendanceRepository ] ──► [ H2 Database ]
⚡ Attendance Evaluation Rules
The service layer dynamically checks the ratio of visibleMinutes against totalMinutes using these fixed rules:

Visibility Ratio	Calculated Status	Reason / Description
>= 60%	PRESENT	Person was actively detected for most of the duration.
30% to 59%	LATE	Person was partially present/detected during the class.
< 30%	ABSENT	Presence duration was below acceptable attendance limits.
🔌 API Endpoints Documentation
1. Mark Attendance
Calculates attendance status from visibility inputs and writes the record into the database.

URL: POST /attendance/mark

Headers: Content-Type: application/json

Request Body:

JSON
{
  "personId": "STU101",
  "role": "STUDENT",
  "visibleMinutes": 45,
  "totalMinutes": 60
}
Response (200 OK):

JSON
{
  "id": 1,
  "personId": "STU101",
  "role": "STUDENT",
  "status": "PRESENT"
}
2. Fetch All Recorded Attendance
Fetches all stored attendance logs directly from the H2 repository.

URL: GET /attendance/all

Response (200 OK):

JSON
[
  {
    "id": 1,
    "personId": "STU101",
    "role": "STUDENT",
    "status": "PRESENT"
  }
]
🗄️ Database & Console Setup
Database Engine: H2 In-Memory Database

Console Web URL: http://localhost:9000/h2-console

JDBC Connection String: jdbc:h2:mem:testdb

Database User: SA

Database Password: (Keep empty/blank)

🛠️ Step-by-Step Setup, Terminal Commands & Manual Verification
Steps followed to run, test, and verify the backend locally:

Step 1: Clone and Build Project
PowerShell
git clone [https://github.com/golusharma23/camera-attendance-system.git](https://github.com/golusharma23/camera-attendance-system.git)
cd camera-attendance-system
Step 2: Start the Spring Boot Backend Server
PowerShell
.\mvnw.cmd spring-boot:run
Application boots up on Port 9000.

Step 3: Test Attendance Calculation via Terminal
In a separate PowerShell window, run the following single-line command to send sample student timing data:

PowerShell
Invoke-RestMethod -Uri "http://localhost:9000/attendance/mark" -Method Post -ContentType "application/json" -Body '{"personId":"STU101","role":"STUDENT","visibleMinutes":45,"totalMinutes":60}'
Step 4: Verify via Web Browser
Open your browser and navigate to:
http://localhost:9000/attendance/all

It will display the processed record in clean JSON format indicating the student was marked PRESENT.

📹 How Real Camera System Connects in Future
When fully integrated with actual hardware:

Video Stream: Webcams/IP Cameras capture live video feed inside a lecture room.

Detection Layer: A client-side Python script powered by OpenCV/Dlib scans student faces, tracks their total visible presence in minutes, and accumulates the timing metrics.

Automated Trigger: When class ends, the Python script sends an automated POST request containing personId, visibleMinutes, and totalMinutes to this Spring Boot backend.

Final DB Persistence: The Spring Boot backend evaluates the percentage, applies status rules, and saves the final result to the main system database automatically.

🔮 Limitations & Next Up Improvements
Database: Upgrade from temporary H2 DB to persistent database storage like MySQL or PostgreSQL.

Frontend: Build a React / Angular instructor dashboard for live reporting.

AI Handoff: Complete integration with OpenCV Python recognition models.

👤 Author
Golu Sharma

Backend Developer & ITM Student

GitHub Profile
