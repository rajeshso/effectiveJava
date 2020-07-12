package com.n2.codility;

import java.util.Arrays;
import java.util.stream.IntStream;

////effectiveJava/katas/doc/globe-Relay.jpg
/**
 * Count the number of ways you can cut out one tree,
 * so that the heights of the remaining ones will increase and decrease alternately
 */
public class CountAlternateSlices {
  public int solution(int[] A) {
    int len = A.length;
    int cutCount = 0;
    if (isAlternateElementsIncreaseOrDecrease(A)) {
      return 0;
    }
     for (int i = 0; i < len; i++) {
      //create a sub array without the i'th element
      int[] left = Arrays.copyOfRange(A,0,i );
      int[] right = Arrays.copyOfRange(A, i+1,len);
      //System.out.println(Arrays.toString(concat(left, right)));
      int[] subArray = concat(left, right);
      //check distinct
      if (isAlternateElementsIncreaseOrDecrease(subArray)) {
        //update distinct
        cutCount++;
      }
    }
    if (cutCount==0) {
      cutCount = -1;
    }
    return cutCount;
  }
  boolean isAlternateElementsIncreaseOrDecrease(int[] a) {
    int len = a.length;
    int toggle;
    if (len<2) {
      return false;
    }
    if (a[0] > a[1]) {
      toggle = -1;
    }else if (a[0] < a[1]) {
      toggle = 1;
    }else {
      return false;
    }
    for (int i = 1; i < len-1; i++) {
      int expected;
      int actual;
      if (toggle<0) {
        expected = 1;
      }else {
        expected = -1;
      }
      if (a[i]<a[i+1]) {
        actual = 1;
      }else if (a[i]>a[i+1]){
        actual = -1;
      }else {
        return false;
      }
      if (expected == actual) {
        toggle = toggle>0?-1:1;
      }else {
        return false;
      }
    }
    return true;
  }
  static int[] concat(int[] left, int[] right) {
    IntStream joinedstream = IntStream.concat(Arrays.stream(left), Arrays.stream(right));
    return joinedstream.toArray();
  }
  public static void main(String[] args) {
    CountAlternateSlices c = new CountAlternateSlices();
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{1,1,1}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{2,1,1}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{2,1,2}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{2,1,2,3}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{2,1,2,3,3}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{0,1,2,3,3}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{0,1}));
    System.out.println(c.isAlternateElementsIncreaseOrDecrease(new int[]{0}));

    System.out.println(Arrays.toString(concat(new int[]{1}, new int[]{2,3})));

    System.out.println("-----");
    c.solution(new int[]{0,1,2,3,4});
    System.out.println("-----");
    System.out.println(c.solution(new int[]{3,4,5,3,7}));
    System.out.println(c.solution(new int[]{1,2,3,4}));
    System.out.println(c.solution(new int[]{1,3,1,2}));
    System.out.println(c.solution(new int[]{3,4,5,5,7}));
    System.out.println(c.solution(new int[]{1,2,3,4}));

  }
}
