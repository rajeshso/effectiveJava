package com.n2.arrays;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestDiagram {

  @Test
  public void aabcaabcabdaShouldReturn7() {
    assertThat(new Diagram().solution("aabcaabcabda")).isEqualTo(7);
  }
}
