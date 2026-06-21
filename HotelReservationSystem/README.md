## Hotel Reservation System (Java + Swing + MySQL)

A desktop application for hotel booking and management built with **Java Swing** and **JDBC (MySQL)**.  
This project is part of the **Advance_Java** repository.

---

##  Features
- User authentication (Admin, Guest)
- Room management (add, update, delete rooms)
- Reservation management (book, cancel, view reservations)
- Dashboard for quick overview
- Simple UI with icons and themes

---


## ⚙️ Prerequisites
- **Java JDK 8+**
- **MySQL Server** (running locally)
- **MySQL Workbench** (optional, for database setup)
- **Git** (to clone the repo)

---

## 🗄️ Database Setup
1. Start your MySQL server.
2. Open MySQL Workbench (or command line).
3. Run the provided SQL script:
   ```sql
   source database.sql;

    Update your database connection string in DatabaseConnection.java if needed: 
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

## How to run:
    git clone https://github.com/naimur-rooted/Advance_Java.git
    cd Advance_Java/HotelReservationSystem1

    Click the *run.bat* file to compile and run the project .

Defatul Account:

Admin:
Username: admin  
Password: admin

Receptionist  
Username: reception  
Password: recep123

Guest  
Username: guest  
Password: guest123
