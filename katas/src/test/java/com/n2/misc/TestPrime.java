package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestPrime {

  static Stream<Arguments> primeTestData() {
    return Stream.of(
        Arguments.of(20, new Integer[]{2, 3, 5, 7, 11, 13, 17, 19}),
        Arguments.of(10, new Integer[]{2, 3, 5, 7}),
        Arguments.of(5, new Integer[]{2, 3, 5}),
        Arguments.of(2, new Integer[]{2})
    );
  }

  @ParameterizedTest
  @MethodSource("primeTestData")
  void testGetPrime(int limit, Integer[] expected) {
    assertThat(getPrime(limit)).containsExactlyInAnyOrder(expected);
  }

  protected Integer[] getPrime(int i) {
    List<Integer> result = new ArrayList<>(i);
    boolean prime = true;
    while(i>1) {
      for(int j=2;j<i;j++) {
        if (i%j==0) {
          prime=false;
        }
      }
      if (prime) result.add(i);
      prime=true;
      i--;
    }
    final Integer[] integers = result.toArray(new Integer[0]);
    return integers;
  }
}
