package com.n2.java9;

public class Test {

  public static final int staticField = 1;

  public static void main(String[] args) {
    System.out.println("java");

    var text = "Planet";
    var year = "2020";

    System.out.println("year = " + year);
    System.out.println("text = " + text);

    for (int i = 0; i < 10; i++) {
      System.out.println("i = " + i);
    }

    if (year == null) {
      //Command+Shift+Enter
      //Command+Option+J
    }
  }

}
