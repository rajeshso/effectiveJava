package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class TestFactorial {
  BigInteger factorial(int n) {
    return IntStream.rangeClosed(2,n)
        .mapToObj(BigInteger::valueOf)
        .parallel()
        .reduce(BigInteger::multiply)
        .orElse(BigInteger.ZERO);
  }
  @Test
  void simpleTest() {
    assertThat(factorial(5)).isEqualTo(120);
  }
}
