package com.n2.arrays;

import static com.n2.arrays.RemoveDups.removeDups;
import static com.n2.arrays.RemoveDups.removeDups1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRemoveDups {

  @Test
  public void testRemoveDups() {
    int[] a = {7,8,8,2,3,5,3,7,2,9,8};
    Assertions.assertThat(removeDups(a)).containsExactly(2,3,5,7,8,9);
  }
  @Test
  public void testRemoveDups1() {
    int[] a = {7,8,8,2,3,5,3,7,2,9,8};
    Assertions.assertThat(removeDups1(a)).containsExactlyInAnyOrder(2,3,5,7,8,9);
  }
}
