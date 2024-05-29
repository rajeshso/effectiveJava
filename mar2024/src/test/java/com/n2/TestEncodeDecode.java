package com.n2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The exercise involves implementing two functions:
 *
 * Encoding a string:
     * The input is a string of characters.
     * The output should be an encoded string where consecutive duplicate characters are replaced with the character followed by the count.
     * For example, if the input is "AABBC", the output should be "A2B2C".
     * If the input is "A3B2C2", the output should be "AAABBCC".
 * Decoding a string:
     * The input is an encoded string.
     * The output should be the original string with the encoded characters expanded.
     * For example, if the input is "A2B2C", the output should be "AABBC".
     * If the input is "AAABBCC", the output should be "A3B2C2".
 * The key steps to implement this exercise are:
     * Iterate through the input string character by character.
     * Keep track of the current character and a count of consecutive occurrences.
     * When the current character changes, append the previous character and its count to the output string.
     * For decoding, split the input string into pairs of character and count, then expand the string accordingly.
 */
public class TestEncodeDecode {

  //if input is "AABBC", the output should be "A2B2C"
  private String encode(String input) {
    if (input.isEmpty()) {
      return input; // Empty string remains empty after encoding
    }
    StringBuilder sb = new StringBuilder();
    char currentChar = input.charAt(0);
    int count = 1;
    for (int i = 1; i < input.length(); i++) {
      char nextChar = input.charAt(i);
      if (currentChar == nextChar) {
        count++;
      } else {
        sb.append(currentChar);
        sb.append(count);
        currentChar = nextChar;
        count = 1;
      }
    }
    sb.append(currentChar);
    sb.append(count);
    return sb.toString();
  }

  //if the input is "A2B2C", the output should be "AABBC"
  private String decode(String input) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    for (; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
        int count = Character.getNumericValue(input.charAt(i + 1));
        for (int j = 0; j < count; j++) {
          sb.append(currentChar);
        }
        i++; // Skip the count digit
      } else {
        sb.append(currentChar);
      }
    }
    // Check for empty string after decoding (all digits or invalid characters)
    if (sb.isEmpty()) {
      return ""; // Or throw an exception if desired
    }
    // Check for extra characters at the end (original check remains)
    if (input.length() > i) {
      throw new IllegalArgumentException("Invalid encoded string: Extra characters at the end");
    }
    return sb.toString();
  }

  @ParameterizedTest
  @MethodSource("encodeArguments")
  void testEncode(String input, String expected) {
    assertEquals(expected, encode(input));
  }
  static Stream<Arguments> encodeArguments() {
    return Stream.of(
        Arguments.of("", ""),
        Arguments.of("A", "A1"),
        Arguments.of("ABC", "A1B1C1"),
        Arguments.of("AABBCC", "A2B2C2"),
        Arguments.of("AAAABBBBCCCCDDDDEEEE", "A4B4C4D4E4")
    );
  }
  @DisplayName("Test encode with repeated characters")
  @ParameterizedTest
  @MethodSource("decodeArguments")
  void testDecode(String input, String expected) {
    assertEquals(expected, decode(input));
  }
  static Stream<Arguments> decodeArguments() {
    return Stream.of(
        Arguments.of("", ""),
        Arguments.of("A1", "A"),
        Arguments.of("A1B1C1", "ABC"),
        Arguments.of("A2B2C2", "AABBCC"),
        Arguments.of("A4B4C4D4E4", "AAAABBBBCCCCDDDDEEEE"),
        Arguments.of("A2B", "AAB")
    );
  }

  @Disabled
  @ParameterizedTest
  @MethodSource("decodeInvalidArguments")
  void testDecode_Invalid(String input) {
    final IllegalArgumentException illegalArgumentException = assertThrows(
        IllegalArgumentException.class, () -> decode(input));
    assertEquals("Invalid encoded string: Extra characters at the end", illegalArgumentException.getMessage());
  }

  static Stream<Arguments> decodeInvalidArguments() {
    return Stream.of(
        Arguments.of("A-1")
    );
  }
  @ParameterizedTest
  @DisplayName("Test Integers greater than 100")
  @ValueSource(ints = {101, 102, 103, 104, 105})
  void testIntegersGreaterThan100(int number) {
    assertTrue(number > 100);
  }

  @ParameterizedTest
  @DisplayName("Test if the DayOfWeek starts with the letter T")
  @EnumSource(value = DayOfWeek.class, names = {"TUESDAY", "THURSDAY"})
  void testDayOfWeekStartsWithT(DayOfWeek day) {
    assertTrue(day.toString().startsWith("T"));
  }

  @ParameterizedTest
  @DisplayName("Encoding and Decoding should be inverses")
  @CsvSource({
      "A, A1",
      "ABC, A1B1C1",
      "AABBCC, A2B2C2",
      "AAAABBBBCCCCDDDDEEEE, A4B4C4D4E4"
  })
  void testEncodeDecode(String originalString, String encodedString) {
    String actualEncoded = encode(originalString);
    String actualDecoded = decode(encodedString);
    assertEquals(encodedString, actualEncoded);
    assertEquals(originalString, actualDecoded);
  }
  @ParameterizedTest
  @DisplayName("Encoding and Decoding should be inverses")
  @CsvFileSource(resources = "/encode-decode.csv")
  void testEncodeDecodeFromFileSource(String originalString, String encodedString) {
    String actualEncoded = encode(originalString);
    String actualDecoded = decode(encodedString);
    assertEquals(encodedString, actualEncoded);
    assertEquals(originalString, actualDecoded);
  }
  @ParameterizedTest
  @DisplayName("Test Double and String")
  @CsvSource({
      "12.12, A1",
      "14.12, A1B1C1"
  })
  void testDoubleAndString(Double number, String str) {
    assertTrue(number > 10);
    assertTrue(str.startsWith("A"));
  }
  @ParameterizedTest
  @ValueSource(strings = {"apple", "banana", "cherry"})
  void testWithStringValueSource(String fruit) {
    assertNotNull(fruit);
  }

  @ParameterizedTest
  @ArgumentsSource(CustomArgumentProvider.class)
  @CsvFileSource(resources = "/encode-decode.csv") //Demonstrate that multiple sources can be combined
  void testWithArgumentsSource(String originalString, String encodedString) {
    String actualEncoded = encode(originalString);
    String actualDecoded = decode(encodedString);
    assertEquals(encodedString, actualEncoded);
    assertEquals(originalString, actualDecoded);
  }

  static class CustomArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of(
          Arguments.of("A", "A1"),
          Arguments.of("ABC", "A1B1C1"),
          Arguments.of("AABBCC", "A2B2C2"),
          Arguments.of("AAAABBBBCCCCDDDDEEEE", "A4B4C4D4E4")
      );
    }
  }
}
