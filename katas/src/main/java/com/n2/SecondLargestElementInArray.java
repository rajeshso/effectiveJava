package com.n2;

import java.util.Arrays;

public class SecondLargestElementInArray {
  public static int secondLargestElementInArray(int[] s) {
    Arrays.sort(s);
    return s[s.length-2];
  }

  public static int secondLargestElementInArray1(int[] s) {
    int len = s.length;
    int temp;
    for (int i = 0; i < 2; i++) {
      for(int j=i; j<len;j++) {
        if (s[i]<s[j]) {
          temp = s[i];
          s[i] = s[j];
          s[j] = temp;
        }
      }
    }
    print(s);
    return s[1];
  }

  public static void main(String[] args) {
    System.out.println(secondLargestElementInArray(new int[]{5,3,7,2,9,8}));
  }

  private static void print(int[] a) {
    System.out.println("\n");
    for(int i=0;i<a.length;i++) {
      System.out.print(a[i]+" ,");
    }
    System.out.println("\n");
  }
}
