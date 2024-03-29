package com.n2.arrays;

import java.util.Arrays;

public class RemoveDups {
  public static int[] removeDups(int[] a) {
    int[] result = new int[a.length];
    Arrays.sort(a);
    int index=0;
    for(int i=0;i<a.length;i++) {
      if (i==0) {
        result[index] = a[i];
        index++;
      }else if (result[index-1]!=a[i]) {
        result[index] = a[i];
        index++;
      }
    }
/*  int[] result1 = new int[index];
    for(int i=0;i<index;i++) {
      result1[i]=result[i];
    }*/
    int[] result1 = Arrays.copyOf(result, index);
    print(result1);
    return result1;
  }
  public static int[] removeDups1(int[] a) {
    return Arrays.stream(a).distinct().toArray();
  }
  private static void print(int[] a) {
    System.out.println(Arrays.toString(a));
  }
}
