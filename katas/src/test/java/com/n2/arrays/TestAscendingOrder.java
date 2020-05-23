package com.n2.arrays;

import static com.n2.arrays.AscendingOrder.arrangeAscending;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestAscendingOrder {

  @Test
  public void testSingleDigitIntegers() {
    int[] a = {5,3,7,2,9,8};
    assertThat(arrangeAscending(a)).containsExactly(2,3,5,7,8,9);
  }


}
