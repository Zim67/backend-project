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
