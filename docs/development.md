### development.md

```markdown
# Development Process

## Project Structure
- **src/main/java**: Main application code.
- **src/test/java**: Test code.
- **docs**: Documentation files.

## Coding Standards
- Follow the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

## Branching Strategy
- Use `main` for production-ready code.
- Create feature branches for new features and fixes.
- Use pull requests to merge changes into `main`.

## Setting Up Your Development Environment
1. Clone the repository and navigate to the project directory.
2. Ensure you have the prerequisites installed.
3. Import the project into your preferred IDE.

## Running the Application
- Use the following command to start the application:
  ```bash
  ./mvnw spring-boot:run

## Airports Feature
This feature allows managing airports in the system.

### Endpoints
- **GET `/airports`**: Retrieves all airports.
- **GET `/airports/search?name=`**: Searches for airports by name.
- **POST `/airports`**: Adds a new airport.
- **PUT `/airports/{id}`**: Updates an existing airport.
- **DELETE `/airports/{id}`**: Deletes an airport by ID.

## Aircrafts Endpoints
- **GET `/aircrafts`**: Retrieves all aircrafts.
- **GET `/aircrafts/search?airline=`**: Searches for aircrafts by airline.
- **POST `/aircrafts`**: Adds a new aircraft.
- **PUT `/aircrafts/{id}`**: Updates an existing aircraft.
- **DELETE `/aircrafts/{id}`**: Deletes an aircraft by ID.

The code for the aircrafts functionality can be found in the `AircraftController`, `AircraftService`, and `AircraftRepository` classes.

## Generic System of Updateables and UpdateData

The project has been refactored to use a generic system based on `Updateables` and `UpdateData`. This change simplifies the logic and ensures consistency across service methods. The new `UpdateableService` class handles the generic update, delete, and add operations, allowing for easier maintenance and scalability.

### Changes:
- Introduced `Updateable` and `UpdateData` interfaces.
- Simplified service methods to use the generic system.
- Refactored existing entities to implement the new interfaces.

# Development Guide

## New Features
### Flights Endpoint
The flights feature has been added, which allows for the creation, updating, and deletion of flights. The following endpoints are included:

- **GET /flights:** Returns all flights.
- **POST /flights/create:** Creates a new flight with specified aircraft, to/from airports, gates, and arrival/departure times.
- **POST /flights/{id}:** Updates an existing flight.
- **DELETE /flights/{id}:** Deletes a specific flight.



