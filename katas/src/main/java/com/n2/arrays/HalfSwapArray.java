package com.n2.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Problem: Write a method that swaps the first and second halves of an array.
 *
 * Method Signature:
 *
 * public static int[] solution(int[] s)
 *
 * Requirements:
 *
 * Take an input array and return a new array where the first half and second half are swapped
 *
 * For even-length arrays: split exactly in the middle
 *
 * For odd-length arrays: the first half gets the extra element
 *
 * Examples:
 *
 * Input: [1, 2, 3, 4] → Output: [3, 4, 1, 2]
 *
 * Input: [1, 2, 3, 4, 5] → Output: [4, 5, 1, 2, 3]
 *
 * Input: [1, 2] → Output: [2, 1]
 *
 * Input: [1] → Output: [1]
 *
 * Constraints:
 *
 * Return a new array (don't modify the original)
 *
 * Handle both even and odd length arrays
 *
 * Use System.arraycopy() for efficient copying
 *
 * Hints:
 *
 * For even arrays: each half has length/2 elements
 *
 * For odd arrays: first half has (length/2) + 1 elements, second half has length/2 elements
 */
public class HalfSwapArray {
  public static int[] solution(int[] s) {
    int[] result = new int[s.length];
    if (s.length%2==0) {
      System.arraycopy(s,0,result,s.length/2,s.length/2);
      System.arraycopy(s,s.length/2,result,0,s.length/2);
    }else {
      System.arraycopy(s,0,result,(s.length/2),(s.length/2)+1);
      System.arraycopy(s,(s.length/2)+1,result,0,s.length/2);
    }
    print(result);
    return result;
  }
  private static void print(int[] a) {
    System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
  }

  public static int[] solution2(int[] s) {
    int len = s.length;
    int[] result = new int[s.length];
    if (len%2==0) {
      int[] firstHalf = Arrays.copyOfRange(s, 0, len/2);
      int[] secondHalf = Arrays.copyOfRange(s, len/2, len);
      System.arraycopy(firstHalf, 0, result,  firstHalf.length, firstHalf.length);
      System.arraycopy(secondHalf, 0, result, 0, secondHalf.length);
    } else {
      int[] firstHalf = Arrays.copyOfRange(s, 0, (len/2)+1);
      int[] secondHalf = Arrays.copyOfRange(s, (len/2)+1, len);
      System.arraycopy(firstHalf, 0, result,  firstHalf.length-1, firstHalf.length);
      System.arraycopy(secondHalf, 0, result, 0, secondHalf.length);
    }
    System.out.println("Original");
    print(s);
    System.out.println("Final");
    print(result);
    return result;
  }
}
