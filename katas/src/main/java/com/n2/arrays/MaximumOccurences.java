package com.n2.arrays;

public class MaximumOccurences {
  public static int solution(int[] s) {
    int element = 0, maxOccurence=0;
    int len = s.length;
    for(int i=0;i<len;i++) {
      int count = 0;
      for (int j = i; j < len ; j++) {
        if (s[i]==s[j]) {
          count+=1;
        }
      }
      if (count>maxOccurence) {
        element=s[i];
        maxOccurence = count;
      }
    }
    System.out.println(element +" occurs "+ maxOccurence + " times");
    return maxOccurence;
  }
}
