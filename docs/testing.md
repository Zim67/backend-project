### testing.md

```markdown
# Testing Procedures

## Unit Tests
- Ensure all public methods are tested.
- Use JUnit 5 for writing tests.
- Mock dependencies where necessary using Mockito.

## Integration Tests
- Write integration tests to verify the interaction between components.
- Use the Spring Boot test framework for integration testing.

## Running Tests
- Use the following command to run all tests:
  ```bash
  ./mvnw test

## Testing Airports Feature
1. **GET `/airports`**: Verify it returns a list of all airports.
2. **GET `/airports/search?name=`**: Test with various names to check search functionality.
3. **POST `/airports`**: Add a new airport and verify it's saved.
4. **PUT `/airports/{id}`**: Update an airport and check if the changes are applied.
5. **DELETE `/airports/{id}`**: Delete an airport and confirm it's removed.

