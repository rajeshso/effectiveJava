package com.n2.codility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestLeader {

  static Stream<Arguments> leaderTestData() {
    return Stream.of(
        Arguments.of(new int[]{2, 4, 3, 3, 3, 2, 3}, new int[]{2, 3, 4, 6}),
        Arguments.of(new int[]{1, 1, 1, 1}, new int[]{0, 1, 2, 3}),
        Arguments.of(new int[]{1, 2, 3}, new int[]{})
    );
  }

  @ParameterizedTest
  @MethodSource("leaderTestData")
  void testLeaderSolutions(int[] arr, int[] expectedIndices) {
    if (expectedIndices.length > 0) {
      assertThat(Leader.solution(arr)).isIn((Object[]) Arrays.stream(expectedIndices).boxed().toArray());
      assertThat(Leader.solution1(arr)).isIn((Object[]) Arrays.stream(expectedIndices).boxed().toArray());
    } else {
      assertThat(Leader.solution(arr)).isEqualTo(-1);
      assertThat(Leader.solution1(arr)).isEqualTo(-1);
    }
  }

  @ParameterizedTest
  @MethodSource("leaderValueTestData")
  void testLeaderValue(int[] arr, int expected) {
    assertThat(leader(arr)).isEqualTo(expected);
  }

  static Stream<Arguments> leaderValueTestData() {
    return Stream.of(
        Arguments.of(new int[]{2, 4, 3, 3, 3, 2, 3}, 3),
        Arguments.of(new int[]{1, 1, 1, 1}, 1),
        Arguments.of(new int[]{1, 2, 3}, -1)
    );
  }

  private int leader(int[] arr) {
    Arrays.sort(arr);
    int halfOfLen = arr.length / 2;
    int mid = arr[halfOfLen];
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if (mid == arr[i]) {
        count++;
      }
    }
    if (count > halfOfLen) {
      return mid;
    } else {
      return -1;
    }
  }
}
