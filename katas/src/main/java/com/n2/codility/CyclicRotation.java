package com.n2.codility;

public class CyclicRotation {
  //int[] arr = {5,3,4,1,2}
  public static int[] solution(int[] A, int K) {
    int len = A.length;
    int[] result = new int[A.length];
    for (int i = 0; i < len; i++) {
      int remainder_trick = (i+K)%len;
      result[remainder_trick] = A[i];
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

  public static void main(String[] args) {
    solution(new int[]{5,4,3,2,1}, 5);
  }
}
