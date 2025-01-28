# ChatApp Microservice

## Overview

**ChatApp Microservice** is a web-based chat application built with Java, utilizing Spring Boot and MySQL. It follows a microservice architecture and supports real-time communication through WebSocket integration. The application ensures data persistence by storing chat data in a MySQL database.

## Features

- **Real-Time Communication**: Utilizes WebSocket for instant messaging between users.
- **Microservice Architecture**: Separates functionalities into distinct services for scalability and maintainability.
- **Data Persistence**: Stores chat data in a MySQL database to ensure message history is retained.
- **Future Enhancements**:
  - Integration of a notification system.
  - Support for media transfer.
  - Administrative functionalities.

## Requirements

- **Java**: Version 8 or higher.
- **Spring Boot**: For building and running the application.
- **MySQL**: For data storage.
- **WebSocket**: For real-time communication.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/sankhadeeproycbowdhury/ChatApp-Microservice.git
   cd ChatApp-Microservice
   ```

2. **Set Up MySQL Database**:
   - Ensure MySQL is installed and running.
   - Create a new database:
     ```sql
     CREATE DATABASE chatapp;
     ```
   - Update the application properties with your MySQL credentials, typically found in `src/main/resources/application.properties`:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/chatapp
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build and Run the Application**:
   - Build the application using Maven:
     ```bash
     mvn clean install
     ```
   - Run the application:
     ```bash
     java -jar target/chatapp-microservice-0.0.1-SNAPSHOT.jar
     ```
   The application will start on `http://localhost:8080` by default.

## Usage

- **Access the Application**: Navigate to `http://localhost:8080` in your web browser.
- **Chat Functionality**: Register or log in to start real-time chatting with other users.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License.

## Author

[Sankhadeep Roy Choudhury](https://github.com/sankhadeeproycbowdhury)

