package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FindTheMissingElement {

  int findMissingDigit(int[] a) {
    Arrays.sort(a);
    boolean missingFound = false;
    int result = 0;
    for (int i = 0; i < (a.length-1) ; i++) {
      if (a[i+1] != a[i]+1) {
        missingFound = true;
        result = a[i]+1;
        break;
      }
    }
    return (missingFound? result : a[a.length-1]+1);
  }

  int findGreatestNumber(int[] a) {
    Arrays.sort(a);
    return a[a.length-1];
  }

  @Test
  void testfindGreatestNumber() {
    int[] given1 = {3,1,5,4};
    assertThat(findGreatestNumber(given1)).isEqualTo(5);
    int[] given2 = {0,3,-1,1,4};
    assertThat(findGreatestNumber(given2)).isEqualTo(4);
    int[] given3 = {0,3,2,-1,1,4};
    assertThat(findGreatestNumber(given3)).isEqualTo(4);
  }

  @Test
  void testMissingDigit() {
    int[] given1 = {3,1,5,4};
    assertThat(findMissingDigit(given1)).isEqualTo(2);
    int[] given2 = {0,3,-1,1,4};
    assertThat(findMissingDigit(given2)).isEqualTo(2);
    int[] given3 = {0,3,2,-1,1,4};
    assertThat(findMissingDigit(given3)).isEqualTo(5);
  }
}
