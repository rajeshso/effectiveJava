package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
 *
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
 *
 * Write a function:
 *
 * class Solution { public int[] solution(int[] A, int K); }
 *
 * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
 *
 * For example, given
 *
 *     A = [3, 8, 9, 7, 6]
 *     K = 3
 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
 *
 *     [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 *     [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 *     [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 * For another example, given
 *
 *     A = [0, 0, 0]
 *     K = 1
 * the function should return [0, 0, 0]
 *
 * Given
 *
 *     A = [1, 2, 3, 4]
 *     K = 4
 * the function should return [1, 2, 3, 4]
 *
 * Assume that:
 *
 * N and K are integers within the range [0..100];
 * each element of array A is an integer within the range [−1,000..1,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */
public class TestCyclicRotation {

  private static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(new int[]{3, 8, 9, 7, 6},3,new int[]{9, 7, 6, 3, 8}),
        Arguments.of(new int[]{0, 0, 0},1,new int[]{0, 0, 0}),
        Arguments.of(new int[]{1, 2, 3, 4},4,new int[]{1, 2, 3, 4}),
        Arguments.of(new int[]{1, 2, 3, 4},0,new int[]{1, 2, 3, 4}),
        Arguments.of(new int[]{1, 2, 3, 4},1,new int[]{4, 1, 2, 3}),
        Arguments.of(new int[]{1, 2, 3, 4},2,new int[]{3, 4, 1, 2})
    );
  }
  @ParameterizedTest
  @MethodSource("arguments")
  public void testSimple(int[] a, int k, int[] expected) {
 //   assertThat(solution(a,k)).containsExactly(expected);
    assertThat(solutionOptimal1(a,k)).containsExactly(expected);
  }
  private int[] solution(int[] a, int k) {
    int len = a.length;
    if (len==0) {
      return a;
    }
    int[] result = new int[len];
    int srcIndex;
    int srcValue;
    int destIndex;
    int destValue;
    for (int i = 0; i < len; i++) {
      //init srcIndex=i, srcValue=a[srcIndex]
      srcIndex = i;
      srcValue=a[srcIndex];
      //Calculate the destIndex and destValue by the formula destIndex=(srcIndex+k)%len
      destIndex=(srcIndex+k)%len;
      destValue=srcValue;
      //destValue=srcValue in result
      result[destIndex] = destValue;
    }
    return result;
  }

  //in-place rotation approach
  private int[] solutionOptimal1(int[] a, int k) {
    System.out.println("a = "+ Arrays.toString(a)  +" k = "+k);
    int len = a.length;
    k %= len; // Handle the case where k is greater than or equal to the array length
    System.out.println("a = "+ Arrays.toString(a) +" len = "+len +" k%=len = "+k);
    rotateSwap(a,0,len-1);// Reverse the entire array
    System.out.println("a = "+ Arrays.toString(a) + " Reversed the entire array");
    rotateSwap(a,0,k-1);// Reverse the first k elements
    System.out.println("a = "+ Arrays.toString(a) + " Reversed the first k "+ k + " elements");
    rotateSwap(a,k,len-1); // Reverse the remaining elements (from k to the end)
    System.out.println("a = "+ Arrays.toString(a) +" Reversed the remaining elements (from k "+k+" to the end len-1 "+(len-1)+")");
    return a;
  }
  private void rotateSwap(int[] arr, int start, int end) {
    while (start < end) {
      int temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
  }
}
