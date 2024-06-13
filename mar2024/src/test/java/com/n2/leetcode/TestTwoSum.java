package com.n2.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * https://leetcode.com/problems/two-sum/
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution1, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 *
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class TestTwoSum {

  /**
   * Concept of Complement
   * Definition:
   *
   * For each number x in the array, its complement with respect to the target is target - x.
   * If x and its complement are both present in the array, then x + (target - x) = target.
   * Example:
   *
   * Suppose the target is 9 and the array is [2, 7, 11, 15].
   * For x = 2, the complement is 9 - 2 = 7.
   * For x = 7, the complement is 9 - 7 = 2.
   */
  private int[] solution(int[] given, int target) {
    Map<Integer, Integer> valueVsindicesMap = new HashMap<>(given.length);
    int len = given.length;
    for (int i = 0; i < len; i++) {
      int complementaryValue = target - given[i];//As we iterate through the array, for each element x, we calculate its complement target - x.
      if (valueVsindicesMap.containsKey(complementaryValue)) { //If it contains, it means we have found two numbers (x and target - x) that add up to the target, and we return their indices.
        return new int[]{valueVsindicesMap.get(complementaryValue),i};//        // If found, return the indices of the two numbers
      }
      valueVsindicesMap.put(given[i],i);//If it is not, we store the number x and its index in the hash map.
    }
    throw new IllegalArgumentException("No Two Sum");// If no solution1 is found, throw an exception
  }

  //This solution1 fails for one edge case where there are duplicate numbers in the array
  private int[] solution1(int[] given, int target) {
    List<Integer> sortedList = Arrays.stream(given).mapToObj(Integer::valueOf).sorted().collect(
        Collectors.toList());
    int forwardCounter = 0;
    int reverseCounter = given.length-1;
    int[] result = new int[2];
    while (forwardCounter<reverseCounter) {
      int forwardValue = sortedList.get(forwardCounter);
      int reverseValue = sortedList.get(reverseCounter);
      int calculatedTarget = forwardValue+reverseValue;
      if (calculatedTarget > target) {
        reverseCounter--;
      } else if (calculatedTarget < target) {
        forwardCounter++;
      } else {
        result[0]=index(given, forwardValue); //find the index of given where forwardValue is equal to the value
        result[1]=index(given, reverseValue);//find the index of given where reverseValue is equal to the value
        break;
      }
    }
    return result;
  }
  private int index(int[] a, int value) {
    for (int i = 0; i < a.length; i++) {
      if (a[i]==value) return i;
    }
    return -1;
  }
  @ParameterizedTest
  @MethodSource("providedGivenArrayAndExpected")
  public void testSimple(int[] given, int target, int[] expected) {
    assertThat(solution(given, target)).containsExactlyInAnyOrder(expected);
    //assertThat(solution1(given, target)).containsExactlyInAnyOrder(expected);
  }

  private static Stream<Arguments> providedGivenArrayAndExpected() {
    return Stream.of(
        Arguments.of(new int[]{2,7,11,15},9, new int[]{0,1}),
        Arguments.of(new int[]{3,2,4},6, new int[]{1,2}),
        Arguments.of(new int[]{3,3},6, new int[]{0,1})
    );
  }
}
