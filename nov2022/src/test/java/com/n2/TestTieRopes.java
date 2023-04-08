package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/16-greedy_algorithms/tie_ropes/
public class TestTieRopes {
  public int solution(int K, int[] A) {
    int len = A.length;
    int ropeCount = 0;
    int before = A[0];
    for (int i = 1; i < len; i++) {
      int checkSum = before + A[i];
      if (checkSum >= K) {
        ropeCount++;
        before=0;
      }else  {
        before = A[i];
      }
    }
    return ropeCount;
  }
  public int solution1(int K, int[] A) {
    int ropeCount = 0;
    int ropeLength = 0;
    for (int rope : A) {
      ropeLength += rope;
      if (ropeLength >= K) {
        ropeCount++;
        ropeLength =0;
      }
    }
    return ropeCount;
  }
  @Test
  void simpleTest() {
    int K = 4;
    int[] ropes = {1,2,3,4,1,1,3};
    int ropeCount = solution(K, ropes);
    assertThat(ropeCount).isEqualTo(3);
  }
  @Test
  void efficientTest() {
    int K = 4;
    int[] ropes = {1,2,3,4,1,1,3};
    int ropeCount = solution1(K, ropes);
    assertThat(ropeCount).isEqualTo(3);
  }
}
