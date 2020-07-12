package com.n2.datarace;

import java.util.concurrent.atomic.AtomicInteger;

class Shopper1  {

  //static AtomicInteger garlicCount = new AtomicInteger();
  AtomicInteger garlicCount;
  String name;
  public Shopper1(String name, AtomicInteger garlicCount) {
    this.name = name;
    this.garlicCount = garlicCount;
  }
/*  public Shopper1(String name) {
    this.name = name;
  }*/

  public void run()  {
  //  System.out.println(name);
  //  System.out.println("garlics start count = "+ garlicCount);
    for (int i = 0; i < 10_000_000; i++) {
      if (name.equals("Rajesh") && i == 10_000) {
        try {
          Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      garlicCount.incrementAndGet();
    }
  //  System.out.println("garlics end count = "+ garlicCount);
  }

  public AtomicInteger getGarlicCount() {
    return garlicCount;
  }
}

public class DataRaceDemo1 {


  public static void main(String[] args) {
    AtomicInteger garlicCount = new AtomicInteger();
    Shopper1 rajesh = new Shopper1("Rajesh",garlicCount);
    Shopper1 priya = new Shopper1("Priya",garlicCount);
    for (int i = 0; i < 10_0; i++) {
      System.out.println(i + "  "+ rajesh.getGarlicCount().intValue());
      rajesh.run();
      priya.run();
    }
    System.out.println("\nWe shall buy " + rajesh.getGarlicCount().intValue() + " garlics");
  }
}