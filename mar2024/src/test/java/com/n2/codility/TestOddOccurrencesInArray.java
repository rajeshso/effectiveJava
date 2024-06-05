package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
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
 * class Solution { public int solution(int[] A); }
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
    int actual = solution(givenArray);

    assertThat(actual).isEqualTo(expectedOddNumber);
  }

  private int solution(int[] givenArray) {
    Map<Integer, Integer> countOfValues = Arrays.stream(givenArray)
        .mapToObj(Integer::valueOf)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e->1)));
    //Find the key that has an odd value count
    final Set<Entry<Integer, Integer>> entrySetForOdd = countOfValues.entrySet().stream()
        .filter(i -> i.getValue() % 2 != 0).collect(Collectors.toSet());
    if (entrySetForOdd.isEmpty()) return -1;
    else return entrySetForOdd.stream().findAny().get().getKey();
  }
}
