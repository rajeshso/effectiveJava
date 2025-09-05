package com.n2.arrays;

import static com.n2.arrays.RemoveDups.removeDups;
import static com.n2.arrays.RemoveDups.removeDups1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestRemoveDups {

  @ParameterizedTest
  @MethodSource("testData")
  public void testRemoveDups(int[] input, int[] expected) {
    Assertions.assertThat(removeDups(input)).containsExactly(expected);
  }

  @ParameterizedTest
  @MethodSource("testData")
  public void testRemoveDups1(int[] input, int[] expected) {
    Assertions.assertThat(removeDups1(input)).containsExactlyInAnyOrder(expected);
  }

  static Stream<Arguments> testData() {
    return Stream.of(
        Arguments.of(new int[]{7,8,8,2,3,5,3,7,2,9,8}, new int[]{2,3,5,7,8,9})
    );
  }
}
