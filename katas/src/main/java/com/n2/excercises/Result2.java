package com.n2.excercises;

import java.util.List;

class Result2 {

  /*
   * Complete the 'minimumGroups' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY predators as parameter.
   */

  public static int minimumGroups(List<Integer> predators) {
    // Write your code here
    int[] predatorsArray = predators.stream().mapToInt(Integer::intValue).toArray();
    final int supreme = -1;
    int minimumGroups = 1;
    int numberOfPredators = predatorsArray.length;
    for (int i = 0; i < predatorsArray.length; i++) {
      int predatorIndex = i;
      int counter = 1;
      while (true) {

        if (!(predatorsArray[predatorIndex] > supreme
            && counter < numberOfPredators
            && predatorsArray[predatorIndex] < numberOfPredators)) {
          break;
        }
        predatorIndex = predatorsArray[predatorIndex];
        counter++;
      }
      if (counter > minimumGroups) {
        minimumGroups = counter;
      }
    }
    return minimumGroups;
  }

  public static void main(String[] args) {
    System.out.println(minimumGroups(List.of(-1, 8, 6, 0, 7, 3, 8, 9, -1, 6, 1)));
    System.out.println(minimumGroups(List.of(-1, 0, 1)));
    System.out.println(minimumGroups(List.of(1, -1, 3, -1)));
  }
}
