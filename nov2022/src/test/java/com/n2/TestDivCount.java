package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/
public class TestDivCount {
  public int solution(int A, int B, int K) {
    int a1 = firstNumberToBeDivisible(A, B, K);
    int b1 = lastNumberToBeDivisible(B, A, K);

    if (a1 == -1) return -1;
   // return ((b1/K) - (a1/K) + 1);
    return (int) Math.round(((double) (b1-a1+1)/K));
  }
  public int firstNumberToBeDivisible(int A, int B, int K) {
    for (int i = A; i < B; i++) {
      if (i%K == 0) return i;
    }
    return -1;
  }
  public int lastNumberToBeDivisible(int B,int A, int K) {
    for (int i = B; i > A; i--) {
      if (i%K == 0) return i;
    }
    return -1;
  }
  @Test
  void simpleTest() {
    assertThat(solution(6,11,2)).isEqualTo(3);
  }
}
