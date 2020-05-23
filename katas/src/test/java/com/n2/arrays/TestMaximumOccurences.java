package com.n2.arrays;

import static org.assertj.core.api.Assertions.assertThat;

import com.n2.arrays.MaximumOccurences;
import org.junit.jupiter.api.Test;

public class TestMaximumOccurences {

  @Test
  public void testMaxOccurence() {
    int[] a = new int[]{1,2,3,4,5,6,7,8,9,3,2,4,2};
    assertThat(MaximumOccurences.solution(a)).isEqualTo(3);
  }
}
