package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestLeader {
  @Test
  public void testLeader() {
    int[] arr= {2,4,3,3,3,2,3};
    assertThat(Leader.solution(arr)).isIn(2,3,4,6);
  }
  @Test
  public void testLeaderWithEfficientSolution() {
    int[] arr= {2,4,3,3,3,2,3};
    assertThat(Leader.solution1(arr)).isIn(2,3,4,6);
  }
}
