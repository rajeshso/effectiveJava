package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestFrogRiverOne {
  @Test
  void simpleTest() {
    int x = 5;
    int[] a = {1,3,1,4,2,3,5,4};
    FrogRiverOne frogRiverOne = new FrogRiverOne();
    int result = frogRiverOne.solution1(x,a);
    assertThat(result).isEqualTo(6);
  }
  @Test
  void simpleTestWithBetterSolution() {
    int x = 5;
    int[] a = {1,3,1,4,2,3,5,4};
    FrogRiverOne frogRiverOne = new FrogRiverOne();
    int result = frogRiverOne.solution2(x,a);
    assertThat(result).isEqualTo(6);
  }
  @Test
  void edgeTest1WithBetterSolution() {
    int x = 1;
    int[] a = {1,1,1};
    FrogRiverOne frogRiverOne = new FrogRiverOne();
    int result = frogRiverOne.solution2(x,a);
    assertThat(result).isEqualTo(0);
  }
  @Test
  void edgeTest2WithBetterSolution() {
    int x = 3;
    int[] a = {1,2,1};
    FrogRiverOne frogRiverOne = new FrogRiverOne();
    int result = frogRiverOne.solution2(x,a);
    assertThat(result).isEqualTo(-1);
  }

}
