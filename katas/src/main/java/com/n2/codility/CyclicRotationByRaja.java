package com.n2.codility;

import java.util.Arrays;
//https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
public class CyclicRotationByRaja {

  public int[] solution(int[] A, int K) {

    K = K % A.length;

    int currIndex = 0, tempSwap=A[currIndex];

    for(int i=0;i<A.length; i++){
      currIndex= (currIndex+K)%A.length;

      //Swap two numbers without third variable
      tempSwap = A[currIndex] + tempSwap ;
      A[currIndex] = tempSwap - A[currIndex] ;
      tempSwap = tempSwap - A[currIndex] ;

    }

    return A;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new CyclicRotation().solution(new int[]{7, 2, 8, 3, 5}, 2)));

    System.out.println(Arrays.toString(new CyclicRotation().solution(new int[]{7, 2, 8, 3, 5}, 5)));
  }

}
