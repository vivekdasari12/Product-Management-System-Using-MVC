🧩 Product Management System Using MVC

🧠 Project Overview

The Product Management System is a full-stack web application built using the Spring Boot MVC framework.
It provides a structured and efficient way to manage product data — allowing users to add, edit, delete, view, and store product details in a database.
This project demonstrates a complete end-to-end implementation of the Model–View–Controller (MVC) architecture using Spring Boot, integrating front-end templates with a robust Java-based backend and a relational database.

🎯 Project Objective
The main goal of this project is to develop a web-based system that simplifies the process of managing product information.
Instead of manually keeping track of product details, the system automates data storage, retrieval, and management through an interactive interface.

  * This helps organizations or administrators:
  * Maintain consistent product records
  * Perform CRUD operations easily (Create, Read, Update, Delete)
  * Upload product images
  * Ensure data integrity and efficiency

🧩 Key Functionalities

  * Add Product — Allows the user to insert a new product with all details (name, brand, price, category, etc.).
  * View Products — Displays a dynamic list of all products stored in the database.
  * Edit Product — Enables updating of existing product information.
  * Delete Product — Removes unnecessary or outdated product entries.
  * Upload Images — Supports product image uploads to enhance visualization.
  * Error Handling — Manages invalid inputs and unexpected actions gracefully.

🧱 Architecture Used — MVC Pattern

This project is designed based on the MVC (Model-View-Controller) architecture:

  * Model → Represents the data and database layer (using JPA Entities and Repository).
  * View → Represents the user interface created using Thymeleaf templates.
  * Controller → Handles user requests, interacts with the service layer, and sends data back to the view.

This separation of layers increases code maintainability, scalability, and modularity.

⚙️ Technologies Used
🖥️ Backend Technologies

  * Java 17 — Core programming language used for development.
  * Spring Boot — Framework used to build and configure the application easily.
  * Spring MVC — Handles the web layer using controllers, models, and views.
  * Spring Data JPA — Simplifies database access using repository interfaces.
  * Hibernate — ORM (Object Relational Mapping) framework that interacts with the database.

💾 Database Technologies

  * MySQL Database — Stores product data such as product name, price, description, and image path.
  * JPA/Hibernate — Automatically manages tables and relationships in the database.

🎨 Front-End Technologies

  * Thymeleaf — Template engine integrated with Spring Boot for rendering dynamic web pages.
  * HTML5, CSS3 — Used to design and style the web pages (add, edit, and list pages).

🧰 Development Tools

 * Spring Tool Suite (STS) — IDE used for coding, debugging, and running the application.
 * Maven — Build automation tool used to manage dependencies and project structure.
 * Git & GitHub — Used for version control and storing the project repository.

🔍 Project Workflow Explanation

  1. User Interaction (View Layer)
      Users interact with the system through HTML pages created using Thymeleaf templates.
      Each page provides a form or table for adding, editing, or viewing products.
  
  2. Controller Layer
    When a user performs an action (like submitting a form), the Controller receives the request, processes it, and interacts with the Service Layer to perform the required business logic.
  
  3. Service Layer
    The Service acts as the intermediary between the controller and repository.
    It contains the main business logic — such as validating product data or handling image uploads.
  
  4. Repository Layer
    The Repository connects directly with the database using Spring Data JPA, performing CRUD operations automatically without writing SQL queries.
  
  5. Database Layer
    All product details are stored in the MySQL database.
    The system automatically updates the database schema using the JPA mapping configurations.

🧠 Project Benefits

  * Reduces manual data entry and management time.
  * Provides an easy and interactive UI for handling products.
  * Demonstrates practical implementation of Spring Boot MVC architecture.
  * Ensures data consistency and scalability for real-world applications.
  * Can be easily extended to include authentication, search, and reporting features.

📈 Future Enhancements

  * Add user login and authentication for admin access.
  * Implement REST API endpoints for external integration.
  * Introduce pagination and search filters for product listings.
  * Enable cloud storage for uploaded images.
  *  Add unit testing using JUnit and Mockito.
