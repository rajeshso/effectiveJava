package com.n2.arrays;

import static org.assertj.core.api.Assertions.assertThat;

import com.n2.arrays.SeparateZerosAndNonZeros;
import org.junit.jupiter.api.Test;

public class TestSeparateZerosAndNonZeros {

  @Test
  public void testSeparateZerosAndNonZeros() {
    int[] arr = {0,12,13,0,3,4,0,6};
    assertThat(SeparateZerosAndNonZeros.solution(arr)).isEqualTo(new int[]{12,13,3,4,6,0,0,0});
  }
  @Test
  public void testSeparateZerosAndNonZeros1() {
    int[] arr = {0,12,13,0,3,4,0,6};
    assertThat(SeparateZerosAndNonZeros.solution1(arr)).isEqualTo(new int[]{12,13,3,4,6,0,0,0});
  }
}
