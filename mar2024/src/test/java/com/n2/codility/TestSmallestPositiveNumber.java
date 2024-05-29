package com.n2.codility;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This is a demo task.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 *
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 *
 * Given A = [1, 2, 3], the function should return 4.
 *
 * Given A = [−1, −3], the function should return 1.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 */
public class TestSmallestPositiveNumber {

  public int solution(int[] A) {
    //Filter negatives and sort and distinct the remaining
    final int[] array = Arrays.stream(A).filter(x -> x > 0).sorted().distinct().toArray();
    //Identify the missing integer
    // The smallest positive integer to be found
    int smallestMissingPositive = 1;
    // Traverse through the sorted positive numbers
    //If the current number equals smallestMissingPositive, increment it.
    for (int i = 0; i < array.length; i++) {
      if (array[i] == smallestMissingPositive) {
        smallestMissingPositive++;
      } else if (array[i] > smallestMissingPositive) {
        return smallestMissingPositive;
      }
    }
    //If the current number is greater than smallestMissingPositive, stop the loop as we have found the smallest missing positive integer.
    return smallestMissingPositive;
  }

  // given A = [1, 3, 6, 4, 1, 2], the function should return 5.
  @Test
  public void testSimple1() {
    int[] A = {1, 3, 6, 4, 1, 2};
    int result = solution(A);
    System.out.println(result);
    Assertions.assertEquals(5, result);
  }
  // Given A = [1, 2, 3], the function should return 4
  @Test
  public void testSimple2() {
    int[] A = {1, 2, 3};
    int result = solution(A);
    System.out.println(result);
    Assertions.assertEquals(4, result);
  }
  //Given A = [−1, −3], the function should return 1.
  @Test
  public void testSimple3() {
    int[] A = {-1, -3};
    int result = solution(A);
    System.out.println(result);
    Assertions.assertEquals(1, result);
  }
}
