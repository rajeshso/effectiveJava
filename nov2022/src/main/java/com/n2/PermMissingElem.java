package com.n2;

import java.util.Arrays;

public class PermMissingElem {
  int solution1(int[] a) {
    Arrays.sort(a);
    for (int i = 0; i < a.length-1; i++) {
      if (a[i+1] != a[i]+1) {
        return a[i]+1;
      }
    }
    return 0;
  }
  int solution2(int[] a) { // this method has a lot of limitations but is efficient
    final int max = Arrays.stream(a).max().getAsInt();
    boolean[] position = new boolean[max+1];
    for (int i = 0; i < a.length; i++) {
      position[a[i]] = true;
    }
    for (int i = 1; i < position.length; i++) {
      if (!position[i]) {
        return i;
      }
    }
    return 0;
  }
  int solution3(int[] a) { //O(n) and the best
    //final int max = Arrays.stream(a).max().getAsInt();
    final int max = a.length+1;
    int expectedSum = (max*(max+1))/2;
    int actualSum = Arrays.stream(a).sum();
    return expectedSum - actualSum;
  }
}
