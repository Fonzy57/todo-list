# 📌 To-Do List Application - Task & User Management

[🇫🇷 Lire ce document en français](README_FR.md)

## 📖 Description

This Java application allows managing a to-do list with users and tasks using CRUD operations (Create, Read, Update,
Delete). Each models.user can have multiple associated tasks.

## 🛠️ Features

- **User Management:** Add, edit, delete, and view users.
- **Task Management:** Add, edit, delete, and view tasks.
- **User-Task Association:** Each models.task is assigned to a specific models.user.
- **Data Storage:** Data can be stored in a database or a file.

## 📂 Project Structure

```
/todo-list
│── /src
│   ├── models         # Entity classes (User, Task)
│   ├── services       # CRUD operations management
│   ├── controllers    # Interface between the models.user and services
│   ├── database       # Data storage management
│── /tests             # Unit tests
│── README.md          # Project documentation
│── pom.xml            # (If using Maven)
│── build.gradle       # (If using Gradle)
```

## 🚀 Installation & Usage

1. **Clone the project:**
   ```bash
   git clone https://github.com/your-username/todo-list-java.git
   cd todo-list-java
   ```
2. **Compile & Run:**
    - With Maven:
      ```bash
      mvn clean install
      mvn exec:java -Dexec.mainClass="com.yourpackage.Main"
      ```
    - With Gradle:
      ```bash
      gradle build
      gradle run
      ```
    - Using command line:
      ```bash
      javac -d bin src/*.java
      java -cp bin com.yourpackage.Main
      ```

## ✅ Requirements

- Java 17+
- Maven or Gradle (optional)

## 📜 License

This project is licensed under MIT.

## ✨ Author

Developed by **[Your Name]**.
