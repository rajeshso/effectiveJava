package com.n2.arrays;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestPlusOne {

  @Test
  public void addOneSimple() {
    int[] a = {1, 2, 4};
    int[] expected = {1, 2, 5};
    assertThat(PlusOne.solution(a)).containsExactly(expected);
  }

  @Test
  public void addOneWithSimpleCarrier() {
    int[] a = {1, 2, 9};
    int[] expected = {1, 3, 0};
    assertThat(PlusOne.solution(a)).containsExactly(expected);
  }

  @Test
  public void addOneWithDoubleCarrier() {
    int[] a = {1, 9, 9};
    int[] expected = {2, 0, 0};
    assertThat(PlusOne.solution(a)).containsExactly(expected);
  }

  @Test
  public void addOneWithResultantSizeOfArrayBigger() {
    int[] a = {9, 9, 9};
    int[] expected = {1, 0, 0, 0};
    assertThat(PlusOne.solution(a)).containsExactly(expected);
  }
}
