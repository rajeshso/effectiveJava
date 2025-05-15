package com.n2.codility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * PermCheck
 *
 * Check whether array A is a permutation.
 * A non-empty array A consisting of N integers is given.
 *
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 *
 * For example, array A such that:
 *
 *     A[0] = 4
 *     A[1] = 1
 *     A[2] = 3
 *     A[3] = 2
 * is a permutation, but array A such that:
 *
 *     A[0] = 4
 *     A[1] = 1
 *     A[2] = 3
 * is not a permutation, because value 2 is missing.
 *
 * The goal is to check whether array A is a permutation.
 *
 * Write a function:
 *
 * class Solution { public int solution2(int[] A); }
 *
 * that, given an array A, returns 1 if array A is a permutation and 0 if it is not.
 *
 * For example, given array A such that:
 *
 *     A[0] = 4
 *     A[1] = 1
 *     A[2] = 3
 *     A[3] = 2
 * the function should return 1.
 *
 * Given array A such that:
 *
 *     A[0] = 4
 *     A[1] = 1
 *     A[2] = 3
 * the function should return 0.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 */
public class TestPermutationCheck {

  public static Stream<Arguments> testDataProvider() {
    return Stream.of(
        Arguments.arguments(new int[]{4,1,3,2}, 1),
        Arguments.arguments(new int[]{4,1,3}, 0),
        Arguments.arguments(new int[]{2, 3, 4, 1, 5}, 1),
        Arguments.arguments(new int[]{1, 2, 3, 5}, 0) ,
        //edge cases
        Arguments.arguments(new int[]{1}, 1),
        Arguments.arguments(new int[]{2}, 0),
        Arguments.arguments(new int[]{1, 2}, 1),
        Arguments.arguments(new int[]{2, 3}, 0),
        //edge cases with larger starting and ending numbers
        Arguments.arguments(new int[]{1000000000, 1000000001, 1000000002, 1000000003, 1000000004}, 0),
        Arguments.arguments(new int[]{1000000000, 1000000001, 1000000002, 1000000004}, 1),
        Arguments.arguments(new int[]{9, 5, 7, 3, 2, 7, 3, 1, 10, 8}, 0),
        //edge case with duplicate numbers
        Arguments.arguments(new int[]{1, 1, 1, 1, 1}, 0)
    );
  }

  /**
   * The main problems in this solution2 are:
   *
   * The assumption that the array contains consecutive numbers.
   * The logic for calculating the start and end sums is incorrect and not sufficient to determine if an array is a permutation.
   * Fails certain edge cases, particularly with arrays that are not starting from 1
   *
   * @param A
   * @return
   */
  public int solutionMy(int[] A) {
    final int[] aSorted = Arrays.stream(A).sorted().toArray();
    int start = aSorted[0];//min
    int end = aSorted[A.length-1];//max
    final int endSum = end * (end + 1) / 2;
    int startSum = 0;
    if (start!=1) startSum = (start) * (start + 1) / 2;
    int actualSum = endSum - startSum;
    int expectedSum = Arrays.stream(aSorted).sum();
    return Math.abs(actualSum-expectedSum)==0 ? 1 : 0;
  }
  public int solution2(int[] A) {
    if (A.length == 0) return 0;

    Set<Integer> seen = new HashSet<>();
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (int num : A) {
      if (seen.contains(num)) {
        return 0;  // Duplicates
      }
      seen.add(num);
      if (num < min) min = num;
      if (num > max) max = num;
    }

    // Check if all numbers from min to max are present
    return (max - min + 1 == A.length) ? 1 : 0;
  }
  public int solution3(int[] A) {
    if (A.length == 0) return 0;
    if (A.length == 1) return A[0] == 1 ? 1 : 0; // Correct single element check

    Arrays.sort(A);

    for (int i = 1; i < A.length; i++) {
      // Check for consecutive elements
      if (A[i] == A[i - 1] || A[i] != A[i - 1] + 1) {
        return 0;
      }
    }
    return A[0] == 1 ? 1 : 0; // Ensure the sequence starts at 1
  }

  @ParameterizedTest
  @MethodSource("testDataProvider")
  public void testSolution(int[] A, int expected) {
   // assertEquals(expected, solutionMy(A));
   // assertEquals(expected, solution2(A));
   // assertEquals(expected, solution3(A)); //Better of the three
  }

}
