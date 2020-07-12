package com.n2.codility;

import java.util.Arrays;
//Caterpillar method
//https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/

/**
 * An integer M and a non-empty array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.
 *
 * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.
 *
 * For example, consider integer M = 6 and array A such that:
 *
 *     A[0] = 3
 *     A[1] = 4
 *     A[2] = 5
 *     A[3] = 5
 *     A[4] = 2
 * There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 *
 * The goal is to calculate the number of distinct slices.
 *
 * Write a function:
 *
 * class Solution { public int solution(int M, int[] A); }
 *
 * that, given an integer M and a non-empty array A consisting of N integers, returns the number of distinct slices.
 *
 * If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
 *
 * For example, given integer M = 6 and array A such that:
 *
 *     A[0] = 3
 *     A[1] = 4
 *     A[2] = 5
 *     A[3] = 5
 *     A[4] = 2
 * the function should return 9, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * M is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..M].
 */
public class CountDistinctSlices {

  // Correct answer : https://github.com/cutajarj/CodilityInJava/blob/master/src/main/java/com/cutajarjames/codility/caterpillarmethod/CountDistinctSlices.java
  //TODO: Wrong. the last distinct element of single digit is not counted
  static int solution1(int m, int[] a) {
    int len = a.length;
    int startIndex = 0;
    int endIndex = 0;
    int distinctCount = 0;
    while (endIndex < len) {
      final int subArrayLen = endIndex - startIndex + 1;
      int[] subArray = new int[subArrayLen];
      System.arraycopy(a, startIndex, subArray, 0, subArrayLen);
      Arrays.sort(subArray);
      if (isDistinct(subArray)) {
        distinctCount++;
        endIndex++;
        System.out.println(Arrays.toString(subArray));
      } else {
        startIndex++;
        endIndex = startIndex;
      }
    }
    return distinctCount;
  }

  /**
   * given {2} , the distinct slices are {2} , distinctCount=0
   * given {2,7} , the distinct slices are {2},{7}, {2,7} , distinctCount=3
   * given {2,7,4} , the distinct slices are {2},{7}, {2,7} , {2,7,4}, {7,4}, {4} distinctCount=3+3=6
   * given {2,7,4,6} , the distinct slices are {2},{7}, {2,7} , {2,7,4}, {7,4}, {4}, {2,7,4,6}, {7,4,6}, {4,6}, {6} distinctCount=6+4=10
   * given {2,7,4,6,3} , the distinct slices are ... distinctCount=10+(size of list)=10+5=15
   * @param m
   * @param a
   * @return
   */
  static int solution(int m, int[] a) {
    distinctMarker = new boolean[m];
    Arrays.fill(distinctMarker,false);
    int len = a.length;
    int startIndex = 0;
    int endIndex = 0;
    int distinctCount = 0;
    while (endIndex<len) {
      int element = a[startIndex];
      if (markDistinct(element,true)) {

      }
    }
    return distinctCount;
  }

  private static boolean[] distinctMarker;
  /**
   * this is an alternative to checkDistinct with o(1)
   *
   * use a created array of a fixed size
   *
   * @param a
   * @param mark
   * @return
   */
  static boolean markDistinct(int a, boolean mark) {
    if (mark) {
      if (distinctMarker[a])
        return false;
      else {
        distinctMarker[a]=true;
      }
    }else { //unmark
      if (distinctMarker[a])
        distinctMarker[a]=false;
      else {
        return false;
      }
    }
    return true;
  }

  static boolean isDistinct(int[] a) {
    Arrays.sort(a);
    int len = a.length;
    if (len < 2) {
      return true;
    }
    while (len >= 2) {
      if (a[len - 1] == a[len - 2]) {
        return false;
      }
      len--;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("Answer for {3,4,5,5,2} is " + solution1(2, new int[]{3, 4, 5, 5, 2}));
    System.out.println("Answer for {3} is " + solution1(2, new int[]{3}));
    System.out.println("Answer for {3,2} is " + solution1(2, new int[]{3, 2}));
    System.out.println("Answer for {2,7,4} is " + solution1(2, new int[]{2, 7, 4}));
    System.out.println("Answer for {2,4,1,7,9,7,3,5,5,8,7,1} is " + solution1(2,
        new int[]{2, 4, 1, 7, 9, 7, 3, 5, 5, 8, 7, 1}));

    System.out.println("------------------------------");
    System.out.println("Answer for {3,4,5,5,2} is " + solution(5, new int[]{3, 4, 5, 5, 2}));

  }


}
