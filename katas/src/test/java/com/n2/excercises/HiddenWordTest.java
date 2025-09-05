package com.n2.excercises;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class HiddenWordTest {

  static Stream<Arguments> hiddenWordProvider() {
    return Stream.of(
      Arguments.of("HARPS", "AAAAA", "+A+++"),
      Arguments.of("HARPS", "HELLO", "H****"),
      Arguments.of("HARPS", "HEART", "H*++*"),
      Arguments.of("HARPS", "HARMS", "HAR*S"),
      Arguments.of("HARPS", "HARPS", "HARPS"),
      Arguments.of("HARPS", "aaaaa", "+A+++"),
      Arguments.of("laptop", "laptop", "LAPTOP"),
      Arguments.of("laptop", "lappot", "LAP+O+"),
      Arguments.of("laptop", "DUKTOP", "***TOP"),
      Arguments.of("laptop", "      ", "******"),
      Arguments.of("new york", "new york", "NEW YORK"),
      Arguments.of("new york", "wen york", "+E+ YORK"),
      Arguments.of("new york", " wenyork", "++++YORK"),
      Arguments.of("new york", "new yort", "NEW YOR*"),
      Arguments.of("new york", "12345678", "********"),
      Arguments.of("new york", "bad", "")
    );
  }

  @ParameterizedTest
  @MethodSource("hiddenWordProvider")
  void testGetHint(String hidden, String guess, String expected) {
    HiddenWord hiddenWord = new HiddenWord(hidden);
    assertThat(hiddenWord.getHint(guess)).isEqualTo(expected);
  }
}
