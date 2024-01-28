
# Todo Application
Welcome to the Todo application, a project built with Java and the Spring Boot framework. This comprehensive task management system integrates various technologies, including SQL, JDBC, ORM (Object-Relational Mapping), Hibernate, Spring Data, JUnit for testing, Dependency Injection, Thymeleaf for the frontend, and Spring Web. The application uses an H2 database with an empty username and password for simplicity.

## Table of Contents
- [Technologies Used](#Technologies_Used)
- [Prerequisites](#Prerequisites)
- [Getting Started](#Getting_Started)
- [Screenshots](#Screenshots)
- [Important Notes](#Important_Notes)

## Technologies
- Java
- Spring Boot
- SQL
- JDBC
- ORM (Object-Relational Mapping)
- Hibernate
- Spring Data
- JUnit
- Dependency Injection
- Thymeleaf
- Spring Web
- Validation

## Prerequisites
- Java Development Kit (JDK)
- Integrated Development Environment (IDE) for Java (e.g., IntelliJ, Eclipse)
- Postman for sending HTTP requests
- Web browser for navigating and managing tasks through the Thymeleaf-based frontend

## Getting Started
1. **Clone the Repository:**
- git clone <[repository_url](https://github.com/rvdxk/todo-app)>

2. **Open in IDE:**

Open the project in your preferred Java IDE.

3. **Configure H2 Database:**

The application uses an H2 database with an empty username and password. 
Ensure that the H2 database configuration is set up correctly in the application.properties file.

4. **Run the Application:**

Run the Spring Boot application to start the server. Access the application using http://localhost:8080.


5. **Accessing the Frontend:**

Open your web browser and navigate to 'http://localhost:8080' to manage your tasks through the Thymeleaf-based frontend.


6. **Testing with Postman:**

Open Postman and use it to interact with the Todo application. Send requests to the relevant endpoints to perform CRUD operations on tasks and groups.

### TASKS:

- **Create Task:**
- Endpoint: POST http://localhost:8080/tasks
- Payload:
<pre>
json
{
    "done": false,
    "description": "Task Description",
    "deadline": "2025-11-13T23:59:59.999"
}
</pre>

- **Read and Sort Tasks:**
- Endpoint: GET http://localhost:8080/tasks?sort=description

- **Read Tasks by Done:**
- Endpoint: GET http://localhost:8080/tasks/search/done?state=false

- **Read Task by Id:**
- Endpoint: GET http://localhost:8080/tasks/{id}

- **Search Tasks by Done:**
- Endpoint: GET http://localhost:8080/tasks/search/done

- **Look for Tasks for Today:**
- Endpoint: GET http://localhost:8080/tasks/search/today

- **Update Task:**
- Endpoint: PUT http://localhost:8080/tasks/{id}
- Payload:
<pre>
json
{
    "done": true,
    "description": "Updated Task Description",
    "deadline": "2025-11-13T23:59:59.999"
}
  </pre>
  
-  **Patch Task:**
- Endpoint: PATCH http://localhost:8080/tasks/{id}
- Payload (partial update):
<pre>
json
{
    "done": true
}
</pre>

### GROUPS:

- **Create Group:**
- Endpoint: POST http://localhost:8080/groups
- Payload:
<pre>
json
{
    "description": "Test group",
    "tasks": [
        {
            "description": "Test",
            "deadline": "2023-12-30T23:59:59.999"
        }
    ]
}
</pre>

- **Read Groups:**
- Endpoint: GET http://localhost:8080/groups

- **Toggle Group (Done):**
-Endpoint: PUT http://localhost:8080/groups/{id}

- **Get Tasks Before Deadline:**
-Endpoint: GET http://localhost:8080/tasks/search/today

- **Additional Features:**
- Check if the task is done.
- Check the deadline for the task.
- When adding a new task, set a date before it has to be done.

### OTHER:
  
- **Get Info URL:**
- Endpoint: GET http://localhost:8080/tasks/info/url

- **Get Info Properties:**
- Endpoint: GET http://localhost:8080/tasks/info/prop

## Screenshots

![Screenshot (72)](https://github.com/rvdxk/todo-app/assets/136000622/ad963c32-d66b-4020-8da0-7bdb92b879da)
![Screenshot (70)](https://github.com/rvdxk/todo-app/assets/136000622/9a83188c-1154-439d-9795-c3b5faf5697c)
![Screenshot (69)](https://github.com/rvdxk/todo-app/assets/136000622/370ea919-b2cb-45e7-b948-3c2a92b32396)
![Screenshot (68)](https://github.com/rvdxk/todo-app/assets/136000622/8328e812-90f8-4b1a-8e4a-cb3d27199a65)

## Important Notes
Ensure that your IDE, JDK, Postman, and web browser are set up and running.
Feel free to explore and customize this Todo application according to your needs. Manage your tasks seamlessly through both Postman and the Thymeleaf-based frontend. Happy coding!

<sup>*This application was inspired by a course from Udemy.<sup>
