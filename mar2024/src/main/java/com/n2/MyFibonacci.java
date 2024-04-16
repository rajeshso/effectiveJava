package com.n2;
class MyFibonacci {
//0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ....
  public static void main(String[] args) {
    printFibo1(0,1,144);
    printFibo2(0,1,144);
    printFibo3(0,1,144);
  }

  static int printFibo1(int previous, int current, int n) {
    if (previous == 0 && current == 1) {
      System.out.print(previous+","+current+","+current+",");
      previous = current;
      printFibo1(previous, current,n);
    }else if (current == n || current > n) {
      return 0;
    } else {
      int newCurrent = previous + current;
      System.out.print(newCurrent+",");
      printFibo1(current, newCurrent, n);
    }
    return 1;
  }
  static void printFibo2(int previous, int current, int n) {
    System.out.print(previous + ","+ current +",");
    while(true) {
      int newCurrent = previous + current;
      if (newCurrent <= n) {
        System.out.print(newCurrent+",");
        previous = current;
        current = newCurrent;
      }else {
        break;
      }
    }
  }
  static void printFibo3(int previous, int current, int n) {
    System.out.println(previous + ","+ current +",");
    printFiboRecursive(current, previous+current, n);
  }

  private static void printFiboRecursive(int previous, int current, int n) {
    if (current <=n) {
      System.out.print(current+",");
      printFiboRecursive(current, previous+current, n);
    }
  }
  }