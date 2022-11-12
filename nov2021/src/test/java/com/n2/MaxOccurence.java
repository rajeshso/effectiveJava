package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MaxOccurence {

  @Test
  public void testNumber() {
    int[] arr = new int[] {10,2,10,4,5, 6,7,8,9,10};
    assertThat(solution(arr)).isEqualTo(10);
  }

  @Test
  public void testNumberThatHasEqualOccurence() {
    int[] arr = new int[] {10,2,10,4,5, 6,7,8,9};
    assertThat(solution(arr)).isEqualTo(-1);
  }
  @Test
  public void testNumberThatHasOnlyOneElement() {
    int[] arr = new int[] {10};
    assertThat(solution(arr)).isEqualTo(-1);
  }

  private int solution(int[] arr) {
    Arrays.sort(arr);
    int len= arr.length;
    int previousElement = arr[0];
    int count = 0;
    int maxCount = 0;
    int maxElement = previousElement;
    for (int i = 1; i < len; i++) {
      if (arr[i]==previousElement) {
        count++;
        if (count>maxCount) {
          maxElement = previousElement;
          maxCount = count;
        }
      }else {
        if (count>maxCount) {
          maxElement = previousElement;
          maxCount = count;
          count = 0;
        }
        previousElement = arr[i];
      }
    }
    System.out.println("maxElement is "+maxElement + " with "+maxCount);
    return (maxCount<=1)? -1: maxElement;
  }

}
