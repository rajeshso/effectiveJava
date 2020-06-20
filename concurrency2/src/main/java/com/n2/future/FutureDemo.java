package com.n2.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class HowManyVegetables implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println("Olivia is counting vegetables");
    Thread.sleep(3000);
    return 42;
  }

}

public class FutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Barron is asking Olivia to count vegetables");
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    final Future<Integer> integerFuture = singleThreadExecutor.submit(new HowManyVegetables());
    System.out.println("Barron can do other things while he waits for Olivia to return");
    System.out.println("Olivia responded with " + integerFuture.get());
    singleThreadExecutor.shutdown();
  }
}
