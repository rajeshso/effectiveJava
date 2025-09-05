package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class TestCyclicRotation1 {

  public static int[] solution(int[] A, int K) {
    int len = A.length;
    int[] result = new int[A.length];
    for (int i = 0; i < len; i++) {
      int currentIndex = i;
      int newIndex = (currentIndex + K) % len;
      result[newIndex] = A[currentIndex];
    }
    System.out.println(Arrays.toString(result));
    return result;
  }

  static Stream<Arguments> rotationProvider() {
    return Stream.of(
      Arguments.of(new int[]{5, 3, 4, 1, 2}, 5, new int[]{5, 3, 4, 1, 2}),
      Arguments.of(new int[]{5, 3, 4, 1, 2}, 2, new int[]{1, 2, 5, 3, 4}),
      Arguments.of(new int[]{1, 2, 3, 4}, 1, new int[]{4, 1, 2, 3})
    );
  }

  @ParameterizedTest
  @MethodSource("rotationProvider")
  void testCycleRotate(int[] arr, int k, int[] expected) {
    assertThat(solution(arr, k)).isEqualTo(expected);
  }
}
/**
 * 0 -> 1 2 3 4 5 6 7 1 -> 7 1 2 3 4 5 6 2 -> 6 7 1 2 3 4 5 3 -> 5 6 7 1 2 3 4 4 -> 4 5 6 7 1 2 3 5
 * -> 3 4 5 6 7 1 2 6 -> 2 3 4 5 6 7 1 7 -> 1 2 3 4 5 6 7 same as 0 8 -> 7 1 2 3 4 5 6 same as 1 9
 * -> 6 7 1 2 3 4 5 same as 2
 */
