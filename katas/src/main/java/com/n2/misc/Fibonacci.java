package com.n2.misc;

public class Fibonacci {
  protected static int[] fibonacci(int n) {
    int t1 = 0, t2 = 1, t3 =0;
    int[] result = new int[n];
    result[0]= t1;
    result[1] = t2;
    for(int i=2;i<9;i++) {
      t3 = t1 + t2;
      result[i] = t3;
      t1 = t2;
      t2= t3;
    }
    return result;
  }
}
