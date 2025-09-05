package com.n2.arrays;

import java.util.Arrays;

/**
 Maximum Difference in Array
 Problem Statement:
 Given an array of integers, find the maximum possible difference between any two distinct elements
 in the array.

 Requirements:

 Input: An integer array that may contain duplicates

 Output: The maximum difference between the largest and smallest unique values

 Handle duplicate values by considering only distinct elements

 Return the difference as: max_unique_value - min_unique_value

 Example:
 Input: [1, 3, 2, 3, 5, 1]
 Process: Remove duplicates → [1, 2, 3, 5]
 Output: 5 - 1 = 4

 Edge Cases:

 Array with all identical elements should return 0

 Single element array should return 0

 Empty array handling (implementation dependent)

 The solution efficiently removes duplicates, sorts the unique elements, and returns the difference
 between the maximum and minimum values.
 */
public class MaximumDifferenceInArray {
  public static int solution(int[] arr) {
    //Sort
    //Remove Dups
    int[] cleanArr = Arrays.stream(arr).distinct().sorted().toArray();
    //Subtract the Last and First elements
    return cleanArr[cleanArr.length-1] - cleanArr[0];
  }
}
