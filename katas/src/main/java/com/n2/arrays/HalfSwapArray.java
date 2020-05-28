package com.n2.arrays;

import java.util.Arrays;

public class HalfSwapArray {
  public static int[] solution(int[] s) {
    int[] result = new int[s.length];
    if (s.length%2==0) {
      System.arraycopy(s,0,result,s.length/2,s.length/2);
      System.arraycopy(s,s.length/2,result,0,s.length/2);
    }else {
      System.arraycopy(s,0,result,(s.length/2),(s.length/2)+1);
      System.arraycopy(s,(s.length/2)+1,result,0,s.length/2);
    }
    print(result);
    return result;
  }
  private static void print(int[] a) {
    System.out.println(Arrays.toString(a));
  }
}
