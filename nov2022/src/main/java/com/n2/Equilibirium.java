package com.n2;

import java.util.Arrays;

//https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
//return abs diff, where abs diff between left and right (split at P) is at a minimum
public class Equilibirium {
  int solution(int[] a) {
    int leftStart = a[0];
    int rightStart = Arrays.stream(a).sum()-leftStart;
    int pivot = Integer.MAX_VALUE;
    for (int i = 1; i < a.length; i++) {
      int possiblePivot = Math.abs(leftStart-rightStart);
      pivot = Math.min(pivot, possiblePivot);
      System.out.println("i = "+i + " a["+i+"]="+a[i]+" leftStart = "+ leftStart +" rightStart="+rightStart + " possiblePivot = "+possiblePivot);
      leftStart = leftStart + a[i];
      rightStart = rightStart - a[i];
    }
    return pivot;
  }

  public static void main(String[] args) {
    int[] a = {3,1,2,4,3};
    Equilibirium e = new Equilibirium();
    int result = e.solution(a);
    System.out.println("result = "+ result);
  }
}
