package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestPassingCars {
  @Test
  public void testSolution2() {
    int[] cars = {0,1,0,1,1};
    assertThat(PassingCars.solution2(cars)).isEqualTo(5);
  }
}
