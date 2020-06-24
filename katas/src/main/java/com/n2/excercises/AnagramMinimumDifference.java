package com.n2.excercises;

public class AnagramMinimumDifference {

  // Counts the no of manipulations required
  static int countManipulations(String s1, String s2) {
    int count = 0;

    int[] char_count = new int[26];

    for (int i = 0; i < s1.length(); i++) {
      char_count[s1.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s2.length(); i++) {
      if (char_count[s2.charAt(i) - 'a']-- <= 0) {
        count++;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    System.out.println(countManipulations("ddcf", "cedk"));
  }
}

