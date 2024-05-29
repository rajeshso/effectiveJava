package com.n2.codility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//Java code for finding the largest palindromic number from a given
//finds the largest palindromic number possible from the given string S by considering the frequency of each digit and creating the palindrome accordingly.
/**
 * The problem statement is:
 *
 * Given a string S consisting only of digits (0-9), find the largest palindromic number that can be formed by rearranging the digits in the string.
 *
 * Clarifications:
 *
 * The order of digits within the palindrome doesn't matter (e.g., "121" and "211" are considered the same palindrome).
 * Leading zeros are allowed in the resulting palindrome.
 * If no valid palindrome can be formed from the digits in S, the output should be "0".
 * Example:
 *
 * Input: S = "444947137"
 * Output: "7449447"
 * Explanation: Here, the largest palindrome that can be formed is "7449447" by using the digits "4449477" from the original string.
 */
public class TestNethermind2 {
  public String solution(String S) {
    int[] count = new int[10];
    for (char c : S.toCharArray()) {
      count[c - '0']++;
    }

    StringBuilder firstHalf = new StringBuilder();
    String center = "";

    // Build the first half of the palindrome and determine the center
    for (int i = 9; i >= 0; i--) {
      if (count[i] % 2 == 1 && center.isEmpty()) {
        center = String.valueOf(i);
      }
      for (int j = 0; j < count[i] / 2; j++) {
        firstHalf.append(i);
      }
    }

    // Construct the full palindrome
    String firstHalfStr = firstHalf.toString();
    String secondHalfStr = firstHalf.reverse().toString();
    String palindrome = firstHalfStr + center + secondHalfStr;

    return palindrome.isEmpty() ? "0" : palindrome;
  }

  @Test
  public void testSimple1() {
    String S = "00900";
    String actual = solution(S);
    System.out.println(actual);
    assertEquals("9", actual);
  }

  @Test
  public void testSimple2() {
    String S = "0000";
    String actual = solution(S);
    System.out.println(actual);
    assertEquals("0", actual);
  }

  @Test
  public void testSimple3() {
    String S = "54321";
    String actual = solution(S);
    System.out.println(actual);
    assertEquals("5", actual);
  }

  @Test
  public void testSimple4() {
    String S = "39878";
    String actual = solution(S);
    System.out.println(actual);
    assertEquals("878", actual);
  }

  @Test
  public void testExample() {
    String S = "444947137";
    String actual = solution(S);
    System.out.println(actual);
    assertEquals("7449447", actual);
  }

}
