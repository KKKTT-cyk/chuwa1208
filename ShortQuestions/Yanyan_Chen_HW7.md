## 1. Explain Referential Integrity in RDBMS/SQL with sample queries

Referential Integrity in RDBMS ensures that relationships between tables remain valid and consistent. In this schema, referential integrity is enforced through FOREIGN KEY constraints, which restrict how data can be inserted, updated, or deleted when tables are related.

### Foreign Key Constraints
The code establishes parent-child relationships between tables:
```sql
FOREIGN KEY (dept_id) REFERENCES department(dept_id)
FOREIGN KEY (school_id) REFERENCES school(school_id)
```
These constraints ensure that:

- A student's dept_id must exist in the department table
- A department's school_id must exist in the school table

### Prevention of Invalid Inserts

Several INSERT statements will fail due to referential integrity violations:
```sql
-- FAILS: dept_id 666 doesn't exist in department table
INSERT INTO student VALUES (1002, 'Tyler A.', 'Female', '2002-08-12', 666);

-- FAILS: Missing school_id reference (also wrong number of columns)
INSERT INTO department VALUES (102, 'Physics', 888);

-- FAILS: dept_id 111 doesn't exist in department table
INSERT INTO student VALUES (1002, 'Nayina', 111);
```

### Prevention of Orphaned Records
```sql
-- FAILS: Cannot delete dept_id 101 because students reference it
DELETE FROM department WHERE dept_id = 101;
```
This DELETE would leave student records "orphaned" (pointing to a non-existent department), so the database blocks it.

### Maintaining Data Consistency
The JOIN query at the end only works correctly because referential integrity guarantees that every dept_id in the student table has a corresponding row in the department table, and every school_id in the department table has a corresponding row in the school table.

## 2. Explain Join in RDBMS/SQL with sample queries

A JOIN is used to retrieve data from multiple tables based on a related column. Joins allow meaningful queries across normalized tables.

### INNER JOIN

INNER JOIN returns only matching records from both tables:

```sql
SELECT s.student_name, d.dept_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id;
```

### LEFT JOIN

LEFT JOIN returns all records from the left table and matching records from the right table. If no match exists, NULL is returned.

```sql
SELECT s.student_name, d.dept_name
FROM student s
LEFT JOIN department d ON s.dept_id = d.dept_id;
```

### RIGHT JOIN

RIGHT JOIN returns all records from the right table and matching records from the left table.

```sql
SELECT s.student_name, d.dept_name
FROM student s
RIGHT JOIN department d ON s.dept_id = d.dept_id;
```

Joins are essential for querying relational data spread across multiple tables.

## 1. Compare Developer API vs User API

A Developer API is designed for programmers or systems to integrate with a platform. It focuses on stability, documentation, authentication, versioning, and automation. Examples include payment APIs, cloud service APIs, and internal service APIs.

A User API is designed to support direct user interactions, often behind web or mobile applications. It focuses on user experience, session handling, and permission control. User APIs typically serve frontend applications rather than external developers.

## 2. Explain components of REST API

A REST API consists of the following components:

- Resources: Represented as URLs (e.g., /students/1)
- HTTP Methods: Define actions (GET, POST, PUT, DELETE)
- Headers: Carry metadata such as authentication and content type
- Request Body: Contains data sent to the server (usually JSON)
- Response Body: Contains returned data
- Status Codes: Indicate request result (e.g., 200, 404, 500)

REST APIs are stateless and rely on standard HTTP protocols.

## 3. Compare each type of HTTP Method

- GET: Retrieves data; safe and idempotent
- POST: Creates new resources; not idempotent
- PUT: Replaces an entire resource; idempotent
- PATCH: Partially updates a resource
- DELETE: Removes a resource; idempotent
- HEAD: Same as GET but without response body
- OPTIONS: Returns supported methods and CORS information

Each method has a specific semantic meaning in RESTful design.

## 4. Explain authentication field in HTTP header

Authentication information is typically sent using the Authorization header.

Common formats include:

- Basic Authentication: Base64-encoded username and password
- Bearer Token: Token-based authentication such as OAuth2 or JWT

## 5. Explain cookies field in HTTP header

Cookies are used to store small pieces of data on the client side.

- Set-Cookie (response header): Sent by server to store cookies
- Cookie (request header): Sent by browser back to server

Cookies are commonly used for session management, authentication, and user preferences. Security attributes include HttpOnly, Secure, and SameSite.

## 6. Explain the purpose of HTTP response headers and why they are necessary

HTTP response headers provide metadata about the response and control how it should be processed by the client.

Common purposes include:

- Defining content type (Content-Type)
- Managing caching (Cache-Control)
- Controlling security (Content-Security-Policy)
- Handling authentication (WWW-Authenticate)
- Enabling cross-origin requests (CORS headers)

Response headers are necessary because they allow browsers, proxies, and clients to correctly interpret, secure, cache, and manage HTTP responses beyond just the response body.

