package com.n2.codility;

import static java.lang.Math.max;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/
public class TestMaxProfit {

  public int solution1(int[] A) {
    int globalMaxSum = 0, localMaxSum = 0;
    int lowerBound = 0, upperBound = 0;
    int len = A.length;
    for (int i = 1; i < len; i++) {
      int difference = A[i] - A[i - 1];
      if (localMaxSum + difference > difference) {
        upperBound++;
      } else {
        upperBound = i;
      }
      if (localMaxSum + difference > globalMaxSum) {
        lowerBound = i;
      } else {
        upperBound = i;
      }
      localMaxSum = max(localMaxSum + difference, difference);
      globalMaxSum = max(localMaxSum, globalMaxSum);
    }
    return globalMaxSum;
  }

  public int solution(int[] A) {
    int globalMaxSum = 0, localMaxSum = 0;
    int len = A.length;
    for (int i = 1; i < len; i++) {
      int difference = A[i] - A[i - 1];
      localMaxSum = max(localMaxSum + difference, difference);
      globalMaxSum = max(localMaxSum, globalMaxSum);
    }
    return globalMaxSum;
  }

  static Stream<Arguments> testData() {
    return Stream.of(
        Arguments.of(new int[]{23171, 21011, 21123, 21366, 21013, 21367}, 356),
        Arguments.of(new int[]{1, 2, 3, 4, 5}, 4),
        Arguments.of(new int[]{5, 4, 3, 2, 1}, 0),
        Arguments.of(new int[]{1, 5, 3, 6, 4}, 5)
    );
  }

  @ParameterizedTest
  @MethodSource("testData")
  void testMaxProfitSolutions(int[] prices, int expected) {
    assertThat(solution(prices)).isEqualTo(expected);
    assertThat(solution1(prices)).isEqualTo(expected);
  }
}
