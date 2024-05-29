package com.n2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Given an ascendingly sorted array A of integers of length n and an integer X, determine whether it contains two distinct elements A[i] and A[j] such that A[i] + A[j] = X
 */
public class TestConditionInSequence {
  public boolean solution(int[] a, int x) {
    int n = a.length;
    int i = 0;
    int j = n - 1;
    while (i < j) {
      if (a[i] + a[j] == x) {
        return true;
      } else if (a[i] + a[j] < x) {
        i++;
      } else {
        j--;
      }
    }
    return false;
  }
  @Test
  void testSimple() {
    int[] a = {1,2,3,4,5,6,7,8,9,10};
    int x = 17;
    assertTrue(solution(a, x));
  }
  @Test
  void testSimple1() {
    int[] a = {1,2,3,4,5,6,7,8,9,10};
    int x = 19;
    assertTrue(solution(a, x));
  }
  @Test
  void testSimple2() {
    int[] a = {1,2,3,4,5,6,7,8,9,10};
    int x = 21;
    assertFalse(solution(a, x));
  }
  @Test
  void testSimple3() {
    int[] a = {1,2,3,4,5,6,7,8,9,10};
    int x = -1;
    assertFalse(solution(a, x));
  }
}
