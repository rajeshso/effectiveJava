package com.n2;
//https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/

import java.util.ArrayList;
import java.util.List;

public class Flags {
  public int solution(int[] a, int gapConstant) {
    int current;
    int before;
    int after;
    final int len = a.length;
    int flagsCount = 0;
    for (int i = 1; i < len-1; ) {
      before= a[i-1];
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
  public int solutionMuchNicer(int[] a) {
    int n = a.length;
    List<Integer> peaks = new ArrayList<>();

    // Find peaks
    for (int i = 1; i < n - 1; i++) {
      if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
        peaks.add(i);
      }
    }

    int flags = 0;
    int lastFlag = -1;
    int distance = 0;

    // Try to place flags
    for (int i = 1; i * i <= n; i++) {
      int currentFlags = 0;
      int currentPos = 0;

      for (int peak : peaks) {
        if (currentPos <= peak && peak <= lastFlag + i) {
          currentPos = peak + i;
          currentFlags++;
        }
      }

      if (currentFlags > flags) {
        flags = currentFlags;
        distance = i;
      }
    }

    return flags;
  }

  public static void main(String[] args) {
    Flags flags = new Flags();
    int[] range = {1,5,3,4,3,4,1,2,3,4,6,2};
    System.out.println(flags.solution(range, 3));
  }
}
