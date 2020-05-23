package com.n2.arrays;

import java.util.Arrays;

public class FindTheMissingElement {
  public static int solution(int[] s) {
    int len = s.length;
    int missing = 0;
    boolean missingFound= false;
    Arrays.sort(s);
    for (int i=0;i<len-1;i++) {
      int currentNumber  = s[i];
      int nextNumber = s[i+1];
      if (currentNumber+1 != nextNumber) {
        missing = currentNumber+1;
        missingFound = true;
        break;
      }
    }
    if (!missingFound) {
      missing = s[len-1]+1;
    }
    return missing;
  }

  public static int solution1(int[] s) {
    int sumOfAllElements = sumOfAllElements(s);
    int greatestNumber = findGreatestNumber(s);
    int sumOfAllNumbers = sumOfAllNumbers(greatestNumber);
    return sumOfAllNumbers-sumOfAllElements;
  }

  private static int findGreatestNumber(int[] s) {
    Arrays.sort(s);
    return s[s.length-1];
  }

  private static int sumOfAllElements(int[] s) {
    int result = 0;
    for (int i = 0; i < s.length; i++) {
      result+=s[i];
    }
    return result;
  }

  private static int sumOfAllNumbers(int s) {
    return (s*(s+1))/2;
  }

}
