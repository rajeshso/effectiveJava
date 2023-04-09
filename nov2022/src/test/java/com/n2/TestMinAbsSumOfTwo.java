package com.n2;

import static java.lang.Math.abs;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

//https://app.codility.com/programmers/lessons/15-caterpillar_method/min_abs_sum_of_two/
public class TestMinAbsSumOfTwo {

  public int solution(int[] A) { //o(n^2)
    int len = A.length;
    int tailIndex = 0;
    int headIndex = 0;
    int currentMin = Integer.MAX_VALUE;
    while (tailIndex < len) {
      int first = A[tailIndex];
      while (headIndex < len) {
        int second = A[headIndex];
        int absSum = abs(first + second);
        System.out.println("("+first+","+second+")="+absSum);
        if (currentMin > absSum) {
          currentMin = absSum;
        }
        headIndex++;
      }
      tailIndex++;
      headIndex=0;
    }
    return currentMin;
  }

  public int solution1(int[] A) { //o(log n)
    Arrays.sort(A);
    int len = A.length;
    int currentMin = Integer.MAX_VALUE;
    int tailIndex = 0;
    int headIndex = len-1;
    while (headIndex>=tailIndex) {
      int first = A[tailIndex];
      int second = A[headIndex];
      int sum = first+second;
      int absSum = abs(sum);
      currentMin = Math.min(currentMin,absSum);
      if (sum>0) headIndex--; else tailIndex++;
    }
    return currentMin;
  }

  @Test
  void simpleTest() {
    int[] a = {1, 4, -3};
    assertThat(solution(a)).isEqualTo(1);
  }
  @Test
  void betterTest1() {
    int[] a = {1, 4, -3};
    //int[] a = {-7,3,-1,5,-11,4,-9,14,17,-2};
    assertThat(solution1(a)).isEqualTo(1);
  }  @Test
  void betterTest2() {
    int[] a = {-7,3,-1,5,-11,4,-9,14,17,-2};
    assertThat(solution1(a)).isEqualTo(1);
  }

  @Test
  void betterTest3() {
    int[] a = {3,4,5,9};
    assertThat(solution1(a)).isEqualTo(6);
  }
  @Test
  void betterTest4() {
    int[] a = {-22,3,4,5};
    assertThat(solution1(a)).isEqualTo(6);
  }
}
