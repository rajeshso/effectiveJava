package com.n2.liveness.deadlock.deadlockSolution;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {

  static int sushiCount = 100_000; //shared variable
  Lock firstChopstick, secondChopstick;

  Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
    super(name);
    this.firstChopstick = firstChopstick;
    this.secondChopstick = secondChopstick;
  }

  @Override
  public void run() {
    while (sushiCount > 0) { //eat sushi till its available

      //pick chopsticks
      firstChopstick.lock();
      secondChopstick.lock();

      if (sushiCount > 0) { //eat
        sushiCount--;
        System.out.println(this.getName() + " took a piece; remaining sushis is " + sushiCount);
      }

      //drop the chopsticks
      secondChopstick.unlock();
      firstChopstick.unlock();
    }
  }
}

class DeadlockDemo {

  public static void main(String[] args) {
    Lock chopstickA = new ReentrantLock();
    Lock chopstickB = new ReentrantLock();
    Lock chopstickC = new ReentrantLock();
    new Philosopher("Barron", chopstickA, chopstickB).start();
    new Philosopher("Olivia", chopstickB, chopstickC).start();
    new Philosopher("Steve", chopstickA, chopstickC)
        .start(); //Note the ordering of the locks: that is the solution to the problem
  }
}
