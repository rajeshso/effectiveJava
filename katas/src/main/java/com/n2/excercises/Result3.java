package com.n2.excercises;

import com.sun.nio.sctp.AbstractNotificationHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Result3 {
  public static int solution(String S, int[] C) {
    // write your code in Java SE 8
    int result = 0;
    Map<String, Integer> map = getFrequency(S);
    System.out.println("map = "+map);
    final Iterator<String> keyIterator = map.keySet().iterator();
    while (keyIterator.hasNext()) {
      String key =  keyIterator.next();
      Integer count = map.get(key);
      if (count==1) {
        continue;
      }
      int toDelete = count ;
      List<Integer> indices = new ArrayList<>();
      for (int index = S.indexOf(key);
          index >= 0;
          index = S.indexOf(key, index + 1))
      {
        //System.out.println("key = " + key+ " index = " + index);
        indices.add(index);
      }
      List<Integer> costs = new ArrayList<>();
      for (int i = 0; i < toDelete; i++) {
        costs.add(C[indices.get(i)]);
      }
      Collections.sort(costs);
      System.out.println("key = "+ key + " costs = "+ costs);
      for (int i = 0; i < costs.size()-1; i++) {
        result+=costs.get(i);
      }
    }

    return result;
  }

  private static Map<String, Integer> getFrequency(String S) {
    Map<String, Integer> map = new HashMap<>();
    int beginIndex = 0;
    int len = S.length();
    for (int i = 0; i < len ; i++) {
      String a = S.substring(beginIndex,i+1);
      if (map.containsKey(a) ) {
        map.put(a, map.get(a)+1);
      }else {
        map.put(a, 1);
      }
      beginIndex++;
    }
    return map;
  }


  static String removeUtil(String str, char last_removed)
  {
    // If length of string is 1 or 0
    if (str.length() == 0 || str.length() == 1)
      return str;

    // Remove leftmost same characters and recur for remaining
    // string
    if (str.charAt(0) == str.charAt(1))
    {
      last_removed = str.charAt(0);
      while (str.length() > 1 && str.charAt(0) == str.charAt(1))
        str = str.substring(1, str.length());
      str = str.substring(1, str.length());
      return removeUtil(str, last_removed);
    }

    // At this point, the first character is definiotely different
    // from its adjacent. Ignore first character and recursively
    // remove characters from remaining string
    String rem_str = removeUtil(str.substring(1,str.length()), last_removed);

    // Check if the first character of the rem_string matches with
    // the first character of the original string
    if (rem_str.length() != 0 && rem_str.charAt(0) == str.charAt(0))
    {
      last_removed = str.charAt(0);
      return rem_str.substring(1,rem_str.length()); // Remove first character
    }


    // If remaining string becomes empty and last removed character
    // is same as first character of original string. This is needed
    // for a string like "acbbcddc"
    if (rem_str.length() == 0 && last_removed == str.charAt(0))
      return rem_str;

    // If the two first characters of str and rem_str don't match,
    // append first character of str before the first character of
    // rem_str
    return (str.charAt(0) + rem_str);
  }
  public static void main(String[] args) {
    System.out.println(solution("abccbd", new int[]{0,1,2,3,4,5}));
   // System.out.println(solution("aabbcc", new int[]{1,2,1,2,1,2}));
   // System.out.println(solution("aaaa", new int[]{3,4,5,6}));

  }
}
