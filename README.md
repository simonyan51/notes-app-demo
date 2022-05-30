# Notes Application with ETL Processing

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#important-notes">Important Notes</a>
    </li>
    <li>
      <a href="#api">API</a>
    </li>
    <li><a href="#usage">Installation</a></li>
  </ol>
</details>

## About The Project

This application provides CRUD API for user notes, ETL processing, Authentication/Authorization.

The application contains the following components:
* Users microservice: Handles authentication and authorization
* Notes microservice: Handles CRUD operations on user notes
* Notes-ETL: Batch processing job for processing notes data

Tools and Technologies are used:
* Java
* Spring Boot
* Spring Batch
* Spring Security
* Spring JPA
* Postgres SQL
* Apache Avro
* Lombok
* Docker/Docker Compose
* AWS ECS, RDS, ECR, EC2, ASM
* GitHub Actions

Architecture:
* Clean Architecture
* Hexagonal Architecture

![Clean Architecture Diagram](https://huongdanjava.com/wp-content/uploads/2020/10/Clean-Architecture.png)

# Important Notes, Improvements (Please Read This Section!!!)

> In this task, a lot of technologies, which I am not familiar with them, so I tried to integrate them, and in my opinion here is a lot of improvements should be done, I want to count down the followings:

### Users and Notes Microservices:
* Handle exceptions thrown by Spring Security
* Customize Default API responses
* Create **Core** package for common classes
* Write Unit tests
* Integrate Swagger

### Notes ETL:
* Dive deep into ETL processing technologies
* Write more abstract implementation batch processing (you can check branch **abstract-factory-implementation-for-notes-etl**)
* Integrate Docker
* Deploy on Amazon Lambda
* Integrate with Amazon S3
* Implement Triggers for batch processing

### Infrastructure:
* Learn more about AWS and setup secure environment
* Reduce duplications in GitHub Actions workflows

### The Most Challenging Parts
* Spring Security: I tried to create separate Authorization server, but based on time estimation, I decided to use JWT auth
* AWS
* Spring Batch (Apache Parquet)

# API

> For using deployed APIs, please extract envs.zip file. Inside of zip file are stored hosts of deployed Microservices. Use password provided in email message

### Users Microservice:

_**Sign In API:**_
```aidl
curl --location -g --request POST 'http://{users_host}/v1/auth/sign-in' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "{email_provided_in_zip_file}",
    "password": "{password_provided_in_zip_file}"
}'
```

_**User Info API:**_
```aidl
curl --location -g --request GET 'http://{users_host}/v1/auth/info' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {sing_in_token}'
```

### Notes Microservice:

* **Get User Notes API:**
```
curl --location -g --request GET 'http://{notes_host}/v1/notes' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {sing_in_token}'
```

* **Get User Notes By Note Id API:**
```
curl --location -g --request GET 'http://{notes_host}/v1/notes/{id}' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {sing_in_token}'
```

* **Create User Note:**
```aidl
curl --location -g --request POST 'http://{notes_host}/v1/notes' \
--header 'Authorization: Bearer {sign_in_token}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "title",
    "text": "text"
}'
```

* **Update User Note By Note Id API:**
```aidl
curl --location -g --request PUT 'http://{notes_host}/v1/notes/{id}' \
--header 'Authorization: Bearer {sign_in_token}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "title",
    "text": "text"
}'
```

* **Delete User Note By Note Id API:**
```aidl
curl --location -g --request DELETE 'http://{notes_host}/v1/notes/{id}' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {sign_in_token}' \
```

# Installation

### Step 1:
```aidl
If you are want to use deployed version, extract ZIP file and use APIs for testing
```

### Step 2:
```aidl
If you want to use locally, Extract ZIP file and put database credentials in application.yml file
```

### Step 3
```aidl
sh ./build-service.sh
```

### Step 4
```aidl
docker-compose up
```

### For Running Notes-ETL:
```aidl
Run Application.class runnable class
```

