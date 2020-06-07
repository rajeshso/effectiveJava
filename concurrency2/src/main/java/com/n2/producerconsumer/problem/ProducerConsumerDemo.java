package com.n2.producerconsumer.problem;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class SoupProducer extends Thread {

  private final BlockingQueue<String> servingQueue;

  SoupProducer(BlockingQueue<String> servingQueue) {
    this.servingQueue = servingQueue;
  }

  @Override
  public void run() {
    for (int i = 0; i < 20; i++) {
      try {
        servingQueue.add("Bowl-" + i);
        System.out.format("Served Bowl #%d - remaining capacity: %d\n", i,
            servingQueue.remainingCapacity());
        sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    servingQueue.add("no more soup for you");
    servingQueue.add("no more soup for you");//to signal the consumer to stop
  }
}

class SoupConsumer extends Thread {

  private final BlockingQueue<String> servingQueue;

  SoupConsumer(BlockingQueue<String> servingQueue) {
    this.servingQueue = servingQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        if (servingQueue.take().equals("no more soup for you")) {
          System.out.println("No more soup, so i stop consumption");
          break;
        }
        System.out.println("Ate " + servingQueue.take());
        sleep(300);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

class ProducerConsumerDemo {

  public static void main(String[] args) {
    BlockingQueue<String> servingQueue = new ArrayBlockingQueue<>(5);
    SoupProducer producer1 = new SoupProducer(servingQueue);
    SoupConsumer consumer1 = new SoupConsumer(servingQueue);
    SoupConsumer consumer2 = new SoupConsumer(servingQueue);

    producer1.start();
    consumer1.start();
    consumer2.start();
  }
}

