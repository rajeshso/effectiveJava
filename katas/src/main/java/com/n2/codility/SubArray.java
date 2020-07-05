package com.n2.codility;

//Caterpillar method
//Find the number of subArrays sums that have a given value
public class SubArray {

  public static void main(String[] args) {
    SubArray subArray = new SubArray();
    System.out.println(subArray.solution(new int[]{2, 4, 1, 7, 3, 9, 6, 3, 2, 5, 8, 7, 1}, 16));
  }

  public int solution(int[] a, int sumConstant) {
    int result = 0;
    int startIndex = 0;
    int endIndex = 0;
    int subArraySum = a[startIndex];
    int len = a.length;
    while (endIndex < len) { //TODO: Always true. Improvise
      if (subArraySum == sumConstant) {
        System.out
            .printf("\nStarting Index is %d, ending index is %d and the total is %d \n", startIndex,
                endIndex, subArraySum);
        result++;
        subArraySum -= a[startIndex];
        startIndex++;
        endIndex++;
        if (endIndex >= len) {
          break;
        }
        subArraySum += a[endIndex];
      } else if (subArraySum < sumConstant) {
        endIndex++;
        if (endIndex >= len) {
          break;
        }
        subArraySum += a[endIndex];
      } else if (subArraySum > sumConstant) { //TODO: Always true. Improvise
        subArraySum -= a[startIndex];
        startIndex++;
        if (startIndex >= len) {
          break;
        }
      }
    }
    return result;
  }
}
