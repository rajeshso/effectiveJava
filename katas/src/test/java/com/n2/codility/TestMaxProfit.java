package com.n2.codility;

import static java.lang.Math.max;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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

  @Test
  public void test() {
    int[] A = new int[6];
    A[0] = 23171;
    A[1] = 21011;
    A[2] = 21123;
    A[3] = 21366;
    A[4] = 21013;
    A[5] = 21367;
    assertThat(solution(A)).isEqualTo(356);
  }
}
