package com.n2.racecondition.problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//problem: Every time this is executed, the order of threads is different and the result is different
class Shopper extends Thread {

  static int bagofChips = 0;
  private static final Lock pencil = new ReentrantLock();

  Shopper(String writer) {
    super(writer);
  }

  @Override
  public void run() {
    if (this.getName().startsWith("Olivia")) {
      pencil.lock();
      try {
        bagofChips += 3;
        System.out.println(getName() + " ADDED three bags of chips");
      } finally {
        pencil.unlock();
      }
    } else {
      pencil.lock();
      try {
        bagofChips *= 3;
        System.out.println(getName() + " DOUBLED the bags of chips");
      } finally {
        pencil.unlock();
      }
    }
  }
}

class RaceConditionDemo {

  public static void main(String[] args) throws InterruptedException {
    //Create 10 shoppers : Barron-0,2,4,6,8 and Olivia-1,3,5,7,9
    Shopper[] shoppers = new Shopper[10];
    for (int i = 0; i < shoppers.length / 2; i++) {
      shoppers[2 * i] = new Shopper("Barron-" + (2 * i));
      shoppers[(2 * i) + 1] = new Shopper("Olivia-" + ((2 * i) + 1));
    }
    for (Shopper s : shoppers) {
      s.start();
    }
    for (Shopper s : shoppers) {
      s.join();
    }
    System.out.println("We need to buy " + Shopper.bagofChips + " bags of chips.");
  }
}
