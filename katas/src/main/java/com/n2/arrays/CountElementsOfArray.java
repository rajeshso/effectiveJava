package com.n2.arrays;

import static java.lang.Math.abs;

import java.util.Arrays;
// when there are negative numbers, add the lowest negative number to a number such that it comes to the zero index.
// also add the same number to all the elements. In the example below, -4 goes to index 0, -3 to index 1

//This is a question in lecture.
public class CountElementsOfArray {

  //This technique works for only small arrays with elements in a short range such as 0-9
  public static int[] solution(int[] A) {
    Arrays.sort(A);
    final int length = A.length;
    final int maxValue = A[length - 1];
    final int minValue = A[0];
    int[] frequency;
    if (minValue < 0) {
      frequency = new int[abs(minValue) + maxValue + 1];
    } else {
      frequency = new int[maxValue + 1];
    }
    for (int i = 0; i < length; i++) {
      if (minValue < 0) {
        frequency[A[i] + abs(minValue)]++;
      } else {
        frequency[A[i]]++;
      }
    }
    System.out.println(Arrays.toString(frequency));
    return frequency;
  }

  public static void main(String[] args) {
    solution(new int[]{4, 3, 0, 4, 3, 9, 2, 4});
    solution(new int[]{-4, 3, -1, 2, -3, 5, 2, 4});
  }
}
