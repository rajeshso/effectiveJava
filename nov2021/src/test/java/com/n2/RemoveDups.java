package com.n2;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveDups {

  private int[] removeDups(int[] a) {
    return Arrays.stream(a).distinct().toArray();
  }

  @Test
  public void testRemoveDups() {
    int[] a = {7,8,8,2,3,5,3,7,2,9,8};
    Assertions.assertThat(removeDups(a)).containsExactlyInAnyOrder(2,3,5,7,8,9);
  }



  @Test
  public void testRemoveDups1() {
    int[] a = {7,8,8,2,3,5,3,7,2,9,8};
    int[] result= removeDups(a);
    Assertions.assertThat(result).containsExactlyInAnyOrder(2,3,5,7,8,9);
  }
}
