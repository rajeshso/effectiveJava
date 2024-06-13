package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Find the missing element in a given permutation.
 *
 * An array A consisting of N different integers is given.
 * The array contains integers in the range [1..(N + 1)],
 * which means that exactly one element is missing.
 *
 * Your goal is to find that missing element.
 *
 * Write a function:
 *
 * class Solution { public int solution1(int[] A); }
 *
 * that, given an array A, returns the value of the missing element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 2
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 5
 * the function should return 4, as it is the missing element.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 */
public class TestPermMissingElement {
  //Mathematical solution
  public int solution1(int[] A) {
    int n = A.length;
    //sort the array
    final int[] sortedArray = Arrays.stream(A).distinct().sorted().toArray();
    int head = sortedArray[0];//min value
    int tail = sortedArray[n-1];//max value
    //Calculate the expected sum
    int expected = n*(n+1)/2; //TODO: Incorrect formula because we are not using the head and tail elements. Correct this
    //Calculate the actual sum
    int actual = Arrays.stream(sortedArray).sum();
    //Calculate the difference expected-actual
    int difference = expected-actual;
    //If the difference is present (positive), then return the value
    //If the difference is negative, then return -1, indicating that the input array is against the instructions given
    //If the difference is zero, then return  actual+1
    if (difference > 0) {
      return difference;
    } else if (difference == 0) {
      return tail + 1; // Return n+1 if all elements are present
    } else {
      return -1;
    }
  }

  //Iterative solution
  public int solution2(int[] A) {
    int n = A.length;
    if (n==0) {
      return 1; //return one if it's an empty array
    }
    //sort the array
    final int[] sortedArray = Arrays.stream(A).distinct().sorted().toArray();
    //Iterate through the array from first to last-1
    for (int i = 0; i < n - 1; i++) {
      //Find if the first element+1 is equal to the next, if not equal, you have found the missing element
      int currentElement = sortedArray[i];
      int calculatedNextElement = currentElement+1;
      int actualNextElement = sortedArray[i+1];
      if (calculatedNextElement == actualNextElement) {
        continue;
      }else {
        return calculatedNextElement;
      }
    }
    //If after the iteration, you have not found the missing element, the missing element is last element of the array plus one
    return sortedArray[n-1]+1 == 0 ?1:sortedArray[n-1]+1 ;
  }
  @ParameterizedTest
  @MethodSource("argumentsStream")
  public void test(int[] A, int expected) {
    assertThat(solution1(A)).isEqualTo(expected);
    assertThat(solution2(A)).isEqualTo(expected);
  }
  private static Stream<Arguments> argumentsStream() {
    return Stream.of(
        Arguments.of(new int[]{2,3,1,5},4),
        Arguments.of(new int[]{2,3,1,5,4},6),
        Arguments.of(new int[]{2,3,1,5,4,6},7),
        Arguments.of(new int[]{2,3,1,5,4,6,8},7),
        Arguments.of(new int[]{},1),
        Arguments.of(new int[]{1},2),
        Arguments.of(new int[]{-2,-3,-1,-5,-4,-6,-8},-7),
        Arguments.of(new int[]{-2,-3,-1,-5,-4,-6,-8,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21},-7),//test case with both negative and positive integers
        Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21},20)
    );
  }
}
