package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.
 *
 * You are given an array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.
 *
 * The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.
 *
 * For example, you are given integer X = 5 and array A such that:
 *
 *   A[0] = 1
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 4
 *   A[4] = 2
 *   A[5] = 3
 *   A[6] = 5
 *   A[7] = 4
 * In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.
 *
 * Write a function:
 *
 * class Solution { public int solutionMy(int X, int[] A); }
 *
 * that, given a non-empty array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.
 *
 * If the frog is never able to jump to the other side of the river, the function should return −1.
 *
 * For example, given X = 5 and array A such that:
 *
 *   A[0] = 1
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 4
 *   A[4] = 2
 *   A[5] = 3
 *   A[6] = 5
 *   A[7] = 4
 * the function should return 6, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N and X are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..X].
 */
public class TestFrogRiverOne {
  //several O(n) loops
  public int solutionMy(int X, int[] A) {
    //X is the number of steps/positions needed to cross
    //Index of A is the time in seconds
    //Value of A is step/position. the event of leaf falling in A[time] position
    //leafedIndex is a boolean array with size X and initialized as false
    Boolean[] leafedIndex = new Boolean[X];
    Arrays.fill(leafedIndex, false);
    int currentIndex = 0;
    //Iterate the array A while either of the conditions - either length of A OR until all of leafedIndex is !true
    while (isLeafedIndexAnyFalse(leafedIndex)) {
      //For every leaf falling event, update true to the appropriate leafedIndex
      if (currentIndex>=A.length) {
        break;
      }
      if (A[currentIndex] <= X)
        leafedIndex[A[currentIndex] - 1] = true;
      else {
        System.out.println("Skipping invalid value "+A[currentIndex]);
      }
      currentIndex+=1;
    }
    //The exit index of A out of the loop is the result or earliest time
    //In case all of leafedIndex is !true, return -1 else return the result calculated in the above index
    return isLeafedIndexAnyFalse(leafedIndex)? -1 : (currentIndex-1);
  }
  Boolean isLeafedIndexAnyFalse(final Boolean[] leafedIndex) {
    return Arrays.stream(leafedIndex).anyMatch(l-> !l);
  }
  //O(n) time and O(1) space
  public int solutionOptimal(int X, int[] A) {
    //X is the number of steps/positions needed to cross
    //Index of A is the time in seconds
    //Value of A is step/position. the event of leaf falling in A[time] position
    //leafedIndex is a boolean array with size X and initialized as false
    boolean[] leafedIndex = new boolean[X];//A boolean[] array is used to track which positions have leaves.
/*    for (int i = 0; i < X; i++) {
      leafedIndex[i] = false;
    }*/
    int coveredPositions = 0;//coveredPositions keeps track of how many unique positions have been covered by leaves
    for (int i = 0; i < A.length; i++) {
      int position = A[i]-1;//For each leaf that falls, convert the position (adjusting for 0-based index).
      if (position<X && !leafedIndex[position]) {//Check if this position is within the range [0, X-1] and if it hasn't been covered yet.
        leafedIndex[position] = true; //If it's a valid position and hasn't been covered, mark it as covered
        coveredPositions++;//and increment coveredPositions.
        if (coveredPositions==X) { //If coveredPositions equals X, return the current time index.
          return i;
        }
      }
    }
    return -1;//If the loop completes without covering all positions, return -1.
  }


  @ParameterizedTest
  @MethodSource("source")
  public void testSimple(int X, int[] A, int expected) {
    assertThat(solutionMy(X, A)).isEqualTo(expected);
    assertThat(solutionOptimal(X, A)).isEqualTo(expected);
  }
  private static Stream source() {
    return Stream.of(
        Arguments.of(5, new int[]{1,3,1,4,2,3,5,4}, 6),
        Arguments.of(5, new int[]{1,3,1,4,2,3,4,4}, -1),
        Arguments.of(5, new int[]{1,3,1,4,2,3,5,4,5}, 6)
    );
  }
}
