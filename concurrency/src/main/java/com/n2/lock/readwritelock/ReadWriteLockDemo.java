package com.n2.lock.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CalendarUser extends Thread {

  private static final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
  private static int today = 0;
  private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private static final Lock readMarker = readWriteLock.readLock();
  private static final Lock writeMarker = readWriteLock.writeLock();

  public CalendarUser(String name) {
    super(name);
  }

  @Override
  public void run() {
    while (today < WEEKDAYS.length - 1) {
      if (this.getName().startsWith("Writer")) {
        writeMarker.lock();
        try {
          today = (today + 1) % 7;
          System.out.println(this.getName() + " has updated the day as " + WEEKDAYS[today]
              + " ; the writer count is = " + readWriteLock.getWriteHoldCount());
        } finally {
          writeMarker.unlock();
        }
      } else if (this.getName().startsWith("Reader")) {
        readMarker.lock();
        try {
          System.out.println(
              this.getName() + " sees the day as " + WEEKDAYS[today] + " ; the readerCount is = "
                  + readWriteLock.getReadLockCount());
        } finally {
          readMarker.unlock();
        }
      }
    }
  }
}

class ReadWriteLockDemo {

  public static void main(String[] args) {
    // create 10 reader threads
    for (int i = 0; i < 10; i++) {
      new CalendarUser("Reader-" + i).start();
    }
    // create 10 writer threads
    for (int i = 0; i < 2; i++) {
      new CalendarUser("Writer-" + i).start();
    }
  }
}
