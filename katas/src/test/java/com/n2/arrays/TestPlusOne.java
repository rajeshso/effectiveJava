package com.n2.arrays;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestPlusOne {

  static Stream<Arguments> testCases() {
    return Stream.of(
      Arguments.of(new int[]{0}, new int[]{1}),
      Arguments.of(new int[]{9}, new int[]{1, 0}),
      Arguments.of(new int[]{1, 2, 4}, new int[]{1, 2, 5}),
      Arguments.of(new int[]{1, 2, 9}, new int[]{1, 3, 0}),
      Arguments.of(new int[]{1, 9, 9}, new int[]{2, 0, 0}),
      Arguments.of(new int[]{9, 9, 9}, new int[]{1, 0, 0, 0}),
      Arguments.of(new int[]{9, 9, 9, 9, 9}, new int[]{1, 0, 0, 0, 0, 0})
    );
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testSolution(int[] input, int[] expected) {
    assertThat(PlusOne.solution(input.clone())).containsExactly(expected);
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testSolution1(int[] input, int[] expected) {
    assertThat(PlusOne.solution1(input.clone())).containsExactly(expected);
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testSolution3(int[] input, int[] expected) {
    assertThat(PlusOne.solution3(input.clone())).containsExactly(expected);
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testSolution4(int[] input, int[] expected) {
    assertThat(PlusOne.solution4(input.clone())).containsExactly(expected);
  }
}
