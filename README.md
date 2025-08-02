# 💉 Child Vaccination Reminder

## Table of Contents
+ [Description](#description)
+ [Technology](#technology)
+ [Features](#features)
+ [Usage](#usage)
+ [Installation and Setup](#installation-and-setup)

---

## Description <a name="description"></a>
**Child Vaccination Reminder** is a Java-based web application developed using Servlets, JSP, and JDBC.  
It helps hospitals manage vaccination schedules for children and reminds parents about upcoming vaccinations.  
The system supports role-based access for **Hospital** and **Child (Parents)**, each with different functionalities and dashboards.

---

## Technology <a name="technology"></a>
- **Frontend**: JSP, HTML, CSS  
- **Backend**: Java (Core + Advanced), Servlets  
- **Database**: MySQL  
- **Connectivity**: JDBC  
- **Server**: Apache Tomcat  
- **IDE**: VS Code / Eclipse  
- **Version Control**: Git & GitHub

---

## Features <a name="features"></a>

### 🔹 Hospital
- Hospital Login  
- Add / View / Delete Vaccines  
- Update Vaccine Price  
- Add / View / Delete Child Records  
- View Upcoming All Child Vaccines (next 30 days)  
- Change Password  

### 🔹 Child (Parent)
- Child Login  
- View Available Vaccines  
- View Upcoming Own Child Vaccines (next 30 days)  
- View Own Child Vaccination Log  
- Change Password  

---

## Usage <a name="usage"></a>
1. **Hospital** manages vaccine inventory, pricing, and child records.  
2. **Hospital** can see upcoming vaccinations for all registered children.  
3. **Child/Parent** can log in to check vaccine schedules and view vaccination history.  
4. Both roles have secure login and password change options.  

---

## Installation and Setup <a name="installation-and-setup"></a>
1. **Clone the Repository**
   ```bash
   git clone https://github.com/<your-username>/ChildVaccinationReminder.git
