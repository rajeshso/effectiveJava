package com.n2.excercises;

import java.util.Arrays;

public class AnagramChecker {

  static boolean isAnagram(String str1, String str2) {
    str1 = str1.replaceAll("\\s", "");
    str2 = str2.replaceAll("\\s", "");
    int len1 = str1.length();
    int len2 = str2.length();
    if (len1 != len2) {
      return false;
    }
    final char[] str1CharArray = str1.toLowerCase().toCharArray();
    final char[] str2CharArray = str2.toLowerCase().toCharArray();
    Arrays.sort(str1CharArray);
    Arrays.sort(str2CharArray);
    return Arrays.equals(str1CharArray, str2CharArray);
  }

  public static void main(String[] args) {
    System.out.println(isAnagram("Mother In Law", "Hitler Woman"));
    System.out.println(isAnagram("Keep", "Peek"));
    System.out.println(isAnagram("rttre", "Peek"));
  }
}
