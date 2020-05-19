package com.n2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class PreferLambda {

  public static void main(String[] args) {
    //
    List<String> words = List.of("Rajesh", "Raghavendran", "Karthik", "Arun", "Ashok");
    List wordsList1 = new ArrayList<String>(words);
    Collections.sort(wordsList1, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
      }
    });
    System.out.println(wordsList1);
    List<String> wordsList2 = new ArrayList<String>(words);
    Collections.sort(wordsList2, Comparator.comparingInt(String::length));
    System.out.println(wordsList2);

    //
    OperationSimple simpleMinus = OperationSimple.MINUS;
    System.out.println(simpleMinus.apply(4,3));
    OperationSimple simplePlus = OperationSimple.PLUS;
    System.out.println(simplePlus.apply(4,3));

    Comparator<String> comparator1 =  new Comparator<String>() {
     @Override
     public int compare(String o1, String o2) {
       System.out.println(this.getClass());
       return Integer.compare(o1.length(), o2.length());
     }
    };
    Comparator<String> comparator2 = (s1,s2) -> Integer.compare(s1.length(), s2.length());


  }

}

enum OperationSimple {
  PLUS("+") {
    @Override
    public double apply(double x, double y) {
      return x+y;
    }
  },
  MINUS("-") {
    @Override
    public double apply(double x, double y) {
      return x-y;
    }
  };

  private final String symbol;

  OperationSimple(String symbol) {
    this.symbol = symbol;
  }

  public abstract double apply(double x, double y);

  @Override
  public String toString() {
    return symbol;
  }
}


