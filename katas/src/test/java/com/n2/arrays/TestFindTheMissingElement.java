package com.n2.arrays;

import static com.n2.arrays.FindTheMissingElement.solution;
import static com.n2.arrays.FindTheMissingElement.solution1;
import static com.n2.arrays.FindTheMissingElement.solution2;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestFindTheMissingElement {

  @Test
  public void testFindTheMissingElementInMiddle() {
    int a[] = new int[]{1,4,5,3,2,8,6};
    assertThat(solution(a)).isEqualTo(7);
    assertThat(solution2(a)).isEqualTo(7);

  }

  @Test
  public void testFindTheMissingElementInFront() {
    int a[] = new int[]{1,4,5,3,2,8,6,-1};
    assertThat(solution(a)).isEqualTo(0);
    assertThat(solution2(a)).isEqualTo(0);
  }

  @Test
  public void testFindTheMissingElementInBack() {
    int a[] = new int[]{7,4,5,3,2,8,6};
    assertThat(solution(a)).isEqualTo(9);
    assertThat(solution2(a)).isEqualTo(9);
  }

  //Another solution, but this appends the value in the top, if there is no missing value
  @Test
  public void testFindTheMissingElementInMiddle1() {
    int a[] = new int[]{1,4,5,3,2,8,6};
    assertThat(solution1(a)).isEqualTo(7);
    assertThat(solution2(a)).isEqualTo(7);
  }

  @Disabled
  public void testFindTheMissingElementInFront1() {
    int a[] = new int[]{1,4,5,3,2,8,6,-1};
    assertThat(solution1(a)).isEqualTo(0);
  }

  @Disabled
  public void testFindTheMissingElementInBack1() {
    int a[] = new int[]{7,4,5,3,2,8,6};
    assertThat(solution1(a)).isEqualTo(9);
  }


}
