package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class TestIntersectionProblem {
  @Test
  public void simpleTest() {
    IntersectionProblem intersectionProblem = new IntersectionProblem();
    int[] a = {3,1,4,2,7};
    int[] b = {8,2,5,4,6};
    int[] expected = {4,2};
    int[] result = intersectionProblem.solution1(a,b);
    System.out.println(Arrays.toString(result));
    assertThat(result).containsExactly(expected);
  }
  @Test
  public void oOfNlogNTest() { //O(a+b)
    IntersectionProblem intersectionProblem = new IntersectionProblem();
    int[] a = {3,1,4,2,7};
    int[] b = {8,2,5,4,6};
    int[] expected = {4,2};
    int[] result = intersectionProblem.solution2(a,b);
    System.out.println(Arrays.toString(result));
    assertThat(result).containsExactlyInAnyOrder(expected);
  }
}
