package com.n2;
//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class CyclicRotation {
  int[] solution1(int[] a,int k) {
    int len = a.length;
    int[] result = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      int srcPos = i;
      int destPos = (i+k)%len;
      result[destPos] = a[srcPos];
    }
    return result;
  }
  public int[] solution2(int[] a, int k) {
    int n = a.length;
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      int destPos = (i + k) % n;
      result[destPos] = a[i];
    }
    return result;
  }

}
