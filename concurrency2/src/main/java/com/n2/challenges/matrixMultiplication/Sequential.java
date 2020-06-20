package com.n2.challenges.matrixMultiplication;

import java.util.Arrays;

/**
 * 00 01 02 10 11 12
 */
public class Sequential {

  int[][] a;
  int[][] b;

  public Sequential(int[][] a, int[][] b) {
    this.a = a;
    this.b = b;
  }

  public static void main(String[] args) {
    int[][] a = {{1, 2, 3}, {4, 5, 6}};
    int[][] b = {{7, 8}, {9, 10}, {11, 12}};

    final int[][] multiplied = new Sequential(a, b).multiply();
    System.out.println(Arrays.toString(multiplied));
  }

  public int[][] multiply() {
    int numOfRowsOfA = a.length;
    int numOfColoumnsOfA = a[0].length;
    int numOfRowsOfB = b.length;
    int numOfColoumnsOfB = b[0].length;
    if (numOfColoumnsOfA != numOfRowsOfB) {
      throw new Error("Invalid dimensions of matrix");
    }
    int[][] c = new int[numOfRowsOfA][numOfColoumnsOfB];
    for (int i = 0; i < numOfRowsOfA; i++) {
      for (int k = 0; k < numOfColoumnsOfB; k++) {
        int sum = 0;
        for (int j = 0; j < numOfColoumnsOfA; j++) {
          sum += a[i][j] * b[j][k];
          // System.out.printf("\n sum %d, i=%d, j=%d, k=%d \n",sum,i,j,k);
        }
        c[i][k] = sum;
      }

    }
    return c;
  }
}
