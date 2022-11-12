package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ReverseInteger {

  private int reverse(int i) {
    int quotient, remainder, result = 0;
    while (i!=0) {
      quotient = i/10;
      remainder = i%10;
      result = (result*10)+remainder;
      i=quotient;
    }
    return result;
  }

  @Test
  public void test123() {
    assertThat(reverse(123)).isEqualTo(321);
  }

  @Test
  public void test2020() {
    assertThat(reverse(2020)).isEqualTo(202);
  }
}
