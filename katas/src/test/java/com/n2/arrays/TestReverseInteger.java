package com.n2.arrays;

import static org.assertj.core.api.Assertions.*;

import com.n2.arrays.ReverseInteger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestReverseInteger {

  @ParameterizedTest
  @CsvSource({
    "123, 321",
    "2020, 202",
    "0, 0",
    "-123, -321"
  })
  void testReverse(int input, int expected) {
    assertThat(ReverseInteger.reverse(input)).isEqualTo(expected);
  }
}
