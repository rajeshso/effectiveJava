package com.n2.codility;

import java.util.Arrays;
//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class CyclicRotation {
  //int[] arr = {5,3,4,1,2}
  public static int[] solution(int[] A, int K) {
    int len = A.length;
    int[] result = new int[A.length];
    for (int i = 0; i < len; i++) {
      int remainder_trick = (i+K)%len;
      result[remainder_trick] = A[i];
    }
    print(result);
    return result;
  }
  private static void print(int[] a) {
    System.out.println(Arrays.toString(a));
  }

  public static void main(String[] args) {
    solution(new int[]{5,4,3,2,1}, 5);
  }
}
/**

 0 -> 1 2 3 4 5 6 7
 1 -> 7 1 2 3 4 5 6
 2 -> 6 7 1 2 3 4 5
 3 -> 5 6 7 1 2 3 4
 4 -> 4 5 6 7 1 2 3
 5 -> 3 4 5 6 7 1 2
 6 -> 2 3 4 5 6 7 1
 7 -> 1 2 3 4 5 6 7 same as 0
 8 -> 7 1 2 3 4 5 6 same as 1
 9 -> 6 7 1 2 3 4 5 same as 2
 */
