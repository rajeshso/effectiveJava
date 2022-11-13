package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestCyclicRotation {
  @Test
  void first() {
    int[] a = {3, 8, 9, 7, 6};
    int[] expected = {6, 3, 8, 9, 7};
    CyclicRotation cyclicRotation = new CyclicRotation();
    int[] result = cyclicRotation.solution1(a,1);
    assertThat(result).containsExactly(expected);
  }
  @Test
  void second() {
    int[] a = {3, 8, 9, 7, 6};
    int[] expected = {7, 6, 3, 8, 9};
    CyclicRotation cyclicRotation = new CyclicRotation();
    int[] result = cyclicRotation.solution1(a,2);
    assertThat(result).containsExactly(expected);
  }
  @Test
  void third() {
    int[] a = {3, 8, 9, 7, 6};
    int[] expected = {9, 7, 6, 3, 8};
    CyclicRotation cyclicRotation = new CyclicRotation();
    int[] result = cyclicRotation.solution1(a,3);
    assertThat(result).containsExactly(expected);
  }
}
