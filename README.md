# Camera Based Attendance System (Backend Engine)

A Java & Spring Boot backend microservice built to process, calculate, and persist visibility-based attendance data via RESTful APIs. 

This repository houses the **core backend engine and database persistence layer**. It handles the dynamic business logic to evaluate raw attendance metrics and is designed for easy integration with OpenCV / Computer Vision tracking systems.

---

## 🛠️ What Was Built & Verified In This Repository

In this stage of the project, the following core backend components were implemented, configured, and tested locally:

* **Spring Boot REST APIs:** Built `POST /attendance/mark` for processing metrics and `GET /attendance/all` for data retrieval.
* **Dynamic Business Logic:** Implemented auto-classification rules based on visibility ratios (60% Present, 30% Late, <30% Absent).
* **Database Layer:** Integrated Spring Data JPA / Hibernate with an H2 in-memory database engine.
* **Manual Verification:** Tested end-to-end data flow using PowerShell (`Invoke-RestMethod`) and verified live output via browser endpoints.

---
<img width="817" height="441" alt="image" src="https://github.com/user-attachments/assets/03bc29e7-e71d-484b-ad13-1b696a9a9f72" />

## 🏗️ System Architecture & Execution Flow

```text
================================================================================
                    [ WHAT WE BUILT & TESTED (BACKEND ENGINE) ]
================================================================================

                               ┌────────────────────────────────┐
                               │   AttendanceController.java    │
                               │   (Handles POST / GET Requests)│
                               └───────────────┬────────────────┘
                                               │
                                               ▼
                               ┌────────────────────────────────┐
                               │     AttendanceService.java     │
                               │  (Calculates 60% / 30% Rules)  │
                               └───────────────┬────────────────┘
                                               │
                                               ▼
                               ┌────────────────────────────────┐
                               │  AttendanceRepository & H2 DB  │
                               │  (Persists Records in Memory)  │
                               └────────────────────────────────┘

================================================================================
                   [ DATA INPUT SOURCES (TEST VS FUTURE) ]
================================================================================

[ Current Local Testing ] ──► PowerShell Invoke-RestMethod ──┐
                                                             ├──► POST /attendance/mark
[ Future Integration ]  ──► OpenCV / Python Script       ──┘
⚡ Business Logic (Attendance Thresholds)The service layer evaluates the ratio of visibleMinutes against totalMinutes using these fixed rules:Visibility RatioCalculated StatusReason / Description>= 60%PRESENTPerson was actively detected for most of the duration.30% to 59%LATEPerson was partially present/detected during the class.< 30%ABSENTPresence duration was below acceptable attendance limits.🔌 API Endpoints Documentation1. Mark AttendanceCalculates attendance status from visibility inputs and writes the record into the database.URL: POST /attendance/markHeaders: Content-Type: application/jsonRequest Body:JSON{
  "personId": "STU101",
  "role": "STUDENT",
  "visibleMinutes": 45,
  "totalMinutes": 60
}
Response (200 OK):JSON{
  "id": 1,
  "personId": "STU101",
  "role": "STUDENT",
  "status": "PRESENT"
}
2. Fetch All Recorded AttendanceFetches all stored attendance logs directly from the H2 repository.URL: GET /attendance/allResponse (200 OK):JSON[
  {
    "id": 1,
    "personId": "STU101",
    "role": "STUDENT",
    "status": "PRESENT"
  }
]
🗄️ Database & Console SetupDatabase Engine: H2 In-Memory DatabaseConsole Web URL: http://localhost:9000/h2-consoleJDBC Connection String: jdbc:h2:mem:testdbDatabase User: SADatabase Password: (Keep empty/blank)🛠️ Terminal Commands & Local Verification StepsSteps executed to build, run, and verify the Spring Boot service locally:Step 1: Clone ProjectPowerShellgit clone [https://github.com/golusharma23/camera-attendance-system.git](https://github.com/golusharma23/camera-attendance-system.git)
cd camera-attendance-system
Step 2: Start Spring Boot ServerPowerShell.\mvnw.cmd spring-boot:run
Application runs on Port 9000.Step 3: Send Test Payload via PowerShellIn a separate terminal window, execute the REST call to simulate camera data input:PowerShellInvoke-RestMethod -Uri "http://localhost:9000/attendance/mark" -Method Post -ContentType "application/json" -Body '{"personId":"STU101","role":"STUDENT","visibleMinutes":45,"totalMinutes":60}'
Step 4: Verify via Web BrowserNavigate to http://localhost:9000/attendance/all to inspect the generated record stored in the database.📹 How Real Camera System Connects in FutureWhen integrating with external OpenCV Python scripts:Camera Capture: OpenCV tracks student face presence duration.Automated Trigger: At session completion, Python issues a POST /attendance/mark HTTP request with payload metrics.Backend Processing: This Spring Boot engine processes the calculation rules and locks the verdict into DB persistence.👤 AuthorGolu SharmaBackend Developer & ITM StudentGitHub Profile
