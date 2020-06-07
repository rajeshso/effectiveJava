package com.n2.conditionvariable.solution;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPerson extends Thread {

  private final int personID;
  private static final Lock slowCookerLid = new ReentrantLock();
  private static int servings = 11;
  private static final Condition soupTaken = slowCookerLid.newCondition();

  public HungryPerson(int personID) {
    this.personID = personID;
  }

  @Override
  public void run() {
    while (servings > 0) {
      slowCookerLid.lock();
      try {
        while ((servings % 2 != personID) && (servings > 0)) {
          System.out.format("This turn is not for Person %d. So closes the lid back\n", personID);
          soupTaken.await();
        }
        if (servings > 0)// threads take alternate servings
        {
          servings--;
          System.out.format(
              "Person %d opened the lid and took 1 serving. The remaining servings are %d\n",
              this.personID, servings);
          soupTaken.signal();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        slowCookerLid.unlock();
      }
    }
  }
}

// efficient, because wrong person checks few times
class ConditionVariableDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 2; i++) {
      new HungryPerson(i).start();
    }
  }
}
