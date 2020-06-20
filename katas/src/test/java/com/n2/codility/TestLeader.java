package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class TestLeader {

  @Test
  public void testLeader() {
    int[] arr = {2, 4, 3, 3, 3, 2, 3};
    assertThat(Leader.solution(arr)).isIn(2, 3, 4, 6);
  }

  @Test
  public void testLeaderWithEfficientSolution() {
    int[] arr = {2, 4, 3, 3, 3, 2, 3};
    assertThat(Leader.solution1(arr)).isIn(2, 3, 4, 6);
  }

  @Test
  public void testLeaderInTest() {
    int[] arr = {2, 4, 3, 3, 3, 2, 3};
    assertThat(leader(arr)).isEqualTo(3);
  }

  private int leader(int[] arr) {
    Arrays.sort(arr);
    int halfOfLen = arr.length / 2;
    int mid = arr[halfOfLen];
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if (mid == arr[i]) {
        count++;
      }
    }
    if (count > halfOfLen) {
      return mid;
    } else {
      return -1;
    }
  }
}
