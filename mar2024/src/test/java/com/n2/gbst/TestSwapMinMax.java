package com.n2.gbst;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//swap the minimum and maximum element in an integer array.
public class TestSwapMinMax {
  //Space Complexity: O(1), since we are using a constant amount of extra space.
  //Time Complexity: O(n), since we are iterating through the array once to find the min and max elements.
  private int[] swap(int[] actual) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    int minIndex = 0;
    int maxIndex = 0;
    int len = actual.length;
    if (len <= 1) return actual;
    for (int i = 0; i < len; i++) {
      if (actual[i] < min) {
        min = actual[i];
        minIndex= i;
      }
      if (actual[i] > max) {
        max = actual[i];
        maxIndex= i;
      }
    }
    if (minIndex != maxIndex) {
      int temp = actual[minIndex];
      actual[minIndex] = actual[maxIndex];
      actual[maxIndex] = temp;
    }
    return actual;
  }

  private int[] swap2(int[] actual) {
    int len = actual.length;
    if (len <= 1) return actual;
    int minIndex = 0;
    int maxIndex = 0;
    for (int i = 1; i < len; i++) {
      if (actual[i] < actual[minIndex]) {
        minIndex = i;
      }
      if (actual[i] > actual[maxIndex]) {
        maxIndex = i;
      }
    }
    if (minIndex != maxIndex) {
      int temp = actual[minIndex];
      actual[minIndex] = actual[maxIndex];
      actual[maxIndex] = temp;
    }
    return actual;
  }
  @DisplayName("Test Swap Min Max")
  @ParameterizedTest(name = "{index} => swap={0}, expected={1}")
  @MethodSource("swapMinMaxTestData")
  void testSwap(int[] actual, int[] expected) {
    assertArrayEquals(expected, swap(actual));
    //assertArrayEquals(expected, swap2(actual));
  }

  static Stream<Arguments> swapMinMaxTestData() {
    return Stream.of(
        // Happy Path Cases
        Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 4, 1}),
        Arguments.of(new int[]{1, 2, 3}, new int[]{3, 2, 1}),
        Arguments.of(new int[]{1, 2, 3, 4}, new int[]{4, 2, 3, 1}),
        Arguments.of(new int[]{-5, 4, 3, 2, 1}, new int[]{4, -5, 3, 2, 1}),
        Arguments.of(new int[]{10, 5, 8, 3, 12}, new int[]{10, 5, 8, 12, 3}),
        
        // Edge Cases
        Arguments.of(new int[]{}, new int[]{}),
        Arguments.of(new int[]{1}, new int[]{1}),
        Arguments.of(new int[]{1, 2}, new int[]{2, 1}),
        Arguments.of(new int[]{5, 5}, new int[]{5, 5}),
        Arguments.of(new int[]{3, 3, 3}, new int[]{3, 3, 3}),
        Arguments.of(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}),
        
        // Sad Cases
        Arguments.of(new int[]{-10, -5, -1}, new int[]{-1, -5, -10}),
        Arguments.of(new int[]{-3, -3, -3}, new int[]{-3, -3, -3}),
        Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0})
    );
  }
}
