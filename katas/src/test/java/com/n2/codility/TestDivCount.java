package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestDivCount {

  @Test
  public void testSolution1() {
    assertThat(DivCount.solution1(6,11,2)).isEqualTo(3);
  }
  @Test
  public void testSolution2() {
    assertThat(DivCount.solution2(6,11,2)).isEqualTo(3);
  }
}
