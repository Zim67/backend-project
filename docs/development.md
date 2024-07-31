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
