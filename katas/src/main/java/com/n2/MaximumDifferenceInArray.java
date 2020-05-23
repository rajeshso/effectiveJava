package com.n2;

import java.util.Arrays;

public class MaximumDifferenceInArray {
  public static int solution(int[] arr) {
    //Sort
    Arrays.sort(arr);
    //Remove Dups?
    //Subtract the Last and First elements
    return arr[arr.length-1] - arr[0];
  }
}
