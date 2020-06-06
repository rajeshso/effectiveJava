package com.n2.mutualexclusion;


import java.util.concurrent.atomic.AtomicInteger;

//Refer: DataRaceDemo.java
class ShopperAtomic extends Thread {

  static AtomicInteger garlicCount = new AtomicInteger(0);

  public ShopperAtomic(String name) {
    super(name);
  }

  @Override
  public void run() {
    for (int i = 0; i < 10_000_000; i++) {
      garlicCount.incrementAndGet();
    }
  }
}

public class AtomicVariableDemo {


  public static void main(String[] args) throws InterruptedException {
    Thread rajesh = new ShopperAtomic("Rajesh");
    Thread priya = new ShopperAtomic("Priya");
    rajesh.start();
    priya.start();
    rajesh.join();
    priya.join();
    System.out.println("\nWe shall buy " + ShopperAtomic.garlicCount + " garlics");
  }
}
