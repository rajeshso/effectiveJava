package com.n2.codility;

import java.util.Arrays;

public class Test {
  public int solution(int[] A) {
    int missingNumber = 0;
    boolean missingNumberFound = false;
    A = removeDups(A);
    for (int i = 0; i < A.length-1; i++) {
      if (A[i+1] != A[i]+1) {
        missingNumberFound = true;
        missingNumber = A[i]+1;
        break;
      }
    }
    if (!missingNumberFound) {
      missingNumber = A[A.length-1]+1;
    }
    if (missingNumber<=0) {
      return 1;
    }
    return missingNumber;
  }
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
    int[] result1 = Arrays.copyOf(result, index);
    return result1;
  }
  public static void main(String[] args) {
    int[] arr = new int[]{1, 3, 6, 4, 1, 2};
    Test test = new Test();
  //  System.out.println(test.solution(arr));
    System.out.println(test.solution(new int[]{-1,-3}));
  }
}
