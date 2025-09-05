package com.n2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

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

  static Stream<Arguments> sequenceProvider() {
    return Stream.of(
      Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 17, true),
      Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 19, true),
      Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 21, false),
      Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, -1, false),
      Arguments.of(new int[]{2,4,6,8,10}, 12, true),
      Arguments.of(new int[]{2,4,6,8,10}, 3, false)
    );
  }

  @ParameterizedTest
  @MethodSource("sequenceProvider")
  void testSolution(int[] a, int x, boolean expected) {
    if (expected) {
      assertTrue(solution(a, x));
    } else {
      assertFalse(solution(a, x));
    }
  }
}
