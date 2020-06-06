package com.n2.datarace;

class Shopper extends Thread {

  static int garlicCount = 0;

  public Shopper(String name) {
    super(name);
  }

  @Override
  public void run() {
    for (int i = 0; i < 10_000_000; i++) {
      garlicCount++;
    }
  }
}

public class DataRaceDemo {


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
