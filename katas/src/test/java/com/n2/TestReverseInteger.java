package com.n2;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestReverseInteger {

  @Test
  public void test123() {
    assertThat(ReverseInteger.reverse(123)).isEqualTo(321);
  }

  @Test
  public void test2020() {
    assertThat(ReverseInteger.reverse(2020)).isEqualTo(202);
  }
}
