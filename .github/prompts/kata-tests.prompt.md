# Prompt: Generate Parameterized JUnit Tests for Java Kata Project

You are working on a long-standing Java coding kata practice project using IntelliJ IDEA. The project is structured with multiple Gradle modules and uses:

- Java version: `JavaVersion.VERSION_23`
- Gradle version: `8.14`

## Objective

Generate **JUnit tests**, specifically **parameterized tests**, for the Java classes in this project. The goal is to ensure thorough coverage of edge cases and typical usage patterns.

## Guidelines

- Use `@ParameterizedTest` from JUnit 5.
- Prefer `@MethodSource` or `@CsvSource` for supplying test data.
- Keep test methods concise and descriptive.
- Ensure compatibility with Gradle 8.14 and Java 23.
- Assume the project follows standard Java conventions and is modularised.

## Example

If given a class like:

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```
Generate a test like:
```java
@ParameterizedTest
@CsvSource({
    "1, 2, 3",
    "-1, -2, -3",
    "0, 0, 0"
})
void testAdd(int a, int b, int expected) {
    Calculator calc = new Calculator();
    assertEquals(expected, calc.add(a, b));
}
```
Notes
Do not include boilerplate unrelated to the test logic.
Focus on clarity and maintainability.
