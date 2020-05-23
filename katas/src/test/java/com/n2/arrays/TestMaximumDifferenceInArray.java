package com.n2.arrays;

import static com.n2.arrays.MaximumDifferenceInArray.solution;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestMaximumDifferenceInArray {

  @Test
  public void testMaxDifferenceInArray() {
    int[] arr = {20, 40,2, 4,5,9,20};
    assertThat(solution(arr)).isEqualTo(38);
  }

}
