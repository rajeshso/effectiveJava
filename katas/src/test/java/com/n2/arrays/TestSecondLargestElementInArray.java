package com.n2.arrays;

import static com.n2.arrays.SecondLargestElementInArray.secondLargestElementInArray;
import static com.n2.arrays.SecondLargestElementInArray.secondLargestElementInArray1;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestSecondLargestElementInArray {

  static Stream<Arguments> testData() {
    return Stream.of(
        Arguments.of(new int[]{5,3,7,2,9,8}, 8),
        Arguments.of(new int[]{9,8,3,5,2,7}, 8),
        Arguments.of(new int[]{1,2,3,4,5}, 4),
        Arguments.of(new int[]{10,10,9}, 10)
    );
  }

  @ParameterizedTest
  @MethodSource("testData")
  void testSecondLargestElementSolutions(int[] arr, int expected) {
    assertThat(secondLargestElementInArray(arr)).isEqualTo(expected);
    assertThat(secondLargestElementInArray1(arr)).isEqualTo(expected);
  }
}
