package com.n2.codility;

import static java.lang.Math.abs;

//https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
public class TapeEquilibirium {

  public static int solution1(int[] a) {
    int len = a.length;
    int result = Integer.MAX_VALUE;
    int leftSum = a[0];
    int rightSum = 0;
    for (int i = 1; i < len; i++) {
      rightSum += a[i];
    }
    for (int i = 1; i < len - 1; i++) {
      if (abs(leftSum - rightSum) < result) {
        result = abs(leftSum - rightSum);
      }
      leftSum += a[i];
      rightSum -= a[i];
    }
    System.out.println(result);
    return result;
  }

  public static void main(String[] args) {
    solution1(new int[]{3, 1, 2, 4, 3});
  }
}
