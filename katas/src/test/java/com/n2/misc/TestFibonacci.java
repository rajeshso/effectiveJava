package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import com.n2.misc.Fibonacci;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//The Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21
public class TestFibonacci {

  static Stream<Arguments> fibonacciTestData() {
    return Stream.of(
        Arguments.of(9, new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21})
    );
  }

  @ParameterizedTest
  @MethodSource("fibonacciTestData")
  void testFibonacci(int n, int[] expected) {
    assertThat(Fibonacci.fibonacci(n)).containsExactly(expected);
  }
}
