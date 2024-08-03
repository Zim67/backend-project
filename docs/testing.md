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

## Testing Aircrafts Functionality

### Unit Tests
- Test the aircrafts service and repository classes to ensure correct data handling.

### Manual Testing
- **GET `/aircrafts`**: Verify it returns the correct list of aircrafts.
- **GET `/aircrafts/search?airline=`**: Ensure the search returns correct results.
- **POST `/aircrafts`**: Test adding a new aircraft with valid and invalid data.
- **PUT `/aircrafts/{id}`**: Check if updates are applied correctly.
- **DELETE `/aircrafts/{id}`**: Ensure aircrafts can be deleted and handle cases where the ID does not exist.

## Testing with Generic System

With the introduction of the generic system (`Updateables` and `UpdateData`), testing now includes validation for these generic methods. The tests ensure that all entities conform to the new structure and that the generic service methods operate correctly across different entity types.

### Updated Tests:
- New unit tests for `UpdateableService`.
- Refactored existing tests to align with the generic system.
- Added edge case tests for `UpdateData` validation.


