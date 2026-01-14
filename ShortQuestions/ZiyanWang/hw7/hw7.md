Explain Referential Integrity in RDBMS/SQL with sample queries.
- Referential Integrity ensures that relationships between tables in a relational database remain consistent and valid.
- A foreign key in a child table must always refer to an existing primary key in a parent table, or be NULL.
- e.g.
```sql
--parent table: be referenced
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(50)
);

--children table: refer to parent table
CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

--Insert
INSERT INTO Customers VALUES (1, 'Alice');
INSERT INTO Orders VALUES (101, 1);

--Delete
FOREIGN KEY (customer_id)
REFERENCES Customers(customer_id)
ON DELETE CASCADE
```

Explain Join in RDBMS/SQL with sample queries.
- JOIN is used to combine rows from two or more tables based on a related column (usually a primary key & foreign key).
- Types: INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL OUTER JOIN
- e.g. INNER JOIN
```sql
--Only returns rows that match in both tables
/* 
| customer_id | name  |
| ----------- | ----- |
| 1           | Alice |
| 2           | Bob   |

| order_id | customer_id |
| -------- | ----------- |
| 101      | 1           |
| 102      | 1           |
| 103      | 3           |
*/
SELECT c.name, o.order_id
FROM Customers c
INNER JOIN Orders o
ON c.customer_id = o.customer_id;

```

Answer following questions:
Compare Developer API vs User API
-Developer API:
- - Methods in SDK (Java JDK)
- - REST API, GraphQL API, SOAP API, RPC API… (they are all Http API）
-User API（public API）：
- - REST API. GraphQL API, SOAP API, RPC API…

Explain components of REST API?
- A REST API is made up of several key components that define how clients communicate with servers.
- Endpoint (URL / Path): The address of a resource.
- HTTP method (Verb): Defines what action to perform on a resource.
- Headers: Metadata about the request or response.
- Request Body: The data sent from client to server.
- Query Parameters: Optional parameters to filter, sort, or paginate data.
- Response Body: The data returned by the server.
- HTTP Status Codes: Indicates the result of the request.
- Authentication & Authorization: Verifies who you are and what you can access.
- (REST Principle) Statelessness: Each request is independent.

Compare each type of HTTP Method
- GET:
- - Retrieve data: GET /users/123
- - No request body
- - Does not change server state
- - Cacheable
- - Safe, idempotent
- POST:
- - Create a new resource or trigger action: POST /users
- - Creates new resource
- - Server decides resource ID
- - Can have side effects
- - Not safe, not idempotent
- PUT:
- - Replace an existing resource entirely: PUT /users/123
- - Full replacement
- - Repeating gives same result
- - Not safe, idempotent
- PATCH:
- - Partial update: PATCH /users/123
- - Updates specific fields
- - More efficient than PUT
- - Not safe, Usually not idempotent
- DELETE:
- - Remove a resource: DELETE /users/123
- - Deletes resource
- - Multiple calls → same result
- - Not safe, idempotent
- HEAD:
- - Same as GET but without response body: HEAD /users/123
- - Used to check existence
- - Used for caching checks
- - Safe, idempotent
- OPTIONS:
- - Ask server what methods are supported: OPTIONS /users
- - Used in CORS preflight
- - Returns allowed methods
- - Safe, Idempotent

Explain authentication field in http header?
- Authentication field in an HTTP header is used to prove who the client is when making a request to a server.
```http request
Authorization: <type> <credentials>
```
- Types:
- - Bearer Token: Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
- - Basic Authentication: Authorization: Basic dXNlcjpwYXNzd29yZA==
- - API Key Authentication: Authorization: ApiKey abc123  or  X-API-Key: abc123
- - OAuth 2.0: Authorization: Bearer <access_token>

Explain cookies field in http header?
- Cookies are small pieces of data stored in the browser and sent to the server in HTTP headers to maintain state between requests.

Explain the purpose of http response headers? Why it is necessary?
- Purpose: HTTP response headers provide metadata about the response, telling the client how to interpret, handle, cache, secure, and process the response body.
- Why necessary: 
- - Because the response body alone is not enough.
- - Headers give context and rules for how the client should treat the data.
- Core Purposes:
- - Describe the Response Data: Tells the client what kind of data it received and how large it is.
- - Control Caching Behavior: Controls whether the browser can reuse cached data or must revalidate.
- - Enable Security: Protects against XSS, MITM, and other attacks.
- - Manage Authentication & Sessions: Tells clients how to authenticate or maintains session state.
- - Support Cross-Origin Requests (CORS): Controls whether browsers allow cross-origin access.
- - Communicate Status & Redirection: Informs client about redirects or when to retry.