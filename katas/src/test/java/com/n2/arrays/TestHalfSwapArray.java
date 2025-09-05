package com.n2.arrays;

import static com.n2.arrays.HalfSwapArray.solution;
import static com.n2.arrays.HalfSwapArray.solution2;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestHalfSwapArray {

  @Test
  public void testHalfSwapArrayWithEvenNumber() {
    int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10};
    assertThat(solution(arr)).isEqualTo(new int[]{6,7,8,9,10,1,2,3,4,5});
    assertThat(solution2(arr)).isEqualTo(new int[]{6,7,8,9,10,1,2,3,4,5});
  }
  @Test
  public void testHalfSwapArrayWithOddNumber() {
    int[] arr = new int[] {1,2,3,4,5,6,7,8,9};
    assertThat(solution(arr)).isEqualTo(new int[]{6,7,8,9,1,2,3,4,5});
    assertThat(solution2(arr)).isEqualTo(new int[]{6,7,8,9,1,2,3,4,5});
  }
}
