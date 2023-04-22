package com.n2.digitalWallet1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class DigitalWallet {
  private final Map<String, Account> accounts;
  private final ReentrantLock renentrantLock = new ReentrantLock();

  public DigitalWallet() {
    accounts = new HashMap<>();
  }

  public void createAccount(Account account) {
    if (accounts.containsKey(account.getAccountId())) {
      System.out.println("Account already exists");
    } else {
      accounts.put(account.getAccountId(), account);
      System.out.println("Account created successfully for "+account.getAccountId() +" with initial amount "+account.getBalance());
    }
  }

  public void deposit(String accountId, int amount) {
    if (accounts.containsKey(accountId)) {
      renentrantLock.lock();
      Account account = accounts.get(accountId);
      int balance = account.getBalance();
      account.setBalance(balance + amount);
      System.out.println("Deposit successful for account "+accountId +" with amount "+amount + ". The available balance is "+account.getBalance());
      renentrantLock.unlock();
    } else {
      System.out.println("Account does not exist for "+accountId);
    }
  }

  public void withdraw(String accountId, int amount) {
    if (accounts.containsKey(accountId)) {
      renentrantLock.lock();
      Account account = accounts.get(accountId);
      int balance = account.getBalance();
      if (balance >= amount) {
        account.setBalance(balance - amount);
        System.out.println("Withdrawal successful for "+ accountId +" for amount "+amount + ". The available balance is "+account.getBalance());
      } else {
        System.out.println("Insufficient balance "+account.getBalance() +" for amount "+amount + ". The available balance in account "+accountId+" is "+account.getBalance());
      }
      renentrantLock.unlock();
    } else {
      System.out.println("Account does not exist for "+accountId);
    }
  }

  public void transfer(String fromAccountId, String toAccountId, int amount) {
    if (accounts.containsKey(fromAccountId) && accounts.containsKey(toAccountId)) {
      renentrantLock.lock();
      Account fromAccount = accounts.get(fromAccountId);
      Account toAccount = accounts.get(toAccountId);
      int fromBalance = fromAccount.getBalance();
      int toBalance = toAccount.getBalance();
      System.out.println("Transfer request "+" for amount "+amount + " Balance for fromAccount "+fromAccountId + " is "+fromAccount.getBalance() + " and toAccount "+toAccountId +" is "+toAccount.getBalance());
      if (fromBalance >= amount) {
        fromAccount.setBalance(fromBalance - amount);
        toAccount.setBalance(toBalance + amount);
        System.out.println("Transfer successful "+" for amount "+amount + " Balance for fromAccount "+fromAccountId + " is "+fromAccount.getBalance() + " and toAccount "+toAccountId +" is "+toAccount.getBalance());
      } else {
        System.out.println("Insufficient balance "+fromAccount.getBalance()+" in fromAccount "+ fromAccountId +" for given amount "+ amount);
      }
      renentrantLock.unlock();
    } else {
      System.out.println("One or both accounts do not exist");
    }
  }

}

@AllArgsConstructor
@Getter
@Setter
class Account {
  private String accountId;
  private String accountName;
  private int age;
  private String address;
  private int balance;
}
