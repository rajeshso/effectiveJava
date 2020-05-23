package com.n2;

import java.util.HashMap;
import java.util.Map;

/**
 * We are given a string S consisting of N lowercase letters.
 * A sequence of two adjacent letters inside a string is called a digram.
 * The distance between two digrams is the distance between the first letter
 * of the first digram and the first letter of the second digram.
 * For example, in string S = "abcde" the distance between digrams "bc"
 * and "de" is 2. We want to find the distance between the furthest identical
 * digrams inside string S. Write a function: that, given a string S consisting
 * of N letters, returns the distance between the two identical digrams in
 * the string that lie furthest away from each other. If there are no two
 * identical digrams inside S, your function should return -1.
 * Examples: 1. Given S = "aabcaabcabda" your function should return 7.
 * The furthest identical digrams are "ab"s, starting in positions 2 and 9
 * (enumerating from 1): "aabcaabcabda". 2. Given S = "aaa" your function
 * should return 1. The furthest identical digrams are "aa"s starting at
 * positions 1 and 2. 3. Given S = "codility" your function should return -1.
 * There are no two identical digrams in S. Write an efficient algorithm for
 * the following assumptions.
 */
public class Diagram {

  public int solution(String s) {
    Map<String, Integer> diagramCount = new HashMap<>(s.length());
    int highestDifference = -1;
    for(int i=0;i<(s.length()-1);i++) {
      String diagram = s.substring(i,i+2);
      final Integer lowestPosition = diagramCount.get(diagram);
      if (lowestPosition!=null) {
        //calculate the highestDifference
        int difference = i-lowestPosition;
        if (difference>highestDifference) {
          highestDifference = difference;
        }
      }else {
        diagramCount.put(diagram,i);
      }
    }
    System.out.println(diagramCount);
    return highestDifference;
  }
}
