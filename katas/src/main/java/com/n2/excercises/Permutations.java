package com.n2.excercises;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

  public static void main(String[] args) {
    Permutations permutations = new Permutations();
/*    List<String> combinationList = permutations.getCombinations("ABC");
    System.out.println(combinationList);*/
    final List<String> result = new ArrayList<>();
    final String str = "TONY";
    permute(str, 0, str.length() - 1);
  }


  private static void permute(String str, int startIndex, int endIndex) {
    if (startIndex == endIndex) {
      System.out.println(str);
    } else {
      for (int i = startIndex; i <= endIndex; i++) {
        System.out
            .println("Before first swap str= " + str + " startIndex = " + startIndex + " i= " + i);
        str = swap(str, startIndex, i);
        System.out.println(
            "After first swap and Before permute str= " + str + " startIndex = " + startIndex
                + " endIndex= " + endIndex + " i= " + i);
        permute(str, startIndex + 1, endIndex);
        System.out.println(
            "After permute str= " + str + " startIndex = " + startIndex + " endIndex= " + endIndex
                + " i= " + i);
        str = swap(str, startIndex, i);
        System.out.println(
            "After second swap str= " + str + " startIndex = " + startIndex + " endIndex= "
                + endIndex + " i= " + i);

      }
    }
  }

  private static String swap(String str, int i, int j) {
    final char[] chars = str.toCharArray();
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    return String.valueOf(chars);
  }
}
