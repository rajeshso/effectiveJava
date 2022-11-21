package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestPassingCars {
  @Test
  void simpleTest() {
    int[] carDirections = {0,1,0,1,1};
    PassingCars passingCars = new PassingCars();
    assertThat(passingCars.solution(carDirections)).isEqualTo(5);
  }

}
