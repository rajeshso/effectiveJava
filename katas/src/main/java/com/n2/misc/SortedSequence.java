package com.n2.misc;

import java.util.Arrays;

/**
 * Given 1…..10 , output should be 1,10,  2, 9,  3,8,  4,7 , 5,6
 * Given output may be sorted or unsorted
 */
public class SortedSequence {
  static public int[] solution(int[] arr) {
    int[] result = new int[arr.length];
    int index = 0;
    int len = arr.length;
    int pairs = (int) Math.ceil((double)len/2);
    int span = len-1;
    Arrays.sort(arr);
    for (int i = 0; i < pairs; i++) {
      result[index++] = arr[i];
      if (i!=span) {
        result[index++] = arr[span];
      }
      span--;
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println( Arrays.toString(solution(new int[]{1,2,3,4,5,6,7,8,9,10})));
    System.out.println( Arrays.toString(solution(new int[]{1,2,3,4,5})));
    System.out.println( Arrays.toString(solution(new int[]{9,2,1,4,3,6,5,7,8,0})));
  }
}
//0 , 9
//1 , 8
//2 , 7
//3 , 6
//4 , 5