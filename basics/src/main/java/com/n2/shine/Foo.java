package com.n2.shine;

import java.io.IOException;

//public strictfp class Foo {
public class Foo {
  protected final static String myField = "Foo";
  protected  void myMethod() {
    System.out.println(myField + " from Foo");
  }

  Object myMethod1(String argument) throws IOException {
    return "";
  }
   static void sleep() {
     System.out.println("Foo Sleep");
   }
  double num1 = 10e+102;
  double num2 = 6e+08;
  //strictfp double calculate() {
  double calculate() {
    return num1 / num2;
  }
}
class Bar extends Foo {
  protected int myField = 1;
  Bar() {
    super();
  }
  Bar(int i) {
    this();
  }
  @Override
  protected void myMethod() {
    System.out.println(myField + " from Bar");
  }

  protected String myMethod(String s) {
    System.out.println(myField + " from Bar");
    return "myMethod Overloaded";
  }
  protected Animal myMethod1(Object argument)  {
    System.out.println("return types of overloaded methods can be anything");
    return new Animal() {
      @Override
      public void move() {

      }
    };
  }
  //@Override
  protected String myMethod1(String argument)  {
    System.out.println("return types of overridden methods are covariant");
/*    try {
      super.myMethod1(argument);
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    return "";
  }


  static void sleep() {
    System.out.println("Bar Sleep");
  }
  public static void main(String[] args) {
    Foo foo = new Foo();
    System.out.println("Foo foo = new Foo() => "+ Foo.myField);
    foo.myMethod();
    Foo.sleep();
    Foo foo1 = new Bar();
    System.out.println("Foo foo1 = new Bar(); => "+ Foo.myField);
    foo1.myMethod();
    Foo.sleep();
    Bar bar = new Bar();
    System.out.println("Bar bar = new Bar(); => "+ bar.myField);
    bar.myMethod();
    sleep();
    bar.myMethod1(1);
    bar.myMethod1("String");
    System.out.println(bar.calculate());
  }

}//hackerrank

interface Animal {
  void move();
}
abstract class AbstractDog implements Animal {

  protected abstract void bark();
  public void move() {
     System.out.println("1");
  }

}