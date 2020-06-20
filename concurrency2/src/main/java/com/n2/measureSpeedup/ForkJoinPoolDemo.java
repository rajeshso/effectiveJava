package com.n2.measureSpeedup;

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

  final static int NUM_EVAL_RUNS = 10;
  final static long SUM_VALUE = 1_000_000_000;

  private static long sequentialSum(long hi, long low) {
    long result = 0l;
    for (long i = low; i < hi; i++) {
      result += i;
    }
    return result;
  }

  public static void main(String[] args) {

    System.out.println("Evaluating Sequential Implementation...");
    long sequentialResult = sequentialSum(SUM_VALUE, 0);
    double sequentialtime = 0;
    for (int i = 0; i < NUM_EVAL_RUNS; i++) {
      long start = System.currentTimeMillis();
      sequentialSum(SUM_VALUE, 0);
      long end = System.currentTimeMillis();
      sequentialtime += (end - start);
    }
    sequentialtime /= NUM_EVAL_RUNS;

    System.out.println("Evaluating Parallel Implementation...");
    RecursiveSum recursiveSum = new RecursiveSum(SUM_VALUE, 0);
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    final Long parallelResult = forkJoinPool.invoke(recursiveSum);
    forkJoinPool.shutdown();

    double parallelTime = 0;
    for (int i = 0; i < NUM_EVAL_RUNS; i++) {
      long start = System.currentTimeMillis();
      recursiveSum = new RecursiveSum(SUM_VALUE, 0);
      forkJoinPool = ForkJoinPool.commonPool();
      forkJoinPool.invoke(recursiveSum);
      forkJoinPool.shutdown();
      long end = System.currentTimeMillis();
      parallelTime += (end - start);
    }
    parallelTime /= NUM_EVAL_RUNS;

/*    if (parallelResult!=sequentialResult) {
      throw new Error("parallelResult != sequentialResult");
    }*/
    System.out.println("Average Sequential Time is " + sequentialtime + " ms");
    System.out.println("Average Parallel Time is " + parallelTime + " ms");
    System.out.format("\nSpeedup is %.2f \n", sequentialtime / parallelTime);
    System.out.format("\nEfficiency is %.2f \n",
        100 * (sequentialtime / parallelTime) / Runtime.getRuntime().availableProcessors());
  }
}
