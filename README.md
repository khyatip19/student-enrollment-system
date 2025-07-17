Student Enrollment System using Spring Boot
Author: Khyati Prajapati

What is Spring Framework? Why do we use it?
Spring is a popular framework for building Java applications. It has many modules like:
<insert image>

In this project, I built a student enrollment system - a basic CRUD application to manage students and their enrollments in various courses. This project covers all the core backend skills - data modelling, REST Apis and CRUD.

Step 1: Setup Springboot project.
Now there are two ways to create a spring boot project:
1. Using start.spring.io website
2. Using IntelliJ

I opted for the first option and went to https://start.spring.io
<insert image>

Select all the options as given in the screenshot above.
Project: Maven
Language: Java
Springboot: Latest Stable Version
Group, Artifact, Name, Description, Package name: Your own choice (default metadata)
Packaging: jar
Java version: 17
Dependencies: 
Spring Web: for rest Apis
Spring Data JPA: for ORM (object relational mapping). Basic CRUD queries are already present in this.
PostgreSQL driver: to connect to my PostgreSQL DB
Spring boot devtools: for hot reload during dev
Lombok: to reduce boiler plate 

Thats it! Click on generate and open the downloaded zip in your IntelliJ IDE after extracting the folder, ofcourse!
You must be able to see the following project structure:
.idea - configuartion files used by Intellij
.mvn - Part of maven wrapper
src - The folder where you will have your code. It has sub folders like main and test.
  - main
      - java: all java files go in this folder
      - resources: all non java files like static assets (HTML, CSS, JS files) go in here
          - application.properties: This is configuration file and it stores data in key value pairs
mvnw
mvnw.cmd
pom.xml- Project object model, it contains the configurations required by the project and all its dependencies. Maven uses this file to download dependencies and build the project.
You can now open your pom.xml and check all the dependencies are added, the ones that we selected before generating the starter code.

Step 2: Connect our database - PostgreSQL to our SpringBoot project
Open src/main/resources/application.properties
In application.properties file we need to add key value pairs that store information on how to connect to our database. (But first, create a database in pgAdmin) 
Once that is done, edit the application.properties file

# PostgreSQL Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/student_enrollment_db  # Tells spring where your database is located, jdbs is Java Database connectivity protocol, and 5432 is default PostgreSQL port
spring.datasource.username=your_username                                      
spring.datasource.password=your_password

# JPA (Hibernate) Settings
spring.jpa.hibernate.ddl-auto=update                                            # Auto-creates/updates your DB tables based on your entity classes. Other options include: none, validate and create.
spring.jpa.show-sql=true                                                        # prints SQL on your console to see exactly what Hibernate is doing
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect # tells hibernate how to write SQL to PostgreSQL

Before jumping to the next step, you would have noticed a new word here - Hibernate. In order to understand Hibernate, we need to know JPA in detail.

What is JPA? 
JPA is Java Persistence API - its a specification or a rulebook. It defines how Java objects (like Student, Course) are mapped to database tables.

What is Hibernate?
Hibernate is the most popular implementation of JPA. It talks to your database, and transkates Java code to SQL behind the scenes.
Example: 
# java code
studentRespository.save(student)
Hibernate converts it to SQL:
INSERT INTO students(name, email) VALUES ('Name', 'Email');

So now to test, if our application got successfully connected to our database, you can go to the main class that runs your app and run it.
If its successful, you will see: 
Tomcat started on port: 8080
Started StudentEnrollApplication in X.XX seconds


