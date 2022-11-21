package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestDominator {

  @Test
  void simpleTest() {
    int[] a = {2,3,3,3,3,4};
    Dominator dominator = new Dominator();
    int dom = dominator.solution(a);
    assertThat(dom).isEqualTo(3);
  }
  @Test
  void simpleTestEvenBetterSolution() {
    int[] a = {2,3,3,3,3,4};
    Dominator dominator = new Dominator();
    int dom = dominator.solutionEvenNicer(a);
    assertThat(dom).isEqualTo(3);
  }
  @Test
  void negativeTest() {
    int[] a = {1,2,3,4,5,6};
    Dominator dominator = new Dominator();
    int dom = dominator.solution(a);
    assertThat(dom).isEqualTo(-1);
  }
  @Test
  void negativeTestEvenBetterSolution() {
    int[] a = {1,2,3,4,5,6};
    Dominator dominator = new Dominator();
    int dom = dominator.solutionEvenNicer(a);
    assertThat(dom).isEqualTo(-1);
  }
  @Test
  void positiveTest() {
    int[] a = {3,0,1,1,4,1,1};
    Dominator dominator = new Dominator();
    int dom = dominator.solution(a);
    assertThat(dom).isEqualTo(1);
  }
  @Test
  void positiveTestEvenBetterSolution() {
    int[] a = {3,0,1,1,4,1,1};
    Dominator dominator = new Dominator();
    int dom = dominator.solutionEvenNicer(a);
    assertThat(dom).isEqualTo(1);
  }
}
