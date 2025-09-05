package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class TestDivCount {

  static Stream<Arguments> divCountProvider() {
    return Stream.of(
      Arguments.of(6, 11, 2, 3),
      Arguments.of(0, 10, 5, 3),
      Arguments.of(10, 10, 5, 1),
      Arguments.of(1, 1, 1, 1),
      Arguments.of(1, 1, 2, 0)
    );
  }

  @ParameterizedTest
  @MethodSource("divCountProvider")
  void testSolution1(int a, int b, int k, int expected) {
    assertThat(DivCount.solution1(a, b, k)).isEqualTo(expected);
  }

  @Disabled
  @ParameterizedTest
  @MethodSource("divCountProvider")
  void testSolution2(int a, int b, int k, int expected) {
    assertThat(DivCount.solution2(a, b, k)).isEqualTo(expected);
  }
}
