package com.n2.codility;

import java.util.Arrays;

public class MaxCounter {
  public static int[] solution(int m, int[] a) {
    int len = a.length;
    int[] result = new int[m];
    for (int i = 0; i < len; i++) {
      if (a[i] > m) {
        int maxCounter = findMax(result);
        Arrays.fill(result,maxCounter);
      }else {
        result[a[i]-1]++;
      }
    }
    return result;
  }

  public static int[] solution2(int N, int[] A) {
    int[] counters = new int[N];
    Arrays.fill(counters, 0);
    int start_line = 0;
    int current_max = 0;
    for (int i : A) {
      int x = i - 1;
      if (i > N)
        start_line = current_max;
      else if (counters[x] < start_line)
        counters[x] = start_line + 1;
      else
        counters[x] += 1;
      if (i <= N && counters[x] > current_max)
        current_max = counters[x];
    }
    for (int i = 0; i < counters.length; i++)
      if (counters[i] < start_line) counters[i] = start_line;
    return counters;
  }

  private static int findMax(int[] a) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < a.length; i++) {
      if (a[i]>max) {
        max = a[i];
      }
    }
    return max;
  }

  private static void print(int[] a) {
    System.out.println("\n");
    for(int i=0;i<a.length;i++) {
      System.out.print(a[i]+" ,");
    }
    System.out.println("\n");
  }
}
