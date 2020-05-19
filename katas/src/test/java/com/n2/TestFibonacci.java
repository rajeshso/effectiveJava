package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
//The Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21
public class TestFibonacci {

  @Test
  public void test9() {
    int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21};
    assertThat(Fibonacci.fibonacci(9)).containsExactly(0, 1, 1, 2, 3, 5, 8, 13, 21);
  }

}
