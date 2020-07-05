package com.n2.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Caterpillar method https://codility.com/media/train/13-CaterpillarMethod.pdf
//https://www.geeksforgeeks.org/program-find-simple-moving-average/
public class MovingAverage {

  //This is efficient than the other solution
  public int[] solution(int[] a, int period) {
    List<Integer> result = new ArrayList<>();
    int len = a.length;
    int startIndex = 0;
    int endIndex = startIndex + period - 1;
    int sum = 0;
    for (int i = 0; i <= endIndex; i++) {
      sum += a[i];
    }
    int average = sum / period;
    result.add(average);
    while (endIndex < len) {
      sum -= a[startIndex];
      startIndex++;
      endIndex++;
      if (endIndex >= len) {
        break;
      }
      sum += a[endIndex];
      average = sum / period;
      result.add(average);
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  public int[] solution1(int[] a, int period) {
    List<Integer> result = new ArrayList<>();
    int len = a.length;
    int startIndex = 0;
    int endIndex = startIndex + period - 1;
    while (endIndex < len) {
      int sum = 0;
      for (int i = startIndex; i <= endIndex; i++) {
        sum += a[i];
        System.out.printf("\nAdding %d and the result sum is %d \n", a[i], sum);
      }
      int average = sum / period;
      result.add(average);
      startIndex++;
      endIndex++;
      System.out.println("endIndex is " + endIndex);
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String[] args) {
    System.out.println("Moving average is " + Arrays
        .toString(new MovingAverage().solution1(new int[]{1, 3, 5, 6, 8}, 3)));
    System.out.println("Moving average is " + Arrays
        .toString(new MovingAverage().solution(new int[]{1, 3, 5, 6, 8}, 3)));
  }
}
