package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestRotateMatrix90 {

  @Test
  public void testCase1() {
    // Test Case 1
    int N = 4;
    int mat[][] = {
        { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 }
    };
    int[][] rotated90 = RotateMatrix90.rotateMatrix(N, mat);
    assertThat(rotated90[0][0]).isEqualTo(mat[0][N-1]);
  }



}
