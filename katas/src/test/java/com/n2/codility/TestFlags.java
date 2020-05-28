package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestFlags {
  @Test
  public void testSolution1() {
    assertThat(Flags.solution1(new int[]{1,5,3,4,3,4,1,2,3,4,6,2})).isEqualTo(3);
  }
}
