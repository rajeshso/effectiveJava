package com.n2.excercises;

//https://www.youtube.com/watch?v=P3YID7liBug&t=14s
public class BinarySearch {

  public static void main(String[] args) {
    int[] array = {1, 3, 4, 5, 7, 8, 9, 10, 11, 12};
    System.out.println(find(array, 1));
    System.out.println(find(array, 2));
    System.out.println(find(array, 3));
    System.out.println(find(array, 4));
    System.out.println(find(array, 5));
    System.out.println(find(array, 6));
    System.out.println(find(array, 7));
    System.out.println(find(array, 8));
    System.out.println(find(array, 9));
    System.out.println(find(array, 10));
    System.out.println(find(array, 11));
    System.out.println(find(array, 12));
    System.out.println(find(array, 13));

    System.out.println(isPresent(array, 1));
    System.out.println(isPresent(array, 2));
    System.out.println(isPresent(array, 3));
    System.out.println(isPresent(array, 4));
    System.out.println(isPresent(array, 5));
    System.out.println(isPresent(array, 6));
    System.out.println(isPresent(array, 7));
    System.out.println(isPresent(array, 8));
    System.out.println(isPresent(array, 9));
    System.out.println(isPresent(array, 10));
    System.out.println(isPresent(array, 11));
    System.out.println(isPresent(array, 12));
    System.out.println(isPresent(array, 13));
  }

  private static int find(int[] array, int i) {
    int leftIndex = 0;
    int rightIndex = array.length - 1;
    while (leftIndex <= rightIndex) {
      int middleIndex = (leftIndex + rightIndex) / 2;
      if (array[middleIndex] == i) {
        return middleIndex;
      } else if (array[middleIndex] > i) {
        rightIndex = middleIndex - 1;
      } else if (array[middleIndex] < i) {
        leftIndex = middleIndex + 1;
      }
    }
    return -1;
  }

  private static boolean isPresent(int[] array, int i) {
    int leftIndex = 0;
    int rightIndex = array.length - 1;
    while (leftIndex <= rightIndex) {
      int middleIndex = (leftIndex + rightIndex) / 2;
      if (array[middleIndex] == i) {
        return true;
      } else if (array[middleIndex] > i) {
        rightIndex = middleIndex - 1;
      } else if (array[middleIndex] < i) {
        leftIndex = middleIndex + 1;
      }
    }
    return false;
  }
}
