package com.n2.misc;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given somasundaram should return {a=3, r=1, s=2, d=1, u=1, m=2, n=1, o=1}
 */
public class LetterFrequencyInWords {
  public static Map<Character,Long> solution(String word) {
    return word.chars().mapToObj(i -> (char) i)
        .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
  }

  public static void main(String[] args) {
    System.out.println(solution("somasundaram"));
  }
}
