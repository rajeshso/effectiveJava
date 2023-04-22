package com.n2.digitalwallet2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * In case Account is persisted in the database, how can we ensure Account and its balance are safely updated ?
 *
 * If the Account object is persisted in a database, there are a few ways to ensure that it and its balance are safely updated:
 *
 * Use a transaction: When updating the account and its balance, wrap the database operations in a transaction. This ensures that either all the updates are applied or none of them are applied. If there is a failure during the update, the transaction will be rolled back, ensuring that the account and its balance remain unchanged.
 *
 * Use optimistic locking: When retrieving the account from the database, include a version number in the query. When updating the account and its balance, check that the version number of the retrieved account is the same as the one in the database. If they are the same, update the account and its balance and increment the version number. If they are not the same, it means that the account was updated by another process in the meantime, and the update should be aborted.
 *
 * Use pessimistic locking: Lock the account record in the database before updating it. This ensures that no other process can update the account while it is being updated. However, this approach can lead to contention and reduced performance, especially in high-concurrency scenarios.
 *
 * By using one or a combination of these approaches, you can ensure that the Account object and its balance are safely updated in a persisted scenario.
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account {
  private int id;
  private int balance;
  private Lock lock = new ReentrantLock();

  public Account(int id, int balance) {
    this.id = id;
    this.balance = balance;
  }
  public void deposit(int amount) {
    lock.lock();
    try {
      System.out.println("Deposit-start- The current balance of Account "+id+" is "+balance+" . We would add "+amount+" to the existing "+balance);
      balance+=amount;
      System.out.println("Deposit -end - After the deposit of "+amount+", The current balance of Account "+id+" is "+balance);
    }finally {
      lock.unlock();
    }
  }
  public boolean withdraw(int amount) {
    boolean success;
    lock.lock();
    try {
      if (balance < amount) {
        System.out.println("withdraw-no - The current balance of Account "+id+" is "+balance+" . We cannot withdraw "+amount+" from the existing "+balance);
        success = false;
      }else {
        System.out.println("Withdraw-start- The current balance of Account "+id+" is "+balance+" . We would withdraw "+amount+" from the existing "+balance);
        balance-=amount;
        success = true;
        System.out.println("Withdraw -end - After the Withdraw of amount "+amount+", The current balance of Account "+id+" is "+balance);

      }
    }finally {
      lock.unlock();
    }
    return success;
  }
  public boolean transfer(Account toAccount, int amount) {
    boolean success = false;
    /**
     * why do you check id < to.getId() ? you have decided to use the sequence of locking based on the condition of id< to.getId() ? Does the sequence of locking and the account id be linked ?
     * Answer:
     * The condition id < to.getId() is used to determine the order in which the locks will be acquired to avoid deadlocks. By convention, we can say that the lock order will always be the one where the account with the lower id is locked first followed by the account with the higher id.
     *
     * This is to avoid situations where two threads try to acquire the locks in opposite orders and end up getting stuck, waiting for each other to release the locks, causing a deadlock.
     *
     * In other words, we have to establish a consistent order of acquiring the locks to avoid deadlocks, and one way to do this is to use the account id as a tiebreaker.
     */
    if (id < toAccount.getId()) {
      try {
        lock.lock();
        try {
          toAccount.lock.lock();
          if (balance< amount) {
            success = false;
            System.out.println("transfer-no - The current balance of Account "+id+" is "+balance+" . We cannot transfer "+amount+" from the existing "+balance);
          }else {
            System.out.println("transfer-start- The current balance of Account "+id+" is "+balance+" And the current balance of toAccount "+toAccount.getId()+" is "+toAccount.getBalance()+". We would withdraw "+amount+" from the existing "+balance);
            balance-=amount;
            toAccount.setBalance(toAccount.getBalance()+amount);
            System.out.println("transfer-end- The current balance of Account "+id+" is "+balance+" And the current balance of toAccount "+toAccount.getId()+" is "+toAccount.getBalance());
            success=true;
          }
        }finally {
          toAccount.lock.unlock();
        }
      }finally {
        lock.unlock();
      }
    }else {
      try {
        toAccount.lock.lock();
        try {
          lock.lock();
          if (balance< amount) {
            success = false;
            System.out.println("transfer-no - The current balance of Account "+id+" is "+balance+" . We cannot transfer "+amount+" from the existing "+balance);
          }else {
            System.out.println("transfer-start- The current balance of Account "+id+" is "+balance+" And the current balance of toAccount "+toAccount.getId()+" is "+toAccount.getBalance()+". We would withdraw "+amount+" from the existing "+balance);
            balance-=amount;
            toAccount.setBalance(toAccount.getBalance()+amount);
            System.out.println("transfer-end- The current balance of Account "+id+" is "+balance+" And the current balance of toAccount "+toAccount.getId()+" is "+toAccount.getBalance());
            success=true;
          }
        }finally {
          lock.unlock();
        }
      }finally {
        toAccount.lock.unlock();
      }
    }
    return success;
  }
}
