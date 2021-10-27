package com.n2.codilitypractice;

import java.util.Arrays;

//http://www.moatazelmasry.com/projects/codility-problems/equilibrium-index/#:~:text=Description%20on%20Codility&text=An%20equilibrium%20index%20of%20this,A%5BN%E2%88%921%5D.
public class EquilibriumProblem {

  static public int solution(int[] a) {
    int resultIndex = -1;
    if (a.length<3) {
      return resultIndex;
    }
    int minAbsDifference = Integer.MIN_VALUE;
    int aLen = a.length;
    int aIndex = 1;
    while (aIndex<aLen) {
      int[] leftArray = Arrays.copyOfRange(a,0,aIndex);
      int[] rightArray = Arrays.copyOfRange(a,aIndex,aLen);
      int leftSum = sum(leftArray);
      int rightSum = sum(rightArray);
      int absDiff = Math.abs(leftSum-rightSum);
      if (absDiff>minAbsDifference) {
        minAbsDifference = absDiff;
        resultIndex = aIndex;
      }
      aIndex++;
    }
    return resultIndex;
  }
  static public int sum(int[] a) {
    return Arrays.stream(a).sum();
  }

  static public int solutionBest(int[] a) {
    int leftSum = a[0];
    int rightSum = sum(a);
    rightSum -=leftSum;
    int diff = Math.abs(leftSum-rightSum);
    for (int i = 1; i < a.length; i++) {
      leftSum+=a[i];
      rightSum-=a[i];
      int currentDiff = Math.abs(leftSum-rightSum);
      if (diff > currentDiff) diff = currentDiff;
    }
    return diff;
  }

    public static void main(String[] args) {
      System.out.println(solution(new int[]{3,1,2,4,3}));
      System.out.println(solutionBest(new int[]{3,1,2,4,3}));
    }
}
