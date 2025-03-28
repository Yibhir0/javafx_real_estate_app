# 🏘️ RealEstate Rental Management System

**Author:** Yassine Ibhir  
**Project:** RealEstate  
**Description:**  
This project is a Java-based desktop application designed to manage real estate properties and rentals. The system supports various types of properties, such as **units, duplexes, apartments, and houses**, and tracks their **occupancy status** (vacant/occupied), **mortgages**, and associated **banks**. It provides a structured and scalable way to handle complex rental and ownership data efficiently.

---


---

## 🛠️ Features

- Manage rental properties (units, duplexes, apartments, houses)
- Track vacant and occupied properties
- Associate properties with mortgage banks
- Store and retrieve rental data from a relational database
- Modular Java architecture using packages
- SQL scripts for DB schema and sample data

---

## 🚀 Steps to Run the Program

### 1️⃣ Set Up the Database Connection

Open `DatabaseConnection.java` located at:

```bash
src/com/mycompany/realestate/model/database/DatabaseConnection.java
```
Update the DB credentials (URL, username, password) to match your local MySQL setup. 

### 2️⃣ Create the Database Tables

Run the SQL script `createTable.sql` (located in `src/com/mycompany/realestate/sql/`) using your preferred MySQL client (e.g., MySQL Workbench, CLI, or DBeaver):

```sql
-- Example
source path/to/createTable.sql;

### 3️⃣ Insert Sample Data (Optional)


Run insertTable.sql inside the package com.mycompany.realestate.sql(optional)

### 4️⃣ Run the Application

# If using terminal
javac com/mycompany/realestate/App.java
java com.mycompany.realestate.App

Or run it directly from your IDE (NetBeans, IntelliJ, Eclipse, etc.).


# If using terminal
javac com/mycompany/realestate/App.java
java com.mycompany.realestate.App


