# Product Management Application

This project is a **Product Management Application** built with Java, Spring Boot, and JSF (JavaServer Faces). The application allows users to manage products, including adding, updating, and deleting product information.

## Features

- View a list of products
- Add new products
- Update existing products
- Delete products
- Validate user input
- Responsive UI using PrimeFaces

## Technologies

- Java
- Spring Boot
- Spring MVC
- JSF (JavaServer Faces)
- PrimeFaces
- PostgreSQL
- RESTful API

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven
- PostgreSQL
- An IDE (e.g., IntelliJ IDEA, Eclipse)

### Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Zaid-R/product-management.git
   cd product-management

2. **Configure PostgreSQL:**

* Create a new database in PostgreSQL.

* Update your `application.properties` with your database credentials:

```bash 
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```
3. **Build the application:**
```bash 
mvn clean install
```
4. **Run the application:**
```bash 
mvn spring-boot:run
```

5. **Access the application:**

    Open your browser and go to `http://localhost:5001/products.xhtml`

## Contact
For questions or issues, please reach out to the maintainer:

- **Maintainer Name**: Zaid Rajab
- **Email**: zaid.rjab1@gmail.com
- **GitHub**: [Zaid-R](https://github.com/Zaid-R)