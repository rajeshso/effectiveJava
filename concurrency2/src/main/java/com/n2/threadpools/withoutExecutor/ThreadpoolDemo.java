package com.n2.threadpools.withoutExecutor;

class VegetableChopper extends Thread {

  @Override
  public void run() {
    System.out.println(this.getName() + " chopped a vegetable");
  }
}

public class ThreadpoolDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new VegetableChopper().start();
    }
  }
}
