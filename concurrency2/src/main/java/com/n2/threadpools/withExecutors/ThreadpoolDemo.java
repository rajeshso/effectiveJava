package com.n2.threadpools.withExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Note that the threads are reused and 100 threads are not created
class VegetableChopper extends Thread {

  @Override
  public void run() {
    System.out.println(currentThread().getName() + " chopped a vegetable");
  }
}

public class ThreadpoolDemo {

  public static void main(String[] args) {
    int numberOfProcessorsInThisMachine = Runtime.getRuntime().availableProcessors();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(numberOfProcessorsInThisMachine);
    for (int i = 0; i < 100; i++) {
      fixedThreadPool.submit(new VegetableChopper());
    }
    fixedThreadPool.shutdown();
  }
}
