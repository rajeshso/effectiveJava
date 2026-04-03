package com.n2.gbst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestArraysAndStrings {

  @DisplayName("Palindrome Permutation")
  /**
   * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palin- drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.The palindrome does not need to be limited to just dictionary words.
   * EXAMPLE
   * Input: Tact Coa
   * Output: True (permutations: "taco cat". "atco cta". etc.)
   */
  @ParameterizedTest(name = "{index} => input={0}, expected={1}")
  @MethodSource("palindromePermutationTestData")
  // MethodSource annotation is used to provide test data for the parameterized test
  void testPalindromePermutation(String input, boolean expected) {
    assertEquals(expected, isPalindromePermutation(input));
    assertEquals(expected, isPalindromePermutation1(input));
    assertEquals(expected, isPalindromePermutation2(input));
  }

  /**
   * A string can form a palindrome if and only if:
   *
   * At most one character has an odd count.
   *
   * For example:
   *
   * "civic" → valid: c(2), i(2), v(1) → only one odd
   *
   * "ivicc" → valid permutation: civic
   *
   * "hello" → invalid: h(1), e(1), l(2), o(1) → three odds
   *
   * So, we need to:
   *
   * Ignore spaces
   *
   * Ignore case
   *
   * Count the frequency of each character
   *
   * Make sure only one character (or none) has an odd count
   * @param input
   * @return
   */
  private boolean isPalindromePermutation(String input) {
    final int length = input.length();
    Map<Character, Integer> charCount = new HashMap<>(length);
    char[] chars = input.toCharArray();
    for (int i = 0; i < length; i++) {
      char c = input.charAt(i);
      if (c == ' ') continue; // ignore spaces
      if (c >= 'A' && c <= 'Z') {
        c += 32; // convert to lowercase
      }

      charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }
    int oddCount = 0;
    for (int count : charCount.values()) {
      if (count % 2 != 0) {
        oddCount++;
        if (oddCount > 1) return false; // more than one character has an odd count
      }
    }
    return true;
  }

  private boolean isPalindromePermutation1(String input) {
    final int length = input.trim().length();
    Map<Character, Integer> charCount = new HashMap<>(length);
    //Ignore spaces
    //Ignore case
    //Count the frequency of each character
    input.chars()
        .filter(c-> !Character.isWhitespace(c))
        .map(Character::toLowerCase)
        .forEach(c-> charCount.put(
        (char) c,(charCount.getOrDefault((char) c,0))+1));
    int oddCount = 0;
    for(Map.Entry<Character, Integer> entry: charCount.entrySet()) {
     // System.out.println(entry.getKey()+" "+entry.getValue());
      if (entry.getValue() % 2 != 0) {
        oddCount++;
        if (oddCount > 1) return false; // more than one character has an odd count
      }
    }
    return true;
  }
  // JDK 23 optimized version - same O(n) complexity
  private boolean isPalindromePermutation2(String input) {
    return input.chars()
        .filter(c->!Character.isWhitespace(c))
        .map(Character::toLowerCase)
        .boxed()
        .collect(Collectors.groupingBy(c->c,Collectors.counting()))
        .values()
        .stream()
        .mapToLong(count->count%2)
        .sum() <=1;
  }


  static Stream<Arguments> palindromePermutationTestData() {
    return Stream.of(
        // Happy cases - valid palindrome permutations
        Arguments.of("Tact Coa", true),
        Arguments.of("civic", true),
        Arguments.of("aab", true),
        Arguments.of("racecar", true),
        Arguments.of("A man a plan a canal Panama", true),
        Arguments.of("Madam", true),
        Arguments.of("aabbcc", true),
        
        // Sad cases - invalid palindrome permutations
        Arguments.of("no", false),
        Arguments.of("ccccivic", false),
        Arguments.of("hello", false),
        Arguments.of("abcdef", false),
        Arguments.of("aabbccd", true),
        
        // Edge cases
        Arguments.of("", true),
        Arguments.of(" ", true),
        Arguments.of("a", true),
        Arguments.of("aa", true),
        Arguments.of("ab", false),
        Arguments.of("   ", true),
        Arguments.of("A", true),
        Arguments.of("Aa", true)
    );
  }
}

