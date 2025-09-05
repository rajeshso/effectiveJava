package com.n2.codility;
public class Equilibirum {
  //O(N)
  public static int solution(int[] s) {
    int pivot = -1;
    int balance=Integer.MAX_VALUE;
    int len = s.length;
    int leftSum = s[0];
    int rightSum = 0;
    for (int i = 1; i < len; i++) {
      rightSum+=s[i];
    }
    for (int i = 1; i < len; i++) {
      if (Math.abs(rightSum-leftSum)<balance) {
        balance = Math.abs(rightSum-leftSum);
        pivot = i;
      }
      leftSum+=s[i];
      rightSum-=s[i];
    }
    return balance;
  }
  //O(N^2)
  public static int solution1(int[] s) {
    int balance=Integer.MAX_VALUE;
    int len = s.length;
    for (int i = 1; i < len; i++) {
      int rightSum = 0;
      int leftSum = 0;
      for (int j = 0; j < i; j++) {
        leftSum+=s[j];
      }
      for (int j = i; j < len; j++) {
        rightSum+=s[j];
      }
      if (Math.abs(rightSum-leftSum)<balance) {
        balance = Math.abs(rightSum-leftSum);
      }
    }
    return balance;
  }
}
