package com.n2.codilitypractice;

import java.util.stream.IntStream;

public class Mataji {

  static public int solution(String s) {
    try {
      Integer.parseInt(s, 2);
    }catch (NumberFormatException nfe) {
      return -1;
    }
    int v = Integer.parseInt(s, 2);
    int counter = 0;
    while (v!=0) {
      if (v%2 ==0)  { //even
        v = v/2;
      }else { //odd
        v-=1;
      }
      counter++;
    }
    return counter;
  }

  static public int solution1(String s) {
    int counter = 0;
    while (!isZero(s)) {
      if (isEven(s))  { //even
        s = divideBy2(s);
      }else { //odd
        s = subtractBy1(s);
      }
      counter++;
    }
    return counter;
  }

  public static String divideBy2(String str) {
    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '0') {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }

  private static boolean isZero(String word) {
    if (isAllZeros(word)) return true;
    return IntStream.range(0, word.toCharArray().length).dropWhile(a->a!='0').count() == word.length();
  }

  static boolean isAllZeros(String s)
  {
    int n = s.length();
    for (int i = 1; i < n; i++)
      if (s.charAt(i) != s.charAt(0) )
        return false;
    return true;
  }

  public static boolean isEven(String s) {
    return  (s.endsWith("0"));
  }


  public static String subtractBy1(String str) {
    String result = null;
    if ((str != null) && (str.length() > 0)) {
      result = str.substring(0, str.length() - 1);
    }
    return result+"0";
  }

  public static void main(String[] args) {

    String s = "011100";
    final int decimalForm = Integer.parseInt(s, 2);
    //System.out.println(decimalForm);
    System.out.println(solution(s));
    System.out.println(solution("111"));
    System.out.println(solution("1111010101111"));

    System.out.println(solution1(s));
    System.out.println(solution1("111"));
    System.out.println(solution1("1111010101111"));

  }

}
