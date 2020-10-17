package com.n2.misc;

import static java.lang.Math.*;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.sort;

import java.util.Arrays;

public class CheapLetterDeletion {

  public static void main(String[] args) {
    char[] S1 = "abccbd".toCharArray();
    System.out.println(solution("abccbd", new int[]{0, 1, 2, 3, 4, 5}));

    char[] S2 = "aabbcc".toCharArray();
    System.out.println(solution("aabbcc", new int[]{1, 2, 1, 2, 1, 2}));

    char[] S3 = "aaa".toCharArray();
    System.out.println(solution("aaaa", new int[]{3, 4, 5, 6}));

    char[] S4 = "ababa".toCharArray();
    System.out.println(solution("ababa", new int[]{10, 5, 10, 5, 10}));

    char[] S5 = "ababaabccdadddddc".toCharArray();
    System.out.println(solution("ababaabccdadddddc",
        new int[]{10, 5, 10, 5, 10, 10, 5, 10, 5, 10, 5, 10, 10, 5, 10, 5, 10}));

    char[] S6 = "zzwzz".toCharArray();
    System.out.println(solution("zzwzz", new int[]{5, 4, 3, 2, 1}));

  //  char[] S7 = "zzzzzzzzzzzzzzzz".toCharArray();
  //  System.out.println(solution("zzzzzzzzzzzzzzzz", new int[]{1,1,1,1,1,1,1,1,1,11,1,1,1,1,1}));


/*    //our solution
    char[] S11 = "abccbd".toCharArray();
    solution("abccbd", new int[]{0, 1, 2, 3, 4, 5});

    char[] S22 = "aabbcc".toCharArray();
    solution("aabbcc", new int[]{1, 2, 1, 2, 1, 2});

    char[] S33 = "aaa".toCharArray();
    solution("aaaa", new int[]{3, 4, 5, 6});

    char[] S44 = "ababa".toCharArray();
    solution("ababa", new int[]{10, 5, 10, 5, 10});

    char[] S55 = "ababaabccdadddddc".toCharArray();
    solution("ababaabccdadddddc",
        new int[]{10, 5, 10, 5, 10, 10, 5, 10, 5, 10, 5, 10, 10, 5, 10, 5, 10});

    char[] S66 = "zzwzz".toCharArray();
    System.out.println(solution("zzwzz", new int[]{5, 4, 3, 2, 1}));*/

  }

  static public int solution(String S, int[] C) {
    char chars[] = S.toCharArray();
    int length = chars.length;
    int minimalCostofDeletions = 0;

    if (tooShort(length)) return minimalCostofDeletions;

    int a = 0;
    for (int i = 1; i < length; i++)
    {
      if (isUnique(chars[a], chars[i]))
      {
        a++;
        chars[a] = chars[i];
        continue;//move on
      }
      minimalCostofDeletions += min(C[i], C[i - 1]);
    }
    System.out.print(" After removing duplicates: ");
    System.out.print(Arrays.copyOfRange(chars, 0, a + 1));
    System.out.println(" -- and Total cost: "+minimalCostofDeletions);
    System.out.print("");
    return minimalCostofDeletions;
  }

  private static boolean isUnique(char aChar, char aChar1) {
    return aChar != aChar1;
  }

  private static boolean tooShort(int length) {
    return length < 2;
  }




/*  static public int solution1(String S, int[] C) {
    int result = 0;
    int pointer = 0;
    char finalLetter = S.charAt(0);
    for (int i = 1; i < S.length(); i++) {
      boolean isFinalLetter = i == (S.length() - 1);
      char currentLetter = S.charAt(i);
      if ((currentLetter != finalLetter) || isFinalLetter) {
        int runLen;
        if (currentLetter != finalLetter) {
          runLen = i - pointer;
        } else {
          runLen = (i - pointer) + 1;
        }
        if (runLen > 1) {
          result += calculateCost(C, pointer, runLen);
        }
        finalLetter = currentLetter;
        pointer = i;
      } else {
        //do nothing
       }
    }
    return result;
  }

  static private int calculateCost( int[] C,  int start,  int length) {

    final int[] subset = Arrays.copyOfRange(C, start, start+length); Arrays.sort(subset);
    int result=0;
    for (int i = 0; i < (subset.length - 1); i++) {
      result += subset[i];
    }
    return result;
  }
  */
}
