package com.n2.codility;

/**
 * https://app.codility.com/programmers/lessons/12-euclidean_algorithm/chocolates_by_numbers/
 * <p>
 * Two positive integers N and M are given. Integer N represents the number of chocolates arranged
 * in a circle, numbered from 0 to N − 1.
 * <p>
 * You start to eat the chocolates. After eating a chocolate you leave only a wrapper.
 * <p>
 * You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on
 * the circle, and eat the following one.
 * <p>
 * More precisely, if you ate chocolate number X, then you will next eat the chocolate with number
 * (X + M) modulo N (remainder of division).
 * <p>
 * You stop eating when you encounter an empty wrapper.
 * <p>
 * For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2,
 * 6.
 * <p>
 * The goal is to count the number of chocolates that you will eat, following the above rules.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N, int M); }
 * <p>
 * that, given two positive integers N and M, returns the number of chocolates that you will eat.
 * <p>
 * For example, given integers N = 10 and M = 4. the function should return 5, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N and M are integers within the range [1..1,000,000,000].
 */
public class ChocolateByNumbers {

  static public int solution1(int n, int m) {
    int count = 1;//ate the first chocolate in the first index
    int first = 0;//start index to eat the chocolate
    int start = 0; // start of the current round
    int next = (start + m) % n;//next start to eat the chcolate
    System.out.println(next);
    while (next != first) {
      count++;
      start = next;
      next = (start + m) % n;
      System.out.println(next);
    }
    return count;
  }

  // LCM = (N*M)/GCD ; Result = LCM/m
  static public int solution2(int n, int m) {
    return ((n * m) / gcd(n, m)) / m;
  }

  static public int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
  }

  public static void main(String[] args) {
    System.out.println("n=10, m=4, count = " + solution1(10, 4));
    System.out.println("n=10, m=4, count = " + solution2(10, 4));
  }
}
