package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestMaxProfit {
  @Disabled
  void simpleTest() {
    int[] a= {5,-4,8,-10,-2,4,-3,2,7,-8,3,-5,3};
    MaxProfit maxProfit = new MaxProfit();
    final int result = maxProfit.solution(a);
    assertThat(result).isEqualTo(10);
  }
  @Test
  void codilityTestData() {
    int[] a= {23171,21011,21123,21366,21013,21367};
    MaxProfit maxProfit = new MaxProfit();
    final int result = maxProfit.solution(a);
    assertThat(result).isEqualTo(356);
  }
  @Test
  void udemyTestData() {
    int[] a= {114,132,119,91,84,29,61,76,38,21,9,63,45,68,81,124,118,78,44,59,80};
    MaxProfit maxProfit = new MaxProfit();
    final int result = maxProfit.solution(a);
    assertThat(result).isEqualTo(115);
  }
  @Test
  void udemyTestDataOnCleanerSolution() {
    int[] a= {114,132,119,91,84,29,61,76,38,21,9,63,45,68,81,124,118,78,44,59,80};
    MaxProfit maxProfit = new MaxProfit();
    final int result = maxProfit.solutionCleaner(a);
    assertThat(result).isEqualTo(115);
  }
}
