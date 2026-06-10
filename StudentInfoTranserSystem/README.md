# Student Info Transfer System (Java Socket + Collections)

## 📌 Project Overview
This project demonstrates a simple **Client–Server application** using **Java Socket Programming** and **Java Collections**.  
The client collects student details (ID, Name, Marks) and sends them to the server.  
The server stores this data using Collections (`HashMap` and `ArrayList`) and displays all student records.

---

## Technologies Used
- Java SE
- Socket Programming (`Socket`, `ServerSocket`)
- Java Collections (`HashMap`, `ArrayList`)
- NetBeans IDE (separate Client and Server projects)

---

## How It Works
1. **Client Side**
   - Uses `Scanner` to take input for Student ID, Name, and Marks.
   - Sends data to the server using `Socket` and `DataOutputStream`.

2. **Server Side**
   - Uses `ServerSocket` to accept client connections.
   - Stores student names in an `ArrayList<String>`.
   - Stores student IDs and marks in a `HashMap<Integer, int[]>`.
   - Displays all student records on the console.
