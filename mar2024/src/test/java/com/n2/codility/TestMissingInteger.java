package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * MissingInteger
 * <p>
 * Find the smallest positive integer that does not occur in a given sequence. This is a demo task.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that
 * does not occur in A.
 * <p>
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 * <p>
 * Given A = [1, 2, 3], the function should return 4.
 * <p>
 * Given A = [−1, −3], the function should return 1.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000]; each element of array A is an integer within the
 * range [−1,000,000..1,000,000].
 */
public class TestMissingInteger {

  public static Stream<Arguments> testDataProvider() {
    return Stream.of(
        Arguments.arguments(new int[]{1, 3, 6, 4, 1, 2}, 5),
        Arguments.arguments(new int[]{1, 2, 3}, 4),
        Arguments.arguments(new int[]{-1, -3}, 1),
        //Single positive Element Edge Case
        Arguments.arguments(new int[]{1}, 2),
        Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11),
        Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, 12),
        Arguments.arguments(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, 12),
        Arguments.arguments(new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, 12)
    );
  }
  //Efficiency: Sorting the array makes the time complexity O(n log n). However, this problem can be solved in O(n) time complexity with O(n) space complexity.
  //  O(n log n) + O(n)
  public int solutionMy(int[] A) {
    int[] aSorted = IntStream.of(A).filter(a -> a > 0).distinct().sorted().toArray();// O(n log n)
    int len = aSorted.length;
    if (len == 0) {
      return 1;
    }
    if (len == 1) {
      return aSorted[0] + 1;
    }
    for (int i = 1; i < aSorted.length; i++) {//O(n)
      //Check the consecutive elements
      if (aSorted[i] != aSorted[i - 1] + 1) {
        return aSorted[i - 1] + 1;
      }
    }
    return aSorted[aSorted.length - 1] + 1;
  }

  /**
   * Explanation:
   * Boolean Array: We use a boolean array present of size n + 1 to keep track of which positive integers from 1 to n are present in the array.
   * Mark Presence: We iterate over the input array and mark the corresponding indices in the boolean array as true.
   * Find Missing Integer: We then iterate over the boolean array starting from 1 to find the first index which is false, indicating the smallest missing positive integer.
   * Edge Case: If all integers from 1 to n are present, the smallest missing positive integer is n + 1.
   * Performance:
   * Time Complexity: O(n), because we traverse the array a constant number of times.
   * Space Complexity: O(n), due to the additional boolean array of size n + 1.
   * @param A
   * @return
   */
  public int solutionOptimal(int[] A) {
    int n = A.length;
    return n;
  }

  @ParameterizedTest
  @MethodSource("testDataProvider")
  public void testSolution(int[] A, int expected) {
    assertThat(solutionMy(A)).isEqualTo(expected);
  }

}
