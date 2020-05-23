package com.n2.arrays;

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
    System.out.println("\n");
    for(int i=0;i<a.length;i++) {
      System.out.print(a[i]+" ,");
    }
    System.out.println("\n");
  }
}
