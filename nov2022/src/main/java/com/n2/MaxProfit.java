package com.n2;

import java.util.Arrays;

//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/
public class MaxProfit {
  int solution(int[] a) {
    a = createDelta(a);
    System.out.println(Arrays.toString(a));
    int len = a.length;
    int globalMax = a[0];
    int localMax = a[0];
    int endCounter = 1;
    while (endCounter<len) {
      int currentElement = a[endCounter];
      int newSum = localMax+currentElement;
      if (currentElement>newSum) {
        localMax = currentElement; //start a new subarray
      } else if (currentElement<newSum) { //we are raising up as a subarray, accumulate profit
        localMax = newSum;
      }else { //newSum == currentElement
        localMax = currentElement; //start a new subarray
      }
      endCounter++;
      globalMax = Math.max(localMax, globalMax);
    }
    return globalMax;
  }
  private int[] createDelta(int[] a) {
    int [] deltaA = new int[a.length];
    deltaA[0] = 0;
    for(int i=1; i<a.length; i++) {
      deltaA[i] = a[i] - a[i-1];
    }
    return deltaA;
  }
  int solutionCleaner(int[] a) {
    int globalMaxSum = 0;
    int localMaxSum = 0;
    for (int i = 1; i < a.length; i++) {
      int d= a[i] - a[i-1];
      localMaxSum = Math.max(d, localMaxSum+d);
      globalMaxSum = Math.max(localMaxSum, globalMaxSum);
    }
    return globalMaxSum;
  }
}
