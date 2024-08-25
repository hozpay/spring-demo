# Spring Boot Application

This is a simple Spring Boot application called containerize with 3 GET endpoints that return JSON responses.  
The application can be easily packaged into a Docker container. 

### Purpose of this application is to create image and then use it in Kubernetes tutorial.


## Overview

The application has three endpoints:

1. **/greeting** - Returns a greeting message. You can pass a name as a query parameter to customize the greeting.
2. **/user** - Returns a list of usernames.
3. **/userid** - Returns a list of user IDs.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java 17** or higher
- **Maven 3.8.1** or higher
- **Docker** (if you want to containerize the app)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/tauseef-ameen/containerize.git
2. Navigate to the project directory:
    ```bash
   cd containerize
3. Build the project:
    ```bash
   mvn clean install
   
## Running the Application

Start the application:
```bash
mvn spring-boot:run
```

## Endpoints

### 1. Greeting

- **URL:** `/greeting`
- **Example:**
  - **Default:**
    ```bash
    curl http://localhost:8080/greeting
    ```
  - **Custom:**
    ```bash
    curl http://localhost:8080/greeting?name=User
    ```

### 2. User List

- **URL:** `/user`
- **Example:**
  ```bash
  curl http://localhost:8080/user

### 3. User ID List

- **URL:** `/userid`
- **Example:**
  ```bash
  curl http://localhost:8080/userid
  
## Docker Image Creation
To create a Docker image, simply run:
   ```bash
   mvn clean install -Pcreateimage
   ```

### Docker Image Creation
Once the image is built, you can run it with:

  ```bash
  docker run -p 8080:8080  medium/docker-containerize:1.0.0-SNAPSHOT
   ```
### Contributing
Feel free to fork this repository and submit a pull request if you have any improvements or fixes!