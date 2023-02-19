package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestFlags {

  @Test
  void simpleFlags() {
    Flags flags = new Flags();
    int[] range = {1,5,3,4,3,4,1,2,3,4,6,2};
    assertThat(flags.solution(range, 3)).isEqualTo(3);
  }
  @Test
  void simpleFlagsWith1Gap() {
    Flags flags = new Flags();
    int[] range = {1,5,3,4,3,4,1,2,3,4,6,2};
    assertThat(flags.solution(range, 1)).isEqualTo(4);
  }
}
