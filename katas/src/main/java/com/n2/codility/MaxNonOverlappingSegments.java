package com.n2.codility;

public class MaxNonOverlappingSegments {

  public static int solution(int[] a, int[] b) {
    return 0;
  }

  public static boolean isOverlap(int a1, int a2, int b1, int b2) {
    return a1 - a2 > 0 || b2 - b1 > 0;
  }
}
