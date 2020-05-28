package com.n2.codility;

import java.util.Arrays;

//https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
//
public class PassingCars {
  //Wrong
  public static int solution1(int[] a) {
    int len = a.length;
    int count =0;
    for (int i = 0; i < len; i++) {
      if (a[i] ==0) {//right
        if (i+1 == len) {
          break;
        }
        if (a[i+1]==1) {
          count++;
          a[i+1] = 0;
          a[i] =1;
        }
      }else { //left
        if (i+1 == len) {
          break;
        }
        if (a[i+1]==0) {
          count++;
          a[i+1] = 1;
          a[i] =0;
        }
      }
    }
    return count;
  }
  //Correct, but not efficient
  public static int solution2(int[] a) {
    int len = a.length;
    int count =0;
    int toPassBy = 0;
    for (int i = 0; i < len; i++) {
      if (a[i] ==0) {//right
        toPassBy+=1;
      }else { //left
        count+=toPassBy;
      }
    }
    return count;
  }
  //Correct but not efficient
  public static int solution3(int[] a) {
    int len = a.length;
    int count =0;
    int toPassBy = 0;
    for (int i = 0; i < len; i++) {
      if (a[i] ==0) {//right
        toPassBy+=1;
      }else { //left
        count+=toPassBy;
      }
    }
    return count;
  }

  //Recommended
  public static int solution4(int[] a) {
    //Improvement tip: Both the for loops can be merged to make this O(n)
    int[] suffixSum = suffixSum(a);
    int intersections = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] == 0) {
        intersections+=suffixSum[i];
      }
      if (intersections > 100000) return -1;
    }
    return intersections;
  }
  public static int[] suffixSum(int[] a) {
    int[] suffixSum = new int[a.length+1];
    int sum;
    for (int i = a.length-1; i >=0 ; i--) {
      sum = suffixSum[i+1] + a[i];
      suffixSum[i] = sum;
    }
    return suffixSum;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(suffixSum(new int[]{0,1,0,1,1})));
    System.out.println(solution4(new int[]{0,1,0,1,1}));
   // System.out.println(solution2(new int[]{0,1,0,1,1}));
  }
}
