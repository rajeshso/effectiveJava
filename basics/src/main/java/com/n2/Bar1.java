package com.n2;

import com.n2.shine.Foo;

public class Bar1 extends Foo {
  @Override
  protected void myMethod() {
    System.out.println(myField + " from Foo");
  }

  public static void main(String[] args) {
    Bar1 bar1 = new Bar1();
    bar1.myMethod();
  }

}
