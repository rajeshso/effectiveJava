package com.n2.codility;

import static java.lang.Math.max;
import static java.util.Arrays.fill;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
class TestMaxCounter1 {

  public int[] solution(int N, int[] A) {
    int[] result = new int[N];
    int currentMax = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] > N) {
        //do max operation
        fill(result, currentMax);
      } else {
        //do increase operation
        result[A[i] - 1]++;
        currentMax = max(currentMax, result[A[i] - 1]);
      }
    }
    return result;
  }

  @Test
  public void testMaxCounter() {
    int[] A = {3, 4, 4, 6, 1, 4, 4};
    assertThat(solution(5, A)).isEqualTo(new int[]{3, 2, 2, 4, 2});
  }
}
