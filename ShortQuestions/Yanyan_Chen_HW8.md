# Yanyan Chen - HW 8

## 0. Environment
- OS: macOS
- Java version: 18.0.2.1
- Database: mySQL

---

## 1. Directly downloaded 02_POST_RUD branch from GitHub repo to my local desktop and import to IntelliJ as a maven project

### pom.xml in project & Maven tool window
<img width="1446" height="749" alt="image" src="https://github.com/user-attachments/assets/5aa68ab7-4eb5-4411-874f-d692fbfdbe55" />

---
## 2. Modify application.properties
### What I changed
- spring.datasource.url=jdbc:mysql://localhost:3306/redbook?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
- spring.datasource.username=root
- spring.datasource.password=NewStrongPassword123!

### local database check
<img width="1444" height="750" alt="image" src="https://github.com/user-attachments/assets/3cb5e8d9-48a9-4034-b2c0-0e4a7a2fba8d" />

### application.properties changes
<img width="1444" height="750" alt="image" src="https://github.com/user-attachments/assets/5557de1e-e48a-47e2-afe1-74d3c1860fea" />

---

## 3. Maven clean and compile
### BUILD SUCCESS
<img width="1444" height="750" alt="image" src="https://github.com/user-attachments/assets/276dc510-d115-47c1-9266-bd398f6f7d98" />

---

## 4. Run the application

<img width="1444" height="641" alt="image" src="https://github.com/user-attachments/assets/bb7a4c83-5b51-4955-b077-b9244a1bad69" />

---

## 5. Create a postman request to generate a record in database

### @PostMapping in PostController

<img width="2868" height="1090" alt="image" src="https://github.com/user-attachments/assets/2caf10d5-24bb-4ad3-ac39-c789623eee89" />

### Postman request

Headers: Content-Type: application/json

<img width="2544" height="1564" alt="image" src="https://github.com/user-attachments/assets/1595ec14-10a4-4c74-bac8-8523e7d7473c" />

Body:

<img width="2544" height="1564" alt="image" src="https://github.com/user-attachments/assets/e7948fb1-1864-44b2-9cf6-39577170757d" />

Postman response

<img width="2544" height="1564" alt="image" src="https://github.com/user-attachments/assets/46ab6bb4-f395-4341-9902-ee83df32849d" />

---

## 6. DB verification
### DB record inserted

<img width="1800" height="1204" alt="image" src="https://github.com/user-attachments/assets/2379c5b1-3635-45f9-8e5c-07007e862a14" />

---

## Q1: Did you create the table POSTS in the database? If not, who did it for you? Can I change this behavior?

No, I did not manually create the posts table in the database. The table was automatically created by Hibernate (JPA) when the Spring Boot application started. This behavior is controlled by the following configuration in application.properties:

spring.jpa.hibernate.ddl-auto=update

With ddl-auto=update, Hibernate automatically generates or updates database tables based on the entity classes.

Yes, this behavior can be changed by modifying the ddl-auto value.

## Q2: Is your id in the database same as what you set in your request? why does this happen?

No, the ID stored in the database is not the same as the one provided in the request. This happens because the entity uses an auto-generated primary key, typically defined with annotations such as @Id and @GeneratedValue. As a result, Hibernate (or the database) automatically generates the ID value when the record is saved, and any ID provided by the client is ignored.
