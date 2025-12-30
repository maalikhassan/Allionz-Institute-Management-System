<div align="center">

# 🎓 Allionz Institute Management System (IMS)

![Java](https://img.shields.io/badge/Java-SE--17-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-orange?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

**A Comprehensive Enterprise Resource Planning Solution for Educational Institutes**

[Features](#-key-features) • [Installation](#-installation) • [Architecture](#-system-architecture) • [Documentation](#-documentation) • [Team](#-team)

---

> 🔒 **Security Note:** This is a demo/portfolio project. The database shown in git history is no longer active and credentials have been changed. All sensitive configuration is now externalized via properties files.

</div>

---

## 📋 Project Overview

**Allionz Institute Management System (IMS)** is a full-featured desktop application developed as the final team project for the **Software Application Development** course at university. This enterprise-grade solution was designed to streamline operations for educational institutes, specifically modeled after A/L (Advanced Level) institutes in Colombo, Sri Lanka.

### 🎯 Project Context

- **Client:** Novara Institute (fictional, based on real institute operations)
- **Duration:** 4 months (Full SDLC)
- **Team Size:** 8 developers
- **My Role:** Team Leader & Project Manager
- **Methodology:** Agile → Waterfall (adapted mid-project)
- **Development:** Java SE with MySQL database
- **Presentation:** Successfully defended in final VIVA

The system addresses real-world challenges faced by educational institutes, including student enrollment management, fee collection, staff administration, financial reporting, and academic operations.

---

## 🌟 Key Features

### 👨‍🎓 Academic Management Module
- **Student Enrollment:** Complete student registration with photo capture
- **Batch Management:** Organize students into classes and batches
- **Subject Assignment:** Flexible subject allocation and tracking
- **Teacher Management:** Comprehensive teacher profiles and assignments
- **Attendance Tracking:** Digital attendance management system
- **Grade Management:** Record and track student performance

### 💰 Financial Management Module
- **Fee Collection:** Multiple payment methods (Cash, Card, Online)
- **Receipt Generation:** Automated receipt printing with barcode
- **Payment Tracking:** Real-time payment status monitoring
- **Expense Management:** Track institute operational expenses
- **Vendor Management:** Manage suppliers and service providers
- **Bill Type Management:** Categorize different fee types
- **Financial Reports:** 
  - Monthly income reports
  - Expense reports
  - Profit/Loss statements
  - Outstanding dues tracking
  - Class-wise fee collection reports

### 🛠️ Administration Module
- **User Management:** Role-based access control (Admin, Academic, Finance)
- **Staff Management:** Employee records for both teaching and non-teaching staff
- **Salary Management:** 
  - Base salary calculation
  - Attendance-based salary processing
  - Automated salary slip generation
- **System Configuration:** Centralized system settings
- **Audit Logs:** Track all system activities
- **Dashboard Analytics:** KPI visualization and monitoring

### 📊 Reporting System
Powered by **JasperReports** with the following reports:
- Student enrollment reports
- Fee collection summaries
- Monthly financial statements
- Salary details (teaching and non-teaching staff)
- Attendance reports
- Custom date-range reports
- Exportable in PDF format

### 🔐 Security Features
- Secure login system with password encryption
- Role-based access control (RBAC)
- Session management
- User activity logging
- Database connection security (SSL)

---

## 🏗️ System Architecture

### Technology Stack

#### Frontend
- **Java Swing** - Desktop GUI framework
- **FlatLaf** - Modern look and feel theme
- **JFreeChart** - Data visualization and charting
- **JCalendar** - Date picker component

#### Backend
- **Java SE 17** - Core programming language
- **JDBC** - Database connectivity
- **MySQL Connector/J** - MySQL driver

#### Database
- **MySQL 8.0** - Relational database
- **Hosted on:** Aiven Cloud (for development/demo)

#### Reporting
- **JasperReports 7.0** - Report generation engine
- **Barcode4J** - Barcode generation for receipts
- **Apache Commons** - Utility libraries

#### Build Tools
- **Apache Ant** - Build automation
- **NetBeans 8.2** - Primary IDE

### Project Structure

```
Allionz-Institute-Management-System/
│
├── src/
│   ├── GUI/                    # User Interface Components
│   │   ├── MainApplication.java
│   │   ├── AcademicDashboard.java
│   │   ├── FinancialDashboard.java
│   │   ├── AdminDashboard.java
│   │   └── ...
│   │
│   ├── model/                  # Business Logic Layer
│   │   ├── MySQL.java          # Database connection
│   │   ├── AcademicUserHandler.java
│   │   ├── FinancialUserHandler.java
│   │   ├── AdminUserHandler.java
│   │   ├── SalaryCalculation.java
│   │   └── LogManager.java
│   │
│   ├── reports/                # JasperReports Templates
│   │   ├── AdminClassFeesReport.jrxml
│   │   ├── AdminMonthly_salaryDetails.jrxml
│   │   ├── AdminProfit_report.jrxml
│   │   └── ...
│   │
│   └── resources/              # Application Resources
│       ├── studentImg/         # Student photographs
│       ├── teacherImg/         # Teacher photographs
│       └── documents/          # Generated documents
│
├── lib/                        # External Libraries
│   ├── mysql-connector-j-9.0.0.jar
│   ├── jasperreports-7.0.0.jar
│   ├── flatlaf-3.5.1.jar
│   └── ...
│
├── build/                      # Compiled classes
├── dist/                       # Distributable JAR
└── nbproject/                  # NetBeans project files
```

### Database Schema

The system uses a normalized relational database with the following key entities:

- **Users & Authentication:** user, user_type, user_session
- **Academic:** student, teacher, subject, batch, class, attendance
- **Financial:** payment, fee_type, expense, vendor, bill_type
- **Administration:** employee, salary, department
- **Audit:** activity_log, system_settings

---

## 🚀 Installation

### Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **MySQL Server 8.0** or higher
- **NetBeans IDE 8.2+** (recommended) or any Java IDE
- **Minimum 4GB RAM** and **500MB disk space**

### Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/Allionz-Institute-Management-System.git
   cd Allionz-Institute-Management-System
   ```

2. **Database Setup**
   ```sql
   -- Create database
   CREATE DATABASE institute_management_system;
   
   -- Import the schema (SQL file should be provided separately)
   mysql -u root -p institute_management_system < database/schema.sql
   ```

3. **Configure Database Connection**
   
   Create a `database.properties` file in the project root directory:
   ```properties
   # Database Configuration
   db.host=localhost
   db.port=3306
   db.name=institute_management_system
   db.username=your_username
   db.password=your_password
   db.ssl.mode=DISABLED
   
   # MySQL Paths (adjust according to your system)
   mysql.bin.path=C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql
   mysqldump.bin.path=C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump
   ```
   
   > **Note:** A template file `database.properties.example` is provided. Copy it to `database.properties` and update with your credentials.
   > 
   > ⚠️ **Never commit `database.properties` to version control!** It's already included in `.gitignore`

4. **Add External Libraries**
   
   All required JAR files are included in the `lib/` directory. If using an IDE:
   - Right-click project → Properties → Libraries
   - Add all JAR files from the `lib/` folder

5. **Build the Project**
   
   Using NetBeans:
   - Open the project in NetBeans
   - Right-click project → Clean and Build
   
   Using Ant (command line):
   ```bash
   ant clean
   ant jar
   ```

6. **Run the Application**
   
   ```bash
   java -jar dist/InstituteManagementSystem.jar
   ```
   
   Or run `MainApplication.java` from your IDE.

### Default Login Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Academic | academic | academic123 |
| Finance | finance | finance123 |

> ⚠️ **Security Note:** Change default passwords immediately after first login. Login might not work as its currently linked to an online database thats powered off

---

## 📸 Screenshots

### 1. Login & User Selection
- Modern splash screen with loading animation
- Role-based login system (Admin/Academic/Finance)

### 2. Admin Dashboard
- Real-time KPI widgets
- Quick access to all modules
- System health monitoring

### 3. Student Management
- Student registration with photo capture
- Batch assignment and tracking
- Academic history

### 4. Financial Dashboard
- Payment processing interface
- Real-time financial summaries
- Outstanding dues alerts

### 5. Reporting Module
- Interactive report generation
- Custom date range selection
- PDF export functionality

---

## 📖 Documentation

### Project Deliverables

This project includes comprehensive documentation prepared for the final VIVA presentation:

1. **GUI Report** - Detailed UI/UX documentation with screenshots
2. **KPI Report** - Key Performance Indicators and system metrics
3. **System Requirements Specification (SRS)**
4. **Database Design Document (ERD)**
5. **User Manual**
6. **Installation Guide**
7. **Test Cases and Reports**

### Key Design Decisions

#### 1. **Modular Architecture**
The system is divided into three independent modules (Academic, Finance, Admin) to ensure:
- Clear separation of concerns
- Easier maintenance and updates
- Role-based access control
- Team parallel development

#### 2. **Methodology Switch: Agile → Waterfall**
Mid-project, we transitioned from Agile to Waterfall due to:
- Fixed deadline requirements
- Clear requirement definition
- Need for comprehensive documentation
- Better suited for academic project evaluation

#### 3. **Desktop Application Choice**
Despite web applications being more common, we chose desktop for:
- Offline functionality (critical for institutes with unreliable internet)
- Better performance for resource-intensive operations
- Enhanced security for sensitive data
- Simpler deployment for single-location use

#### 4. **JasperReports Integration**
Selected JasperReports for:
- Professional PDF generation
- Customizable report templates
- Excellent Java integration
- Complex layout support

---

## 🎯 Project Management Approach

### As Team Leader & Project Manager

#### Responsibilities:
- **Sprint Planning:** Organized 2-week sprints with clear deliverables
- **Task Allocation:** Distributed work based on team member strengths
- **Code Reviews:** Conducted peer reviews to maintain code quality
- **Client Communication:** Managed stakeholder expectations
- **Risk Management:** Identified and mitigated project risks
- **Documentation:** Ensured all deliverables were properly documented
- **VIVA Preparation:** Coordinated team for final presentation

#### Challenges Overcome:
1. **Timeline Pressure:** Managed scope creep and prioritized features
2. **Team Coordination:** Synchronized work across 8 developers
3. **Technical Debt:** Balanced rapid development with code quality
4. **Learning Curve:** Helped team members learn new technologies
5. **Database Design:** Iteratively refined schema based on requirements

#### Tools Used:
- **Version Control:** Git & GitHub
- **Communication:** WhatsApp, Discord
- **Documentation:** Google Docs, Draw.io
- **Project Tracking:** Trello/Notion boards
- **Code Collaboration:** NetBeans with shared repositories

---

## 🧪 Testing

### Testing Strategy
- **Unit Testing:** JUnit 4 for business logic validation
- **Integration Testing:** Database connectivity and transaction testing
- **System Testing:** End-to-end workflow validation
- **User Acceptance Testing:** Tested with actual institute staff
- **Regression Testing:** Ensured new features didn't break existing functionality

### Test Coverage
- Authentication and authorization
- Student enrollment workflows
- Fee payment processing
- Report generation accuracy
- Database transaction integrity
- UI responsiveness and error handling

---

## 🚧 Known Limitations & Future Enhancements

### Current Limitations
- Desktop-only application (no web/mobile interface)
- Single institute deployment (not multi-tenant)
- Limited to local network access
- Manual database backup required

### Proposed Future Enhancements
- [ ] Web-based interface using Spring Boot + React
- [ ] Mobile app for parents and students
- [ ] SMS/Email notifications for fees and attendance
- [ ] Online exam management module
- [ ] Cloud deployment with multi-institute support
- [ ] Advanced analytics and predictive insights
- [ ] Integration with payment gateways
- [ ] Automated backup and disaster recovery
- [ ] API development for third-party integrations

---

## 👥 Team

**Allionz Software Solutions** - University Project Team

| Role | Member | Responsibilities |
|------|--------|------------------|
| **Team Leader & Project Manager** | Maalik Hassan | Overall coordination, SDLC management, system architecture |
| Full Stack Developer | Vishmika Sewmini | Academic module development |
| Full Stack Developer | Pawan Sarathchandra | Financial module development |
| Backend Developer | Pramuditha Lakshan | Database design and optimization |
| Frontend Developer | Ashan Sanchitha  | UI/UX design and implementation |
| QA Engineer | Pasindu Madhuwantha | Testing and quality assurance |
| Documentation Specialist | Shehan Sandaruwan  | User manuals and technical docs |
| Database Administrator | Anuradha Prasadanie  | Database management and reporting |

---

## 📜 License

This project was developed as part of an academic course and is intended for educational purposes only. 

**Academic Use:** This software was created to fulfill the requirements of the Software Application Development course final project.

---

## 🙏 Acknowledgments

- **Course Instructors** for guidance and feedback throughout the project
- **Novara Institute** (fictional) and real A/L institutes in Colombo for inspiration
- **University** for providing the platform and resources
- **Team Members** for their dedication and collaborative effort
- **Open Source Community** for the excellent libraries and tools

---

## 📞 Contact

For any questions or inquiries about this project:

- **Email:** maalikhassan1@gmail.com
- **LinkedIn:** www.linkedin.com/in/maalik-hassan
- **GitHub:** https://github.com/maalikhassan

---

## 📊 Project Statistics

- **Total Lines of Code:** ~15,000+
- **Number of Classes:** 50+
- **Database Tables:** 25+
- **JasperReports Templates:** 15+
- **Total Development Hours:** ~800+ hours (team combined)
- **Git Commits:** 300+
- **Documentation Pages:** 100+

---

<div align="center">

**Developed with ☕ and 💻 by the Allionz Software Solutions Team**

*Institute Management System - Transforming Educational Administration*

⭐ If you find this project interesting, please consider giving it a star!

</div>
