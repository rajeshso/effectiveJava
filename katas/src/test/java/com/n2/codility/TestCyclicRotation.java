package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class TestCyclicRotation {

  @Test
  public void testCycleRotate2() {
    int[] arr = {5,3,4,1,2};
    assertThat(CyclicRotation.solution(arr,2)).isEqualTo(new int[]{1,2,5,3,4});
  }
}
