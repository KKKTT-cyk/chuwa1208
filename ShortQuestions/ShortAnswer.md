# HTTP & REST API Core Concepts

## 1. Compare Developer API vs User API

| Aspect | Developer API                   | User API                        |
|---|---------------------------------|---------------------------------|
| Target audience | Developers / services           | End users (browser, mobile app) |
| Purpose | System-to-system integration    | User Service                    |
| Authentication | API keys, OAuth, service tokens | Cookies, session, OAuth         |
| Rate limiting | Common and strict               | Usually lighter                 |
| Data format | JSON / XML                      | HTML / JSON                     |
| Stability | Strong backward compatibility   | Can change with UI              |
| Example | SDK standard libaris; REST API  | Login / profile APIs            |

**Summary:**
- Developer APIs are designed for **programmatic access and automation**.
- User APIs support **interactive user-facing workflows**.

---

## 2. Components of a REST API

### 1. Resource
- Represents an entity
- Identified by a URL

### 2. HTTP Method Type
- Define the action executed on the resources
- GET, POST, PUT, DELETE, etc. 

### 3. Request Header
- Metadata (Authorization, Content-Type, etc.)

### 4. Request Body
- Request Payload sent to the server

### 5. Response
- HTTP status code (200, 400, 404, etc.)
- Response Header
- Response Body

---

## 3. Compare HTTP Methods

| Method   | Purpose                | Idempotent | Safe (not modify server state) | Typical Use              |
|----------|------------------------|------------|--------------------------------|--------------------------|
| GET      | Retrieve data/resource | Yes        | Yes                            | Fetch resources          |
| POST     | Create resource        | No         | No                             | Create / trigger actions |
| PUT      | Replace resource       | Yes        | No                             | **Full update**          |
| PATCH    | Partial update         | No         | No                             | **Partial update**       |
| DELETE   | Remove resource        | Yes        | No                             | Delete                   |
| HEAD     | Metadata only          | Yes        | Yes                            | Check existence          |
| OPTIONS  | Supported methods      | Yes        | Yes                            | CORS / capability check  |

---

## 4. Authentication field in HTTP Header

### Common Authentication Types
- Bearer Token 
  - Used in OAuth 2.0/JWT-based authentication
  - Token represents the authenticated user or service
  - Stateless (not depend on server session)
- Basic Authentication
  - Encodes `username:password` using Base64
  - Not secure without HTTPS
- API Key
  - Used for **service-to-service** API authentication
  - Often included in a custom header field

---

## 5. Cookies field in HTTP Header

Cookies in HTTP headers allow servers to maintain user state across stateless HTTP requests by storing and automatically sending session-related data.

- server set cookies and include it in response
- browser receive and store cookie
- in future API calls, include cookie in request header

---

## 5. Purpose of http response headers
tell client :
- how to interpret the response (`Content-Type`, `Content-Encoding`)
- how to handle caching (`Cache-Control`, `Expires`)
- how to manage security(`Set-Cookie`)
- how to control client behavior(`Location`, `Retry-after`)

why necessary:
- Separate metadata and data
- Correct client behavior
- Security enforcement