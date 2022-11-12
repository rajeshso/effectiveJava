package com.n2;
//https://www.geeksforgeeks.org/minimum-number-of-manipulations-required-to-make-two-strings-anagram-without-deletion-of-character/

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Minimum Number of Manipulations required to make two Strings Anagram Without Deletion of Character
 */
public class AnagramMinimumDifference {

  private int solution(String s1, String s2) {

    int[] charCount = new int[26];
    int count = 0;
    for (int i = 0; i < s1.length(); i++) {
      charCount[s1.charAt(i)-'a']++;
    }
    for (int i = 0; i < s2.length(); i++) {
      if (charCount[s2.charAt(i)-'a']-- <= 0) {
        count++;
      }
    }
    return count;
  }

  @Test
  void test1() {
    assertThat(solution("aba", "baa")).isZero();
  }



  @Test
  void test2() {
    assertThat(solution("ddcf", "cedk")).isEqualTo(2);
  }
}
