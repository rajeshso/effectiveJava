package com.n2.conditionvariable.problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPerson extends Thread {

  private final int personID;
  private static final Lock slowCookerLid = new ReentrantLock();
  private static int servings = 11;

  public HungryPerson(int personID) {
    this.personID = personID;
  }

  @Override
  public void run() {
    while (servings > 0) {
      slowCookerLid.lock();
      try {
        if ((servings % 2 == personID) && (servings > 0))// threads take alternate servings
        {
          servings--;
          System.out.format(
              "Person %d opened the lid and took 1 serving. The remaining servings are %d\n",
              this.personID, servings);
        } else {
          System.out.format("This is not Person %d. So closes the lid back\n", personID);
        }
      } finally {
        slowCookerLid.unlock();
      }
    }
  }
}

//not efficient, because wrong person checks several times
class ConditionVariableDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 2; i++) {
      new HungryPerson(i).start();
    }
  }
}
