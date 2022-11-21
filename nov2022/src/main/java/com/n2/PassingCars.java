package com.n2;

import java.util.Arrays;

//https://medium.com/@sarcas0705/codility-passing-cars-bc24b09b07ed
//https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
public class PassingCars {

  int solution(int[] a) {
    int east = 1;
    int west = 0;
    int westMovingCars = 0;
    int passingCars = 0;
    int len = a.length;
    //count the cars before us and add to the passingCars
    for (int j : a) {
      if (west == j) {
        westMovingCars++;
      } else if (east == j) {
        passingCars += westMovingCars;
      }
    }
    return passingCars;
  }

  public static void main(String[] args) {
    PassingCars passingCars = new PassingCars();
    int[] carDirections = {0,1,0,1,1};
    System.out.println(Arrays.toString(carDirections) + " = "+ passingCars.solution(carDirections));
  }
}

