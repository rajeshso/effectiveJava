package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class TestCyclicRotation {

  static Stream<Arguments> rotationProvider() {
    return Stream.of(
      Arguments.of(new int[]{5,3,4,1,2}, 2, new int[]{1,2,5,3,4}),
      Arguments.of(new int[]{1,2,3,4}, 1, new int[]{4,1,2,3}),
      Arguments.of(new int[]{1,2,3,4}, 0, new int[]{1,2,3,4})
    );
  }

  @ParameterizedTest
  @MethodSource("rotationProvider")
  void testCycleRotate(int[] arr, int k, int[] expected) {
    assertThat(CyclicRotation.solution(arr, k)).isEqualTo(expected);
  }
}
