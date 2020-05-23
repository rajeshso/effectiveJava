package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestEquilibrium {

  @Test
  public void testEquilibrium() {
    int[] arr = {3,1,2,4,3};
    assertThat(Equilibirum.solution(arr)).isEqualTo(3);
  }
  @Test
  public void testEquilibrium1() {
    int[] arr = {3,1,2,4,3};
    assertThat(Equilibirum.solution1(arr)).isEqualTo(3);
  }
}
