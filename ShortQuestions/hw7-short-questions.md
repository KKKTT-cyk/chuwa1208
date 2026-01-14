**Compare Developer API vs User API**

Developer API

- Target Audience: Software engineers and developers.
- Purpose: To allow external software to integrate with a service (e.g., Stripe's API for payments).
- Stability: High; changes require versioning so third-party apps don't break.
- Documentation: Extensive public documentation (like Swagger or Postman).

User API

- Target Audience: End-users or client applications.
- Purpose: To power the specific features of an app's interface (e.g., the API a mobile app uses to load your - profile).
- Stability: Agile; often changes frequently as the UI/UX evolves.
- Documentation: Usually internal or proprietary; not intended for public use.

**Explain components of REST API?**

RESTful API components are typically divided into the Request sent by the client and the Response sent back by the server.

1. Components of the API Request

When a client wants to interact with a server, it sends a request containing these four primary elements:

- Endpoint (URL): The specific address used to target a resource. For example, https://api.example.com/v1/users/123.

- HTTP Method (Verb): Defines the action to be performed. Common methods include:

    - GET: Retrieve data (Read).

    - POST: Create a new resource.

    - PUT/PATCH: Update an existing resource.

    - DELETE: Remove a resource.

- Headers: Metadata that provides extra context about the request, such as authentication tokens (Authorization) or the format of the data being sent (Content-Type: application/json).

- Body (Payload): The actual data sent to the server, usually required for POST, PUT, and PATCH requests. It is most commonly formatted in JSON.

2. Components of the API Response

After the server processes the request, it sends back a response consisting of:

- Status Code: A three-digit number indicating the result of the request.

    - 200 OK: Success.

    - 201 Created: A new resource was successfully made.

    - 400 Bad Request: There was an error in the client's request.

    - 404 Not Found: The resource doesn't exist.

    - 500 Internal Server Error: Something went wrong on the server side.

- Response Headers: Information about the server and the data being returned (e.g., Cache-Control or Date).

- Response Body: The data requested by the client or a message confirming the action, typically in JSON or XML format.

**Compare each type of HTTP Method**

To compare them effectively, we look at two critical properties:

- Safe: The request does not change the state of the server (it is "read-only").

- Idempotent: Making the same request multiple times has the same effect as making it once.

| Method  | Purpose          | Safe | Idempotent | Cacheable |
| ------- | ---------------- | ---- | ---------- | --------- |
| GET     | Read data        | ✅    | ✅          | ✅         |
| POST    | Create resource  | ❌    | ❌          | ❌         |
| PUT     | Replace resource | ❌    | ✅          | ❌         |
| PATCH   | Partial update   | ❌    | ⚠️         | ❌         |
| DELETE  | Delete resource  | ❌    | ✅          | ❌         |
| HEAD    | Headers only     | ✅    | ✅          | ✅         |
| OPTIONS | Capabilities     | ✅    | ✅          | ❌         |
| TRACE   | Debugging        | ✅    | ✅          | ❌         |
| CONNECT | Tunnel           | ❌    | ❌          | ❌         |


**Explain authentication field in http header?**

The authentication field in an HTTP header is used to prove the identity of a client (user, application, or service) when making a request to a server. It helps the server verify *who you are* and *what you are allowed to access*.


1. Why Authentication Headers Are Needed

- Prevent unauthorized access to protected resources
- Identify the caller (user or system)
- Enable access control and permission checks
- Support stateless communication (especially in REST APIs)

Without authentication headers, the server cannot reliably distinguish between legitimate and malicious requests.

2. Common Authentication-Related HTTP Headers

`Authorization` Header (Most Important)

The `Authorization` header carries the credentials used for authentication.

**Explain cookies field in http header?**

In an HTTP transaction, "cookies" allow a stateless protocol to remember who you are. While the protocol itself doesn't track state, cookies act like a digital luggage tag that your browser carries back and forth between you and the server.

How the Exchange Works

- The process follows a simple "handshake" cycle:

- The Request: You visit a website (e.g., you log in).

- The Response: The server sends back the requested page along with a Set-Cookie header.

- The Storage: Your browser stores that cookie locally.

- The Subsequent Request: The next time you click a link on that site, your browser automatically attaches the Cookie header to your request.


**Explain the purpose of http response headers? Why it is necessary?**

HTTP response headers are key–value pairs sent by the server to the client (browser or API consumer) as part of an HTTP response.
They provide metadata about the response, not the actual response body itself.

1. Describe the Response Data

They tell the client what kind of data is being returned and how to interpret it.

Examples:

- Content-Type: Data format (JSON, HTML, image, etc.)

- Content-Length: Size of the response body

- Content-Encoding: Compression used (gzip, br)

2. Control Caching Behavior

Response headers define whether and how responses can be cached to improve performance.

Examples:

- Cache-Control

- Expires

- ETag

- Last-Modified

3. Enable Security Controls

They protect clients from common attacks such as XSS, clickjacking, and MIME sniffing.

Examples:

- Content-Security-Policy

- X-Frame-Options

- X-Content-Type-Options

- Strict-Transport-

4. Manage Authentication & Authorization

Response headers communicate authentication status and requirements.

Examples:

- WWW-Authenticate

- Authorization (in responses for token refresh)

- Set-Cookie (for session-based auth)

5. Control Cookies & Sessions

They instruct the browser how to store and send cookies securely.

Example:

- Set-Cookie

6. Handle Redirects

Response headers tell clients where to go next.

Example:

- Location

7. Support Cross-Origin Requests (CORS)

They define whether resources can be accessed from other origins.

Examples:

- Access-Control-Allow-Origin

- Access-Control-Allow-Methods