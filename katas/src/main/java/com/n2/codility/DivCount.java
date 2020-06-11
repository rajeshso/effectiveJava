package com.n2.codility;

/**
 * https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int A, int B, int K); }
 * <p>
 * that, given three integers A, B and K, returns the number of integers within the range [A..B]
 * that are divisible by K, i.e.:
 * <p>
 * { i : A ≤ i ≤ B, i mod K = 0 }
 * <p>
 * For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three
 * numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * A and B are integers within the range [0..2,000,000,000]; K is an integer within the range
 * [1..2,000,000,000]; A ≤ B.
 */
public class DivCount {

  public static int solution1(int a, int b, int k) {
    int result = 0;
    for (int i = a; i <=b ; i++) {
      if (i%k == 0) result+=1;
    }
    return result;
  }

  public static int solution2(int a, int b, int k) {
    int result = 0;
    int indexOfKMultiple = 0;
    for (int i = a; i <=b ; i++) {
      indexOfKMultiple++;
      if (i%k == 0) {
        break;
      }
    }
    indexOfKMultiple++;
    result = ((b-a+1)/indexOfKMultiple);
    return result;
  }
}
