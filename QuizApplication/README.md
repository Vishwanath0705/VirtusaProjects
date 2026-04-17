# Online Quiz Application (Java Swing)

## Overview
This is a desktop-based Online Quiz Application built using Java Swing. The system supports two types of users: Admin and Student. Admins can manage questions and view results, while students can register, take quizzes, and view their results.

The application follows a layered architecture using:
- UI Layer (Swing)
- Service Layer
- DAO Layer
- Database Layer

---

## Features

### Admin Features
- Add new questions
- Update existing questions
- Delete questions
- View all questions
- View student results

### Student Features
- Register new account
- Login system
- Enter personal details before quiz
- Attempt timed quiz
- View final score and performance report

---

## Architecture

The project is structured into multiple layers:

### UI Layer
Handles all Swing-based screens:
- Login screen
- Registration screen
- Admin dashboard
- Question management screens
- Quiz interface
- Result screens

### Service Layer
Contains business logic:
- QuestionService
- StudentService
- UserService

### DAO Layer
Handles database operations:
- QuestionDao
- StudentDao
- UserDao

### Database Layer
MySQL database used for storing:
- Users
- Questions
- Student results

---

## Database Tables

### users
- id
- username
- email
- mobile
- password
- user_type

### question
- id
- question
- option1
- option2
- option3
- option4
- answer

### student
- id
- name
- roll_no
- email
- phone_number
- marks
- total_questions
- percentage

---

## Technologies Used
- Java (JDK 8+)
- Swing (GUI)
- JDBC
- MySQL

---

## Project Flow

1. User opens application
2. Login or Register
3. Admin or Student is identified
4. Admin manages questions or views results
5. Student enters details and starts quiz
6. System loads questions one by one
7. Timer controls each question
8. Score is calculated automatically
9. Final result is stored and displayed

---

## How to Run

1. Clone the repository
2. Import project into NetBeans / IntelliJ / Eclipse
3. Configure MySQL database
4. Update DB connection in `DbConnection.java`
5. Run `main.java`

