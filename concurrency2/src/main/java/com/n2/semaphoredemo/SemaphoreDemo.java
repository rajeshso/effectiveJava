package com.n2.semaphoredemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class CellPhone extends Thread {

  //Note : If you have permits 1, you can call this a binary semaphore, if more than 1, you can call this as a counting semaphore
  private static final Semaphore charger = new Semaphore(4);

  public CellPhone(String name) {
    super(name);
  }

  @Override
  public void run() {
    try {
      charger.acquire();
      System.out.println(this.getName() + " is charging....");
      sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(this.getName() + " has completed charging");
      charger.release();
    }
  }
}

class SemaphoreDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new CellPhone("Phone-" + i).start();
    }
  }
}
