package com.n2.codility;

public class DivCount {
  public static int solution1(int a, int b, int k) {
    int result = 0;
    for (int i = a; i <=b ; i++) {
      if (i%k == 0) result+=1;
    }
    return result;
  }

  public static int solution2(int a, int b, int k) {
    int result = 0;
    int indexOfKMultiple = 0;
    for (int i = a; i <=b ; i++) {
      indexOfKMultiple++;
      if (i%k == 0) {
        break;
      }
    }
    indexOfKMultiple++;
    result = ((b-a+1)/indexOfKMultiple);
    return result;
  }
}
