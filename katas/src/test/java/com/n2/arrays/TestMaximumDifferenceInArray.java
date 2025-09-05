package com.n2.arrays;

import static com.n2.arrays.MaximumDifferenceInArray.solution;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestMaximumDifferenceInArray {

  @ParameterizedTest
  @MethodSource("testData")
  public void testMaxDifferenceInArray(int[] arr, int expected) {
    assertThat(solution(arr)).isEqualTo(expected);
  }

  static Stream<Arguments> testData() {
    return Stream.of(
        // Happy cases
        Arguments.of(new int[]{20, 40, 2, 4, 5, 9, 20}, 38),
        Arguments.of(new int[]{1, 2, 3, 4, 5}, 4),
        Arguments.of(new int[]{10, 5, 15, 3, 8}, 12),
        
        // Edge cases
        Arguments.of(new int[]{5}, 0), // Single element
        Arguments.of(new int[]{7, 7, 7}, 0), // All same elements
        Arguments.of(new int[]{1, 1, 2, 2}, 1), // Duplicates
        
        // Boundary cases
        Arguments.of(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, Integer.MAX_VALUE - Integer.MIN_VALUE),
        Arguments.of(new int[]{-10, -5, -1}, 9), // All negative
        Arguments.of(new int[]{-5, 0, 5}, 10), // Negative to positive
        Arguments.of(new int[]{0, 0, 1}, 1) // With zeros
    );
  }

}
