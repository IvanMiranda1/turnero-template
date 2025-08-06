# Turnero Template

A reusable and standardized base template for appointment management systems, built with Spring Boot and designed following clean architecture principles (ports and adapters). This project aims to normalize and streamline the creation of new turnero-style applications by providing a consistent and well-structured starting point.

## Purpose

This repository was created to serve as a cloneable template, allowing developers to reuse a preconfigured skeleton that follows best practices in structure, modularization, and technology integration. It promotes maintainability, scalability, and clean separation of concerns.

## Tech Stack

- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **Lombok**
- **Jakarta Bean Validation**
- **MapStruct**

## Key Features

- Hexagonal architecture (Ports and Adapters)
- Clear separation between domain, infrastructure, and application layers
- Input validation using standard annotations
- Basic authentication and authorization setup
- Entity-DTO mapping using MapStruct
- Interfaces for persistence decoupled from implementation
- Ready to be cloned, extended, and reused

## Project Structure
com/
└── app/
    ├── TurneroApplication.java
    ├── application/
    │   └── usecase/
    │       └── EntityService.java
    ├── domain/
    │   ├── model/
    │   │   └── Entity.java
    │   └── port/
    │       └── EntityRepository.java
    └── infrastructure/
        ├── adapter/
        │   ├── persistence/
        │   │   └── EntityPostgresAdapter.java
        │   └── rest/
        │       └── EntityController.java
        ├── dto/
        │   └── EntityDTO.java
        └── persistence/
            ├── entity/
            │   └── EntityEntityJPA.java
            └── repository/
                └── EntityJpaRepository.java

## Requirements

- Java 21+
- Maven or Gradle
- PostgreSQL

## Getting Started

1. Clone the repository:
   ```bash
   git clone git@github.com:IvanMiranda1/turnero-template.git
