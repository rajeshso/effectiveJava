package com.n2.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//You can use the same formula for notes or coins too

/**
 * Given 17 unit square to paint, Given 1 Unit Volume of paint for 1 Unit Square Given Paint cans of
 * any number of 6 Units cans, 3 Units cans and 1 Unit cans
 * <p>
 * To Find, What is the minimum number of cans to buy ?
 */
public class GreedyAlgorithm1 {

  public int[] solution(int paintArea, int[] paintCans) {
    Arrays.sort(paintCans);
    List<Integer> paintCansResult = new ArrayList<>();
    while (paintArea > 0) {
      int can = pickTheBestCan(paintCans, paintArea);
      paintCansResult.add(can);
      paintArea -= can;
    }
    return paintCansResult.stream().mapToInt(i -> i).toArray();
  }

  private int pickTheBestCan(int[] paintCans, int paintArea) {
    int min = Integer.MAX_VALUE;
    int result = 0;
    for (int i = 0; i < paintCans.length; i++) {
      int diff = paintArea - paintCans[i];
      if (diff < 0) {
        continue;
      }
      if (diff <= min) {
        min = diff;
        result = paintCans[i];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    GreedyAlgorithm1 greedyAlgorithm1 = new GreedyAlgorithm1();
    int[] result1 = greedyAlgorithm1.solution(17, new int[]{6, 3, 1});
    System.out.println(Arrays.toString(result1));//The answer should be 6,6,3,1,1
    int[] result2 = greedyAlgorithm1.solution(67, new int[]{1, 2, 5, 10, 20, 50, 100});
    System.out.println(Arrays.toString(result2));//The answer should be 50,10,5,2
  }

}
