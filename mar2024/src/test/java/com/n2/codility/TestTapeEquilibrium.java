package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.
 *
 * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
 *
 * The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
 *
 * In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
 *
 * For example, consider array A such that:
 *
 *   A[0] = 3
 *   A[1] = 1
 *   A[2] = 2
 *   A[3] = 4
 *   A[4] = 3
 * We can split this tape in four places:
 *
 * P = 1, difference = |3 − 10| = 7
 * P = 2, difference = |4 − 9| = 5
 * P = 3, difference = |6 − 7| = 1
 * P = 4, difference = |10 − 3| = 7
 * Write a function:
 *
 * class Solution { public int solutionMy(int[] A); }
 *
 * that, given a non-empty array A of N integers, returns the minimal difference that can be achieved.
 *
 * For example, given:
 *
 *   A[0] = 3
 *   A[1] = 1
 *   A[2] = 2
 *   A[3] = 4
 *   A[4] = 3
 * the function should return 1, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
 */
public class TestTapeEquilibrium {
  // O(n) + O(n)
  public int solution(int[] A) {
    int len = A.length;
    int totalSum = Arrays.stream(A).sum(); // Total sum of the array O(n)
    int minDifference = Integer.MAX_VALUE; // Initialize minDifference with a large value
    int firstPartSum = 0; // Initialize the sum of the first part

    for (int i = 0; i < len - 1; i++) { //O(n)
      firstPartSum += A[i]; // Accumulate the sum for the first part
      int secondPartSum = totalSum - firstPartSum; // Calculate the second part sum
      int difference = Math.abs(firstPartSum - secondPartSum); // Calculate the absolute difference
      minDifference = Math.min(minDifference,difference);// Update minDifference if a smaller difference is found
    }
    return minDifference; // Return the minimum difference
  }

  @ParameterizedTest
  @MethodSource("arguments")
  public void test(int[] A, int expected) {
    assertThat(solution(A)).isEqualTo(expected);
  }
  private static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(new int[]{3, 1, 2, 4, 3}, 1),
        Arguments.of(new int[]{1, 2, 3, 4, 5}, 3),
        Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1)
    );
  }
}
