package com.n2.codility;

import java.util.Arrays;
//Kadane's algorithm
public class MaxProfit {

  public static int[] solution(int[] a) {
    int globalMax=a[0],localMax=a[0];
    int globalMaxBegin=0, globalMaxEnd=0, localMaxBegin=0, localMaxEnd=0;
    int currentValue = 0;
    int len = a.length;
    for (int i = 1; i < len; i++) {
      currentValue = a[i];
      if (localMax+currentValue > currentValue) {
        localMax = localMax+currentValue;
        localMaxEnd = i;
        if (localMax > globalMax) {
          globalMax = localMax;
          globalMaxBegin = localMaxBegin;
          globalMaxEnd = localMaxEnd;
        }
      }else {
        localMax = currentValue;
        localMaxBegin = i;
        localMaxEnd = i;
        if (localMax > globalMax) {
          globalMax = localMax;
          globalMaxBegin = localMaxBegin;
          globalMaxEnd = localMaxEnd;
        }
      }
    }
    return Arrays.copyOfRange(a, globalMaxBegin, globalMaxEnd);
  }

  public static int solution1(int[] a) {
    int globalMaxSum=0, localMaxSum=0;
    int len = a.length;
    for (int i = 1; i < len ; i++) {
      int d = a[i] - a[i-1];
      localMaxSum = Math.max(localMaxSum+d, d);
      globalMaxSum = Math.max(globalMaxSum,localMaxSum);
    }
    return globalMaxSum;
  }
  public static void main(String[] args) {
    int[] arr = {5,-4,8,-10,-2,4,-3,2,7,-8,3,-5,3};
    System.out.println(Arrays.toString(solution(arr)));
    int[] arr1 = {23171, 21011, 21123,21366,21013,21367};
    System.out.println((solution1(arr1)));

  }
}
