package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Consecutive Elements: This refers to elements in the array that appear one after another.
 * Differences: This suggests the task involve calculating the differences between these consecutive elements.
 * Possible Problem Statement:
 *
 * Given an array of integers, find the maximum difference between any two consecutive elements in the array.
 *
 * Example Cases:
 *
 * Input: [1, 3, 7, 2, 4]
 *
 * Consecutive differences: 3-1=2, 7-3=4, 2-7=-5, 4-2=2
 * Maximum difference: 4 (between 7 and 3)
 * Input: [1, 1, 1, 1]
 *
 * Consecutive differences: 1-1=0, 1-1=0, 1-1=0
 * Maximum difference: 0 (all elements are the same)
 */
public class TestConsecutiveElementsDifferences {
  private static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(new int[]{1, 3, 7, 2, 4}, 4),
        Arguments.of(new int[]{1, 1, 1, 1}, 0)
    );
  }
  @ParameterizedTest
  @MethodSource("arguments")
  void testOddOccurrencesInArray(int[] givenArray, int expectedOddNumber) {
    assertThat(solution(givenArray)).isEqualTo(expectedOddNumber);
  }

  private int solution(int[] givenArray) {
    int maxDifference = Integer.MIN_VALUE;
    int len = givenArray.length;
    int previousElement = givenArray[0];
    for (int i = 1; i < len; i++) {
      int possibleResult = givenArray[i]-previousElement;
      if (possibleResult>maxDifference) {
        maxDifference = possibleResult;
      }
      previousElement=givenArray[i];
    }
    return Math.max(maxDifference, Integer.MIN_VALUE);
  }

}
