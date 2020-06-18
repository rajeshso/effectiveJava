package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
//https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
public class TestMaxCounter {
  @Test
  public void testMaxCounter() {
    int[] arr = {3,4,4,6,1,4,4};
    int n = 5;
    int[] expected = {3,2,2,4,2};
    assertThat(MaxCounter.solution(n,arr)).isEqualTo(expected);
  }
  @Test
  public void testMaxCounter2() {
    int[] arr = {3,4,4,6,1,4,4};
    int n = 5;
    int[] expected = {3,2,2,4,2};
    assertThat(MaxCounter.solution2(n,arr)).isEqualTo(expected);
  }
}
