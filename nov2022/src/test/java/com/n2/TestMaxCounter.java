package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestMaxCounter {

  @Test
  void simpleTest() {
    int n = 5;
    int[] a = {3,4,4,6,1,4,4};
    int[] expectedResult = {3, 2, 2, 4, 2};
    MaxCounter maxCounter = new MaxCounter();
    assertThat(maxCounter.solution(n,a)).containsExactly(expectedResult);
  }
}
