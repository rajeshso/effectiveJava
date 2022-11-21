package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestNumberOfDiscIntersections {

  @Test
  void simpleTest() {
    int[] a = {1,5,2,1,4,0};
    NumberOfDiscIntersections numberOfDiscIntersections = new NumberOfDiscIntersections();
    final int result = numberOfDiscIntersections.solution(a);
    assertThat(result).isEqualTo(8);
  }


}
