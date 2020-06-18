package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class TestCyclicRotation1 {

  //int[] arr = {5,3,4,1,2}
  public static int[] solution(int[] A, int K) {
    int len = A.length;
    int[] result = new int[A.length];
    for (int i = 0; i < len; i++) {
      int currentIndex = i;
      int newIndex = (currentIndex + K) % len;
      result[newIndex] = A[currentIndex];
    }
    System.out.println(Arrays.toString(result));
    return result;
  }

  @Test
  public void testCycleRotate5() {
    int[] arr = {5, 3, 4, 1, 2};
    assertThat(solution(arr, 5)).isEqualTo(new int[]{5, 3, 4, 1, 2});
  }

  @Test
  public void testCycleRotate2() {
    int[] arr = {5, 3, 4, 1, 2};
    assertThat(solution(arr, 2)).isEqualTo(new int[]{1, 2, 5, 3, 4});
  }
}
/**
 * 0 -> 1 2 3 4 5 6 7 1 -> 7 1 2 3 4 5 6 2 -> 6 7 1 2 3 4 5 3 -> 5 6 7 1 2 3 4 4 -> 4 5 6 7 1 2 3 5
 * -> 3 4 5 6 7 1 2 6 -> 2 3 4 5 6 7 1 7 -> 1 2 3 4 5 6 7 same as 0 8 -> 7 1 2 3 4 5 6 same as 1 9
 * -> 6 7 1 2 3 4 5 same as 2
 */
