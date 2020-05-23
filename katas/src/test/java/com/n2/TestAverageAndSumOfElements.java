package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestAverageAndSumOfElements {

  @Test
  public void testAverage() {
    int a[] = {1,1,2,3,4,5,6,6};
    assertThat(average(a)).isEqualTo(3);
  }
  @Test
  public void testSum() {
    int a[] = {1,1,2,3,4,5,6,6};
    assertThat(sum(a)).isEqualTo(28);
  }

  private int sum(int[] a) {
    int count = 0;
    for (int i=0;i<a.length;i++) {
      count+=a[i];
    }
    return count;
  }

  private int average(int[] a) {
    return sum(a)/a.length;
  }
}
