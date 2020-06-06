package com.n2.lock.deadlockdemo;

class Balls {

  static int balls = 0;
}

class Runs {

  static int runs = 0;
}

class Counter implements Runnable {


  @Override
  public void run() {
    incrementBallAfterRun();
    incrementRunAfterBall();
  }

  private void incrementRunAfterBall() {
    synchronized (Balls.class) {
      synchronized (Runs.class) {
        Runs.runs++;
        Balls.balls++;
      }
    }
  }

  private void incrementBallAfterRun() {
    synchronized (Runs.class) {
      synchronized (Balls.class) {
        Balls.balls++;
        Runs.runs++;
      }
    }
  }
}

class DeadlockDemo {

  public static void main(String[] args) throws InterruptedException {
    Runnable target = new Counter();
    Thread t1 = new Thread(target);
    Thread t2 = new Thread(target);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Balls = " + Balls.balls);
    System.out.println("Runs = " + Runs.runs);
  }
}