package com.n2.codilitypractice;

import java.util.Arrays;
//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class CyclicRotationProblem {
  static int[] solution(int[] a, int k) {
    int aLen = a.length;
    int[] result = new int[aLen];
    for (int i = 0; i < aLen; i++) {
      int oldIndex = i;
      int newIndex = (oldIndex+k)%aLen;
      result[newIndex] = a[oldIndex];
    }
    return result;
  }
/*  static int[] solutionBest(int[] a, int k) {
    int aLen = a.length;
    k = k%aLen;
    int currentIndex = 0;
    int tempSwapValue = a[currentIndex];
    for (int i = 0; i < aLen; i++) {
      currentIndex = (currentIndex+i)%k;
      int fromIndex = i;
      int fromValue = a[fromIndex];
      int toIndex = (fromIndex+k)%aLen;
      int toValue = a[toIndex];
      a[toIndex] = fromValue;
      a[fromIndex] = toValue;
    }
    return a;
  }*/


  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new int[]{5,3,4,1,2},2)));
    System.out.println(Arrays.toString(solution(new int[]{7,2,8,3,5},2)));
    System.out.println(Arrays.toString(solution(new int[]{7,2,8,3,5},5)));



  }
}
