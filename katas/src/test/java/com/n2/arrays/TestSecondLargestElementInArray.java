package com.n2.arrays;

import static com.n2.arrays.SecondLargestElementInArray.secondLargestElementInArray;
import static com.n2.arrays.SecondLargestElementInArray.secondLargestElementInArray1;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestSecondLargestElementInArray {

  @Test
  public void testSecondLargestElementInArray() {
    assertThat(secondLargestElementInArray(new int[]{5,3,7,2,9,8})).isEqualTo(8);
  }
  @Test
  public void testSecondLargestElementInArray1() {
    assertThat(secondLargestElementInArray1(new int[]{5,3,7,2,9,8})).isEqualTo(8);
  }
  @Test
  public void testSecondLargestElementInArray2() {
    assertThat(secondLargestElementInArray1(new int[]{9,8,3,5,2,7})).isEqualTo(8);
  }
}
