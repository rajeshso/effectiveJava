package com.n2.lock.trylockdemo1;

import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {

  static int itemsInNotepad = 0;
  int itemsToAdd = 0;
  static ReentrantLock pencil = new ReentrantLock();

  Shopper(String name) {
    super(name);
  }

  @Override
  public void run() {
    while (itemsInNotepad <= 20) {
      if (itemsToAdd > 0) { //items present, add them to the notepad
        try {
          pencil.lock();
          itemsInNotepad += itemsToAdd;
          System.out.println(
              this.getName() + " added " + itemsToAdd + " item(s) to notepad. Notepad is now "
                  + itemsInNotepad);
          itemsToAdd = 0;
          Thread.sleep(1000);//Time spent writing
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          pencil.unlock();
        }

      } else {//items absent, think and collect them to itemsToAdd
        try {
          Thread.sleep(100);//think
          itemsToAdd++;// collect
          System.out.println(this.getName() + " found " + itemsToAdd + " to buy.");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

}

class TryLockDemo {

  public static void main(String[] args) throws InterruptedException {
    Shopper rajesh = new Shopper("Rajesh");
    Shopper priya = new Shopper("Priya");
    long start = System.currentTimeMillis();
    rajesh.start();
    priya.start();
    rajesh.join();
    priya.join();
    long end = System.currentTimeMillis();
    System.out.println(
        "Shopper's notepad count is " + Shopper.itemsInNotepad + " in elapsed time " + (
            (float) (end - start) / 1000));


  }
}
