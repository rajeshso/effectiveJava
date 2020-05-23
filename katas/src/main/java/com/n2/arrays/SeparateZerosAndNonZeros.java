package com.n2.arrays;

public class SeparateZerosAndNonZeros {
  public static int[] solution(int[] s) {
    int len = s.length;
    int[] result = new int[len];
    int count=0;
    for (int i = 0; i < len; i++) {
      if(s[i]!=0) {
        result[count++]=s[i];
      }
    }
    return result;
  }
  public static int[] solution1(int[] s) {
    int len = s.length;
    int count=0;
    for (int i = 0; i < len; i++) {
      if(s[i]!=0) {
        s[count++]=s[i];
      }
    }
    //if count < len
    while(count<len) {
      s[count] = 0;
      count++;
    }

    return s;
  }
}
