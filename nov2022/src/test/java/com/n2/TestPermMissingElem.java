package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestPermMissingElem {
  @Test
  void simpleTest() {
    int[] a = {2,4,7,8,1,5,3};
    PermMissingElem permMissingElem = new PermMissingElem();
    int actual = permMissingElem.solution1(a);
    int expected = 6;
    assertThat(actual).isEqualTo(expected);
  }
  @Test
  void oOfTest() {
    int[] a = {2,3,1,5};
    PermMissingElem permMissingElem = new PermMissingElem();
    int actual = permMissingElem.solution2(a);
    int expected = 4;
    assertThat(actual).isEqualTo(expected);
  }
  @Test
  void oOfNTest() {
    int[] a = {2,4,7,8,1,5,3};
    PermMissingElem permMissingElem = new PermMissingElem();
    int actual = permMissingElem.solution3(a);
    int expected = 6;
    assertThat(actual).isEqualTo(expected);
  }
}
