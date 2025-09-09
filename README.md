# 🛡️ Backend Tasks Project - User Authentication & Management

A **Spring Boot backend application** for **user registration, login, role-based access control**, **JWT authentication**, and **email-based password reset**. Designed for easy **CRUD operations** on users and secure **role management** (ADMIN vs USER).

---

## 🚀 Key Features

- **User Registration & Login**
  - New users are automatically assigned the **USER** role.
  - Only one **ADMIN** exists in the system.
  - **JWT token** generated upon login for secure API access.

- **Forgot Password & Email Verification**
  - Sends a **verification code** to user's email for password reset.
  - Allows **secure password updating** after verification.

- **CRUD Operations**
  - Full **Create, Read, Update, Delete** functionality for users.
  - Uses **UserMapper** for **DTO ↔ Entity** conversion.

- **Security**
  - **JWT token-based authentication** for protected endpoints.
  - **Role-based access control** for ADMIN vs USER.
  - Global exception handling with meaningful error responses.

