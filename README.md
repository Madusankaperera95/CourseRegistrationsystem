# Java Course Registration System Project

## Overview
The Course Registration System (CRS) aims to automate the course registration process at educational institutions. It simplifies academic schedule management for students while providing faculty and administrative staff with an efficient way to maintain and track course and student data. This project is managed with Maven and follows standard conventions to support dependency management, build automation, and deployment.

## Prerequisites
Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 11 or later
- Apache Maven 3.6 or later
- An IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code)

## Project Structure
```
project-root/
│-- src/
│   ├── main/
│   │   ├── java/                # Source code
│   │   │   ├── lk/cmjd/coursework/
│   │   │   │   ├── controller/  # Controllers
│   │   │   │   ├── dao/         # Data Access Objects
│   │   │   │   ├── dto/         # Data Transfer Objects
│   │   │   │   ├── entity/      # Entity models
│   │   │   │   ├── images/      # Image resources
│   │   │   │   ├── service/     # Business logic services
│   │   │   │   ├── styles/      # Styles and CSS
│   │   │   │   ├── util/        # Utility classes
│   │   │   │   ├── views/       # UI Views
│   │   │   │   ├── App.java     # Main application entry
│   │   │   │   ├── HelloController.java  # Sample Controller
│   │   │   ├── module-info.java # Module descriptor
│   │   ├── resources/           # Configuration files
│   ├── test/                    # Unit tests
│-- target/                      # Compiled output
│-- pom.xml                       # Maven configuration file
│-- .gitignore                    # Git ignore file
│-- README.md                     # Project documentation
```

## Installation & Setup
### Clone the repository
```sh
git clone <repository_url>
cd project-root
```

### Build the project
```sh
mvn clean install
```





## Dependencies
Dependencies are managed via `pom.xml`. To add a new dependency, update the `<dependencies>` section and run:
```sh
mvn clean install
```

### Credetials
```sh
Admin Login - email: kumara@yopmail.com
              pwd: sL@393#A
Student Login - email: kinara@yopmail.com
              pwd: 1234
```


## Contact
For any inquiries, contact [Manoj Perera] at [manojperera095@gmail.com].

