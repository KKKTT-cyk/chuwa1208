### Q1\. Explain Referential Integrity in RDBMS/SQL with sample queries.
* Referential integrity is a database concept used to ensure that the relationships between tables remain consistent and valid. It guarantees that data cannot be entered or deleted if it contradicts the defined rules of the database schema.
* In the script (`SQL_Referential_Integrity.sql`), this is enforced using Foreign Key constraints.
* Implementation (Constraint Definition): The schema establishes a dependency where the `student` table (child) relies on the `department` table (parent).
```
FOREIGN KEY (dept_id) REFERENCES department(dept_id)
```
* This constraint mandates that every value in the `student.dept_id` column must correspond to an existing `dept_id` in the `department` table.
* Enforcement Mechanisms:
1. Insert Restriction: The database prevents the insertion of "orphan" records. In the script, the attempt to insert a student with `dept_id` 666 fails because that ID does not exist in the parent `department` table.

```
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES (1002, 'Tyler A.', 'Female', '2002-08-12', 666);
```

2. Delete Restriction: The database protects against accidental data loss. The script attempts to delete the department with `dept_id` 101. 

```
DELETE FROM department WHERE dept_id = 101;
```

* This operation is blocked because the student "John Zhang" is currently assigned to `dept_id` 101. Deleting the department would leave the student record pointing to a non-existent reference, violating integrity.

---

### Q2\. Explain Join in RDBMS/SQL with sample queries.
* A Join is an operation used to combine rows from two or more tables based on a related column. It allows for the retrieval of a comprehensive dataset spanning multiple normalized tables.
* Inner Join: Returns records only when there is a match in both tables.

```
SELECT s.student_name, d.dept_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id;
```
-> Retrieves students who are assigned to a valid department. Students with `NULL` department IDs are excluded.

* Left (Outer) Join: Returns all records from the left table (`student`), and matched records from the right (`department`).
```
SELECT s.student_name, d.dept_name
FROM student s
LEFT JOIN department d ON s.dept_id = d.dept_id;
```
-> Includes students like "Alice" and "Bob" who have `NULL` department IDs, ensuring no students are omitted from the report.

* Right (Outer) Join: Returns all records from the right table (`department`), and matched records from the left (`student`).
```
SELECT s.student_name, d.dept_name
FROM student s
RIGHT JOIN department d ON s.dept_id = d.dept_id;
```
-> Ensures all departments are listed, even those like "Physics" (from `SQL_Referential_Integrity.sql`) that currently have no students.

* Full Outer Join (Simulated): Returns all records when there is a match in either table. Since MySQL lacks a native `FULL JOIN` command, the script simulates it using `UNION`:
```
SELECT s.student_name, d.dept_name FROM student s LEFT JOIN department d ON s.dept_id = d.dept_id
UNION
SELECT s.student_name, d.dept_name FROM student s RIGHT JOIN department d ON s.dept_id = d.dept_id;
```
-> Produces a complete list of all students and all departments, regardless of association.

--------

### Q1\. Compare Developer API vs User API
* The distinction between these two often lies in the intended consumer of the interface.

* Developer API (Public/Open API): These are interfaces designed specifically for third-party software engineers to build applications on top of a platform. 
* They prioritize clear documentation, version stability, and authentication standards (like OAuth).

* Example: I use the Stripe API to integrate payments into my own website, or the Google Maps API to show a location in my app.

* User API (Private/Internal API): These are the internal endpoints that a company’s own frontend (mobile app or website) calls to perform actions for the end-user. 
* They are often optimized for the specific UI requirements of that product and may change frequently without public notice.

* Example: When I click "Like" on Instagram, the app calls an internal User API endpoint to update the database.

### Q2\, Explain components of REST API?
* A REST (Representational State Transfer) API is not just a URL; it relies on a specific set of architectural constraints and components to function correctly.

* Resources (URIs): The "nouns" of the API. These are the unique addresses for data objects (e.g., `/api/users/123`).

* HTTP Methods (Verbs): The "verbs" that define the action to be performed on the resource (GET, POST, etc.).

* Headers: Metadata sent with the request or response. They handle authentication, content type specification (JSON vs XML), and caching.

* Body (Payload): The actual data being sent or received, usually formatted as JSON.

* Status Codes: Standard integers (200, 404, 500) that tell the client the result of the request.

### Q3\. Compare each type of HTTP Method
| Method | Purpose | Safe? | Idempotent? | Description |
| :--- | :--- | :--- | :--- | :--- |
| **GET** | **Retrieve** | Yes | Yes | Fetches data without changing anything on the server. Repeating it is safe. |
| **POST** | **Create** | No | No | Submits new data to the server. Running it twice creates two records. |
| **PUT** | **Replace** | No | Yes | Updates a resource completely. Running it twice results in the same state. |
| **PATCH** | **Update** | No | No* | Partially updates a resource (e.g., changing just an email address). |
| **DELETE** | **Remove** | No | Yes | Deletes a resource. Running it twice produces the same result (the item is gone). |

* PATCH is technically non-idempotent by definition, though often implemented as idempotent.

### Q4\. Explain authentication field in http header?
* The `Authorization` header is the primary vehicle for verifying a user's identity without requiring them to log in for every single click.

* How it works: When I send a request, I include this header to prove I have permission to access the resource.

* Common Format: `Authorization: <Scheme> <Credentials>`

* Typical Schemes:

1. Basic: The credentials are a Base64-encoded username/password, which is less secure.

2. Bearer: The credential is a "Token", like a JWT, that the server issued to me when I first logged in. This is the industry standard for modern APIs.

### Q5\. Explain cookies field in http header?
* Cookies are small pieces of data used to maintain state in an otherwise stateless HTTP protocol.

* The Workflow:

1. The Server sends a `Set-Cookie` header in a response.

2. The Browser stores this data.

3. For every subsequent request to that same domain, the Browser automatically includes the `Cookie` header.

* Usage: It is primarily used for Session Management (keeping me logged in) and Tracking (analytics).

* Security: Flags like `HttpOnly` (cannot be accessed by JavaScript) and `Secure` (only sent over HTTPS) are critical for preventing attacks like XSS (Cross-Site Scripting).

### Q6\. Explain the purpose of http response headers? Why it is necessary?
* Response headers are the "shipping label" of the data package. They tell the client (browser or code) exactly how to handle the payload inside the box.

* Why are they necessary? Without headers, the client is blind. It receives a stream of bytes but doesn't know:

1. Format: Is this an image, a PDF, or a JSON text file? (Handled by `Content-Type`).

2. Security: Is this site allowed to load scripts from other domains? (Handled by `CORS` headers).

3. Efficiency: Should I save this file for later, or download it again next time? (Handled by `Cache-Control`).

* If these headers are missing or incorrect, the application will break (e.g., rendering text as code instead of a webpage) or become vulnerable to security threats.



