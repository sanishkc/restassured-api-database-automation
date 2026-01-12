**RestAssured API Automation Framework**

[![API Tests](https://github.com/sanishkc/restassured-api-database-automation/actions/workflows/api-tests.yml/badge.svg)](https://github.com/sanishkc/restassured-api-database-automation/actions/workflows/api-tests.yml)

**Overview:**

This repository contains an API test automation framework built using RestAssured (Java) to automate and validate REST API endpoints deployed on Spring Boot with a Render PostgreSQL backend. The framework currently demonstrates GET, POST, and DELETE operations on user data and is designed to showcase modern API automation best practices, clean architecture, and CI/CD readiness.

**Tech Stack:**

 - RestAssured – API automation  
 - Java 
 - Maven – Build & dependency management  
 - TestNG – Test framework  
 - PostgreSQL – Database (Render Managed)  
 - Git & GitHub – Version control  
 - CI/CD Ready – GitHub Actions / Jenkins compatible  

**Test Coverage:**

  - GET /users API  
    - Status code validation
    - Response body validation
    - Response header validation
      
  - POST /users
    - Create a new user with JSON payload 
    - Status code validation
    - Response body validation
    - Response header validation
   
  - DELETE /users/{id}
    - Delete the latest user dynamically based on highest ID 
    - Status code validation
    - Response header validation

**Author:** Sanish Chandran | QA Automation Lead |📍 Cape Town, South Africa

**LinkedIn:** https://www.linkedin.com/in/sanishchandran/

**GitHub:** https://github.com/sanishkc/restassured-api-database-automation

**Purpose:** This project is created for learning, demonstration, and professional portfolio purposes. It showcases hands-on expertise in API automation using RestAssured, integration with Spring Boot APIs, working with cloud databases (Render PostgreSQL), and modern QA engineering practices in enterprise environments.
