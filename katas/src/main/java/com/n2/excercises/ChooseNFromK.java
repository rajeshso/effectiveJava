package com.n2.excercises;

//https://medium.com/knerd/why-n-choose-k-a810ebee76d4
public class ChooseNFromK {

  // n!/r!(n-r)! combinations formula
  public int chooseNFromK(int n, int k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
  }

  private int factorial(int n) {
    if (n == 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  public static void main(String[] args) {
    System.out.println(new ChooseNFromK().chooseNFromK(5, 2));
  }

}
