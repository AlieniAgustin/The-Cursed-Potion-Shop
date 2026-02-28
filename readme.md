# 🧪 The Cursed Potion Shop (Spring Boot Learning Project)

## 📖 About This Project
This project was developed as a hands-on exercise to learn and master the fundamentals of **Spring Boot**. It is a complete MVC (Model-View-Controller) web application designed to manage an inventory of magical potions, enforcing strict business rules and data validation.

### 🛠️ Technologies & Concepts Learned
* **Spring Web:** Building web applications, handling HTTP requests (`@GetMapping`, `@PostMapping`), and managing data flow with Controllers.
* **Thymeleaf:** Server-side rendering engine to dynamically generate HTML views.
* **Spring Data JPA & MySQL:** Connecting to a real relational database, mapping Java objects to database tables (`@Entity`), and performing CRUD operations using Repository interfaces.
* **Jakarta Bean Validation:** Automatically validating incoming form data using declarative annotations (`@NotBlank`, `@Min`, `@Max`, `@Size`).
* **Spring Boot DevTools:** Improving the development experience with live reloading.
* **Bootstrap 5:** Designing a clean, responsive, and user-friendly web interface.
* **Comprehensive Testing:**
    * **Unit Testing:** Using **JUnit 5** and **Mockito** (`@Mock`, `@InjectMocks`) to test business logic in the Service layer in complete isolation.
    * **Integration Testing:** Using `@SpringBootTest` and **MockMvc** to simulate HTTP requests, test web endpoints, model attributes, and flash attributes (`RedirectAttributes`).

---

## 🔮 Domain Rules (Business Logic)
The Ministry of Magic has strict regulations for alchemy. Every potion managed by this system follows these rules:
* **Identification:** Every potion has a unique, automatically generated `ID` and a strictly **unique Name**.
* **Effect:** A required description of the potion's magical properties.
* **Volatility Index (Risk Level):** The risk level must be an integer strictly between **1 and 100**.
* **Automatic Legality Assessment:** The Alchemist cannot decide if a potion is legal or not. The system calculates it automatically:
    * If `1 <= Risk Level <= 90` ➡️ The potion is marked as **Legal**.
    * If `Risk Level > 90` ➡️ The potion is classified as **Illegal**.

---

## 🚀 Features
1.  **Catalog View:** A main dashboard displaying all potions currently in the inventory, highlighting illegal ones.
2.  **Brew a Potion (Create):** A form to prepare a new potion. It includes real-time validation feedback and prevents the creation of duplicate names.
3.  **Potion Details:** A specific view to inspect the complete dossier of a single potion by its ID.
4.  **Destroy Potion (Delete):** A mechanism to permanently remove a potion from the database, redirecting with success or error flash messages.

---

## 💻 How to Run the Application

Since this project uses the Maven Wrapper (`mvnw`), you don't need to install Maven globally on your system. These instructions are tailored for **Linux / VS Code / Java 21**.

### 1. Database Setup
Ensure you have MySQL running on your local machine. Create an empty database named `cursed_potions_db`.

**⚠️ SECURITY NOTE:** For security reasons, real database credentials are not tracked in this repository. You must configure your local `src/main/resources/application.properties` file with your own MySQL username and password:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cursed_potions_db
spring.datasource.username=YOUR_MYSQL_USERNAME_HERE
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE
spring.jpa.hibernate.ddl-auto=update
```

### 2. Start the Server
Open your integrated terminal (e.g., in VS Code) at the root of the project folder and run:
```
./mvnw spring-boot:run
```

### 3. Access the Web App
Once the console shows that the application has started, open your web browser and navigate to:
http://localhost:8080/catalog

### 4. Run Tests
To verify that all business rules and web controllers are working correctly, you can run the automated test suite (Unit and Integration tests) by executing:
```
./mvnw test
```
