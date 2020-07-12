package com.n2.misc;

/**
 * https://www.jaktech.co.uk/interview-questions/t-rowe-price-software-engineer-interview-question/
 *
 * Write a method or function in the major programming language
 * of your choice that returns the longest word in a sentence and
 * its length. For example, “The cow jumped over the moon.”
 * should return “jumped” and 6.
 */
public class LongestWordInSentence {
  public static String solution(String input) {
    final String[] words = input.trim().split("\\s+");
    int len = words.length;
    String bigWord="";
    int max = 0;
    for (int i = 0; i < len; i++) {
      String word = words[i];
      int wordLen = word.length();
      if (wordLen>max) {
        max = wordLen;
        bigWord= word;
      }
    }
    return bigWord+" "+max;
  }

  public static void main(String[] args) {
    System.out.println(solution("The cow jumped over the moon."));
    System.out.println(solution(""));
  }
}
