package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class TestHalfSwapArray {

  int[] solution(int[] arr) {
    int[] result = new int[arr.length];
    int len = arr.length;
    if (len%2 == 0) {
      System.arraycopy(arr, 0, result, (len / 2), (len / 2));
      System.arraycopy(arr, (len / 2), result, 0, len / 2);
    }else {
      System.arraycopy(arr, 0, result, (len / 2), (len / 2)+1);
      System.arraycopy(arr, (len / 2)+1, result, 0, (len/2));
    }
    System.out.println(Arrays.toString(result));
    return result;
  }

  @Test
  public void testHalfSwapArrayWithEvenNumber() {
    int[] arr = new int[] {1,2,3,4,5, 6,7,8,9,10};
    assertThat(solution(arr)).isEqualTo(new int[]{6,7,8,9,10,1,2,3,4,5});
  }

  @Test
  public void testHalfSwapArrayWithOddNumber() {
    int[] arr = new int[] {1,2,3,4,5,6,7,8,9};
    assertThat(solution(arr)).isEqualTo(new int[]{6,7,8,9,1,2,3,4,5});
  }
}