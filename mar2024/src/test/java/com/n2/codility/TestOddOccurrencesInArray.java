package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 * A non-empty array A consisting of N integers is given. The array contains an odd number of elements, and each element of the array can be paired with another element that has the same value, except for one element that is left unpaired.
 *
 * For example, in array A such that:
 *
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 * the elements at indexes 0 and 2 have value 9,
 * the elements at indexes 1 and 3 have value 3,
 * the elements at indexes 4 and 6 have value 9,
 * the element at index 5 has value 7 and is unpaired.
 * Write a function:
 *
 * class Solution { public int solution1(int[] A); }
 *
 * that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 * the function should return 7, as explained in the example above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an odd integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * all but one of the values in A occur an even number of times.
 */
public class TestOddOccurrencesInArray {

  private static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(new int[]{1, 2, 3, 4, 3, 2, 1}, 4),
        Arguments.of(new int[]{1}, 1),
        Arguments.of(new int[]{2, 2, 45}, 45),
        Arguments.of(new int[]{3, 21, 3}, 21),
        Arguments.of(new int[]{3, 3, 4, 4, 1, 1, 5}, 5),
        Arguments.of(new int[]{5, 3, 3, 4, 4, 1, 1}, 5),
        Arguments.of(new int[]{5, 1, 1, 4, 4, 1, 1}, 5)
    );
  }

  @ParameterizedTest
  @MethodSource("arguments")
  void testOddOccurrencesInArray(int[] givenArray, int expectedOddNumber) {
    assertThat(solution(givenArray)).isEqualTo(expectedOddNumber);
    assertThat(solutionOptimal(givenArray)).isEqualTo(expectedOddNumber);
  }

  //Overall O(n), but countOfOccurences has one O(n) and oddCountOfOccurences has another O(n)
  private int solution(int[] givenArray) {
    //Group items and count
    Map<Integer, Integer> countOfOccurences = Arrays.stream(givenArray)
        .mapToObj(i -> Integer.valueOf(i))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    //For every entry, filter only those that have odd Count of occurence
    Set<Map.Entry<Integer, Integer>> oddCountOfOccurences = countOfOccurences.entrySet()
        .stream().filter(entry -> entry.getValue() % 2 != 0).collect(Collectors.toSet());
    //If there is nothing in the oddCountOfOccurences, let us return -1
    if (oddCountOfOccurences.isEmpty() || oddCountOfOccurences.size() > 1)
      return -1;
    //If there is atleast one, let us return the first one (assuming there is only one entry in the givenArray
    return oddCountOfOccurences.stream().findAny().get().getKey();
  }

  /**
   * Optimal Solution A more optimal and commonly accepted solution1 uses the XOR bitwise operation.
   * This solution1 is efficient both in terms of time and space complexity.
   * <p>
   * Explanation The XOR operation has useful properties for this problem:
   * <p>
   * 𝑎 ⊕ 𝑎 = 0 a ⊕ a=0 (any number XORed with itself is 0) 𝑎 ⊕ 0 = 𝑎 a ⊕ 0=a (any number XORed
   * with 0 is the number itself) XOR is commutative and associative. Using these properties, you
   * can iterate through the array and XOR all elements together. The pairs will cancel each other
   * out (because 𝑎 ⊕ 𝑎 = 0 a ⊕ a=0), and you will be left with the unpaired element.
   *
   * Consider the array [9, 3, 9, 3, 7].
   *
   * Initially, result is 0.
   * i = 9: result becomes 0 ^ 9 = 9.
   * i = 3: result becomes 9 ^ 3 = 6. (Here, the first 3 cancels out with the second 3)
   * i = 9: result becomes 6 ^ 9 = 3. (Here, the first 9 cancels out with the second 9)
   * i = 7: result becomes 3 ^ 7 = 4. (Here, 7 is the unpaired element and remains)
   * @param givenArray
   * @return
   */
  private int solutionOptimal(int[] givenArray) {
    int result = 0;
    for (int i : givenArray) {
      result ^= i;
    }
    return result;
  }
}
