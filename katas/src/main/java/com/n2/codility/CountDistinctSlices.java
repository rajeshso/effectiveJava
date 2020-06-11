package com.n2.codility;

import java.util.Arrays;

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

  static boolean isDistinct(int[] a) {
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


  }
}
