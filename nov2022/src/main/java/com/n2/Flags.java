package com.n2;
//https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/

public class Flags {
  public int solution(int[] a, int gapConstant) {
    int current = 0;
    int before = a[0];
    int after = 0;
    final int len = a.length;
    int flagsCount = 0;
    for (int i = 1; i < len-1; ) {
      current = a[i];
      after = a[i+1];
      final boolean peak = isPeak(before, current, after);
      if (peak) {
        flagsCount++;
        i+=gapConstant;
      }else {
        i++;
      }
    }
    return flagsCount;
  }
  private boolean isPeak(int before, int current, int after) {
    return  ((current>=before) && (current>=after));
  }

  public static void main(String[] args) {
    Flags flags = new Flags();
    int[] range = {1,5,3,4,3,4,1,2,3,4,6,2};
    System.out.println(flags.solution(range, 3));
  }
}
