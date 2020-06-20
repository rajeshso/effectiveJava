package com.n2.divideAndConquer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//Add a long list of number by divide and conquer/ForkJoinPool
class RecursiveSum extends RecursiveTask<Long> {

  private final long hi;
  private final long low;

  public RecursiveSum(long hi, long low) {
    this.hi = hi;
    this.low = low;
  }

  @Override
  protected Long compute() {
    if (hi - low < 100_000) {
      long total = 0l;
      for (long i = low; i < hi; i++) {
        total += i;
      }
      return total;
    } else {
      long mid = (hi + low) / 2;
      RecursiveSum left = new RecursiveSum(mid, low);
      RecursiveSum right = new RecursiveSum(hi, mid + 1);
      left.fork(); //Divide
      return right.compute() + left.join(); //right can be computed directly in the current thread
    }
  }
}

public class ForkJoinPoolDemo {

  public static void main(String[] args) {
    RecursiveSum recursiveSum = new RecursiveSum(1_000_000_000, 0);
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    final Long total = forkJoinPool.invoke(recursiveSum);
    forkJoinPool.shutdown();
    System.out.println("Total is " + total);
  }
}
