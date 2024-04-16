package com.n2;

import java.util.stream.IntStream;

public class MyFactorial {

  public static void main(String[] args) {
    System.out.println(factorialIterative(4));
    System.out.println(factorialRecursive(4));
    System.out.println(factorialStreams(4));
  }

  private static long factorialIterative(int i) {
    long result = 1;
    for (int j = 1; j <= i; j++) {
      result = result*j;
    }
    return result;
  }
  private static long factorialRecursive(int i) {
    return factoRecursive(1, i);
  }

  private static long factorialStreams(int i) {
    return IntStream.rangeClosed(1,i).reduce(1, (acc, a)-> acc*a);
  }

  private static long factoRecursive(long acc, int n) {
    if (n==0)
      return acc;
    return factoRecursive(acc*n,n-1);
  }
}

