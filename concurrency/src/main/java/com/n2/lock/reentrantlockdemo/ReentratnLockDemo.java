package com.n2.lock.reentrantlockdemo;

import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {

  static int garlicCount = 0;
  static int potatoCount = 0;
  static ReentrantLock pencil = new ReentrantLock();

  private void addGarlic() {
    pencil.lock();
    System.out.println("Hold Count = " + pencil.getHoldCount());
    garlicCount++;
    pencil.unlock();
  }

  private void addPotatoes() {
    pencil.lock();
    potatoCount++;
    addGarlic();
    pencil.unlock();
  }

  @Override
  public void run() {
    for (int i = 0; i < 10_000; i++) {
      addGarlic();
      addPotatoes();
    }
  }

}

class ReentrantLockDemo {

  public static void main(String[] args) throws InterruptedException {
    Shopper rajesh = new Shopper();
    Shopper priya = new Shopper();
    rajesh.start();
    priya.start();
    rajesh.join();
    priya.join();
    System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    System.out.println("We should buy " + Shopper.potatoCount + " potatoes.");

  }
}
