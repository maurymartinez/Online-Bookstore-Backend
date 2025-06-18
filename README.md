# 📚 Online Bookstore API

A secure RESTful API built with Spring Boot 3 and Java 17, featuring JWT-based authentication and a simple book management system.

---

## ✨ Features

* User registration and login
* Stateless authentication using JSON Web Tokens (JWT)
* Protected API endpoints with role-based access (`USER`)
* Book management:

    * Retrieve all books

---

## 💠 Technologies

* Java 17
* Spring Boot 3
* Spring Security
* JJWT (`io.jsonwebtoken`)
* H2 In-Memory Database
* Lombok
* Hibernate (JPA)

---

## 🔐 Authentication Endpoints

* `POST /api/auth/register`: Register a new user
* `POST /api/auth/login`: Authenticate and receive a JWT token

### 👤 Default User

For convenience, the following user is available:

* **Email:** `user@example.com`
* **Password:** `user`

> Useful for testing with in-memory H2 database. The user is inserted at startup via `data.sql`.

---

## 📚 Book Endpoints (Requires JWT)

* `GET /api/books`: Retrieve all books

---

## ▶️ Run the Application

### Using Maven

1. **Build the project:**

   ```bash
   mvn clean package
   ```

2. **Run the generated JAR file:**

   ```bash
   java -jar target/bookstore-0.0.1.jar
   ```

   > Adjust the JAR filename if needed.

3. **Access the API:**

   Visit [http://localhost:8080/api](http://localhost:8080/api)

---

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
