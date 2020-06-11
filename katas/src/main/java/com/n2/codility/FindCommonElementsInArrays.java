package com.n2.codility;

import java.util.Arrays;

//Given two sorted arrays, find all the common elements.
// Do not use O(n^2)
// USE O(a+b)
public class FindCommonElementsInArrays {

  static public int[] solution1(int[] a, int[] b) {
    Arrays.sort(a);
    Arrays.sort(b);
    int[] result = new int[a.length];
    int resultIndex = 0;
    int aIndex = 0;
    int bIndex = 0;
    while (aIndex < a.length) {
      if (a[aIndex] == b[bIndex]) {
        result[resultIndex] = a[aIndex];
        resultIndex++;
        bIndex++;
        aIndex++;
      } else if (a[aIndex] < b[bIndex]) {
        aIndex++;
      } else if (a[aIndex] > b[bIndex]) {
        while (b[bIndex] < a[aIndex] && bIndex < b.length) {
          bIndex++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 7};
    int[] b = {2, 4, 5, 6, 8};
    System.out.println(
        "Intersection problem for " + Arrays.toString(a) + " and " + Arrays.toString(b) + " is "
            + Arrays.toString(solution1(a, b)));
  }
}
