package com.n2.misc;

public class RotateMatrix90 {
  public static int[][] rotateMatrix(int N, int mat[][]) {
    int[][] rotated = new int[N][N];
    int column = N;
    for (int x=0;x<N;x++) {
      column = N-1;
      for (int y=0;y<N;y++) {
        rotated[x][y] = mat[y][column];
      }
    }
    return rotated;
  }

}
