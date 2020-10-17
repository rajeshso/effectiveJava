package com.n2.misc;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * There are N blocks, numbered from 0 to N-1, arranged in a row.
 * A couple of frogs were sitting together on one block when they
 * had a terrible quarrel. Now they want to jump away from one another
 * so that the distance between them will be as large as possible.
 *
 * The distance between blocks numbered J and K, where J <= K, is
 * computed as k-J+1. The frogs can only jump up, meaning that they
 * can move from one block to another only if the two blocks are adjacent
 * and the second block is of the same or greater height as the first.
 *
 * What is the longest distance that they can possibly create between each other,
 * if they also chose to sit on the optimal starting block initially ?
 */
public class LongestDistanceBetweenFrogs {

  public static void main(String[] args) {

    System.out.println(solution(new int[]{2,6,8,5}));
    assert (solution(new int[]{2,6,8,5}) == 3);

    System.out.println(solution(new int[]{1,5,5,2,6}));
    assert (solution(new int[]{1,5,5,2,6}) == 4);

    System.out.println(solution(new int[]{1,1  }));
    assert (solution(new int[]{1,1}) == 2);

    System.out.println(solution(new int[]{1,1  }));
    assert (solution(new int[]{1,1}) == 2);

    System.out.println(solution(new int[]{5, 7, 8, 8, 8, 5, 7, 10}));
    assert (solution(new int[]{5, 7, 8, 8, 8, 5, 7, 10}) == 6);
  }

  static private int findDistance(int[] blocks, int possibleReferencePosition) {
    int frogLeftJumps = getFrogLeftJumps(blocks, possibleReferencePosition);
    int frogRightJumps = getFrogRightJumps(blocks, possibleReferencePosition);
    return frogLeftJumps + frogRightJumps + 1;
  }

  private static int getFrogRightJumps(int[] blocks, int referencePosition) {
    int frogRightJumps =0;
    int length = blocks.length;
    while (true) {
      if (isRightTipOffPillar(referencePosition, length))
        break;
      if (blocks[referencePosition+1] >= blocks[referencePosition]) {
        frogRightJumps++;
        referencePosition++;
      } else {
        break; //end of the pillars
      }
    }
    return frogRightJumps;
  }

  private static boolean isRightTipOffPillar(int referencePosition, int blockLen) {
    return !(referencePosition < (blockLen - 1));
  }

  private static int getFrogLeftJumps(int[] blocks, int referencePoint) {
    int frogLeftJumps =0;
    while (referencePoint > 0) {
      if (blocks[referencePoint -1] >= blocks[referencePoint]) {
        frogLeftJumps++;
        referencePoint--;
      } else {
        break;
      }
    }
    return frogLeftJumps;
  }

  static public int solution(int[] blocks) {
    int maxDistanceBetweenFrogs = 0;
    int currentblockOfFrog =0;
    final int blockLen = blocks.length;
    for (int i = 0; i < blockLen; i++) {
      currentblockOfFrog = findDistance(blocks, i);
      maxDistanceBetweenFrogs = max(maxDistanceBetweenFrogs, currentblockOfFrog);
    }
    return maxDistanceBetweenFrogs;
  }

  public static boolean canMoveRight(int currentPosition, int rightPosition, int currentHeight, int heightAtRight) {
    return true;
  }
}
