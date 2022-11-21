package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestBrackets {

  @Test
  void simpleTest() {
    Brackets b = new Brackets();
    assertThat(b.solution("{[()()]}")).isOne();
  }
  @Test
  void simpleNegativeTest() {
    Brackets b = new Brackets();
    assertThat(b.solution("[(]()]")).isZero();
  }
}
