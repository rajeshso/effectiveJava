package com.n2.mutualexclusion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Refer: DataRaceDemo.java
class Shopper extends Thread {

  static int garlicCount = 0;
  static Lock pencil = new ReentrantLock();

  public Shopper(String name) {
    super(name);
  }

  @Override
  public void run() {
    pencil.lock();
    for (int i = 0; i < 10_000_000; i++) {
      garlicCount++;
    }
    pencil.unlock();
  }
}

public class MutualExclusionDemo {


  public static void main(String[] args) throws InterruptedException {
    Thread rajesh = new Shopper("Rajesh");
    Thread priya = new Shopper("Priya");
    rajesh.start();
    priya.start();
    rajesh.join();
    priya.join();
    System.out.println("\nWe shall buy " + Shopper.garlicCount + " garlics");
  }
}
