package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class AscendingOrderTest {

  private int[] ascending(int[] unorderedItems) {
    int len = unorderedItems.length;
    int[] a = Arrays.copyOf(unorderedItems, unorderedItems.length);
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len; j++) {
/*        if (a[i]>a[j]) {
          int temp = a[i];
          a[i] = a[j];
          a[j] = temp;
        }*/
        int min = Math.min(a[i], a[j]);
        int max = Math.max(a[i], a[j]);
        a[i] = min;
        a[j] = max;
      }
    }
    return a;
  }

  @Test
  void checkAscending() {
    int[] unorderedItems = new int[] {1,6,2,4,9};
    int[] expected = Arrays.copyOf(unorderedItems, unorderedItems.length);
    Arrays.sort(expected);
    int[] actual = ascending(unorderedItems);
    assertThat(actual).containsExactly(expected);
  }
}
