# Assignment 7 Yichao Chen

### Q1 Explain Referential Integrity in RDBMS/SQL with sample queries.

Regerential Integrity is a rule that ensures relations between tables remains consistent.
The foreign key in a child table must reference an existing primary key in a parent table
```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    birth_date DATE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id)
);
```
In this example three tables  are us `school`, `department` and `student`
- `department.school_id` is a foreign key referencing `school.school_id`
- `student.dept_id` is a foreign key referencing `department.dept_id`

We need create a school table
```sql
CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);
```

Insert invalid data (fails due to referential integrity):
```sql
INSERT INTO student VALUES (1002, 'Tyler A.', 666);
```
This insertion fails because `dept_id = 666` does not exist in the department table.

```sql
INSERT INTO department VALUES (102, 'Physics', 888);
```
Fails: not have school_id reference 

```sql
INSERT INTO student VALUES (1002, 'Nayina', 111);
```
Fails: not match to exists department_id.

```sql
DELETE FROM department WHERE dept_id = 101;
```
Fails: since `student.dept_id` is a foreign key referencing `department.dept_id`, deleting the parent row would break referential integrity and create orphan records.\
Therefore, the database prevents this delete operation.

### Q2 Explain Join in RDBMS/SQL with sample queries.
In RDBMS (relational database management systems), JOIN is used to combine data from multiple tables based on the relationships between them (usually primary key/foreign key relationships),\
allowing for queries that retrieve information across tables.

`student.dept_id` - > `department.dept_id` A typical one-to-many relationship. A department can have many students.

```sql
-- Manual 'JOIN' (NO join keyword)
SELECT 
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s, department d, school sc
WHERE 
    s.dept_id = d.dept_id
    AND d.school_id = sc.school_id;

-- Join
SELECT 
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id
JOIN school sc ON d.school_id = sc.school_id;
```
In the explicit JOIN example, only one base table (student) is specified in the FROM clause.\
Other related tables (department and school) are connected using JOIN ON conditions.

#### INNER JOIN
INNER JOIN returns only the rows that have matching values in both tables.
Result: Only students who belong to an existing department are returned.
#### FULL JOIN
FULL JOIN returns all rows from both tables. If there is no match on one side, NULL is returned for that side.\
Result: All students and all departments are included, regardless of matching.
#### LEFT JOIN
LEFT JOIN returns all rows from the left table, and the matching rows from the right table.\
If there is no match, `NULL` is returned for columns from the right table.\
Result: All students are returned, including those without a department.
#### RIGHT JOIN
RIGHT JOIN returns all rows from the right table, and the matching rows from the left table.\
If there is no match, `NULL` is returned for columns from the left table.\
Result: All departments are returned, including those without students.

### Q3 Compare Developer API vs User API
Developer API:\
It is designed for developer integrate with other software applications
- Developer API is mainly used by developers for building applications or services.
- Developer API emphasizes flexibility and extensibility.\

User API:\
It is designed for users or client-facing application. It focuses on usability and user interaction
- User API is used by client applications to serve end-user functionality.
- User API emphasizes simplicity, security, and user experience.

### Q4 Explain components of REST API?
- Endpoint: The URL that identifies a resource
- HTTP Methods: Define actions on resources
- Headers: Contain metadata such as authentication tokens, content type, and cookies.
- Request Body: Contains data sent from the client to the server (usually in JSON format).
- Response: Includes status codes, headers, and response body returned by the server.
### Q5 Compare each type of HTTP Method
- GET: Read, Retrieve data from the server.
- POST: Create, Create a new resource.
- PUT: Update (Replace), Replace an existing resource.
- PATCH: Update (Partial), Modify part of an existing resource.
- DELETE: Delete, remove a resource.
### Q6 Explain authentication field in http header?
The Authentication field in the HTTP header is used to verify the identity of the client making the request.
- Ensures that only authorized users can access protected resources
- Prevents unauthorized access
- Supports mechanisms such as Basic Auth, Bearer Token, OAuth, and JWT
### Q7 Explain cookies field in http header?
The Cookie header is used to store and send small pieces of data between the client and server.
- Maintain user sessions
- Store user preferences
### Q8 Explain the purpose of http response headers? Why it is necessary?
HTTP response headers provide additional information about the server response.
Helps the client understand how to process the response. Improves security, performance, and compatibility.\
Controls caching, content format, and session management

