package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class TestEquilibrium {

  static Stream<Arguments> equilibriumProvider() {
    return Stream.of(
      Arguments.of(new int[]{3,1,2,4,3}, 3),
      Arguments.of(new int[]{1,2,3,4,5}, 5),
      Arguments.of(new int[]{1,1,1,1,1}, 1)
    );
  }

  @Disabled
  @ParameterizedTest
  @MethodSource("equilibriumProvider")
  void testEquilibrium(int[] arr, int expected) {
    assertThat(Equilibirum.solution(arr)).isEqualTo(expected);
  }

  @Disabled
  @ParameterizedTest
  @MethodSource("equilibriumProvider")
  void testEquilibrium1(int[] arr, int expected) {
    assertThat(Equilibirum.solution1(arr)).isEqualTo(expected);
  }
}
