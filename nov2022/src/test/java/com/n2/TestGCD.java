package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestGCD {
  public int solution(int a, int b) { //o(log(a+b))
    return gcd(a,b);
  }
  private int gcd(int a, int b) {
    if (b==0)
      return a;
    else
      return gcd(b,a%b);
  }
  @Test
  void simpleTest1() {
    assertThat(solution(742,518)).isEqualTo(14);
  }
  @Test
  void simpleTest2() {
    assertThat(solution(39,27)).isEqualTo(3);
  }
}
