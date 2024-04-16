package com.n2;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");
    MySimpleSingleton1 mySimpleSingleton1 = MySimpleSingleton1.create("Rajesh");
    System.out.println(mySimpleSingleton1.myNameIs());


    int[] intArray = {1, 2, 3};
    Integer[] integerArray = {1, 2, 3};

    processArray(intArray);    // Calls the method with int[] parameter
    processArray(integerArray);

    //
    List<String> baz = new ArrayList<>();
    foo(baz);

  }
  public static void processArray(int[] arr) {
    System.out.println("Processing int[] array");
    // Process int[] array
  }

  public static void processArray(Integer[] arr) {
    System.out.println("Processing Integer[] array");
    // Process Integer[] array
  }
  static public void foo(List<? extends Object> bar){
  }

}