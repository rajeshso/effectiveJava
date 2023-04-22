package com.n2.digitalWallet1;

import java.util.HashMap;
import java.util.Map;

public class DigitalWalletSynchronized {
  private final Map<String, Account> accounts;

  public DigitalWalletSynchronized() {
    accounts = new HashMap<>();
  }

  public synchronized void createAccount(Account account) {
    if (accounts.containsKey(account.getAccountId())) {
      System.out.println("Account already exists");
    } else {
      accounts.put(account.getAccountId(), account);
      System.out.println("Account created successfully for "+account.getAccountId() +" with initial amount "+account.getBalance());
    }
  }

  public synchronized void deposit(String accountId, int amount) {
    if (accounts.containsKey(accountId)) {
      Account account = accounts.get(accountId);
      int balance = account.getBalance();
      account.setBalance(balance + amount);
      System.out.println("Deposit successful for account "+accountId +" with amount "+amount + ". The available balance is "+account.getBalance());
    } else {
      System.out.println("Account does not exist for "+accountId);
    }
  }

  public synchronized void withdraw(String accountId, int amount) {
    if (accounts.containsKey(accountId)) {
      Account account = accounts.get(accountId);
      int balance = account.getBalance();
      if (balance >= amount) {
        account.setBalance(balance - amount);
        System.out.println("Withdrawal successful for "+ accountId +" for amount "+amount + ". The available balance is "+account.getBalance());
      } else {
        System.out.println("Insufficient balance "+account.getBalance() +" for amount "+amount + ". The available balance in account "+accountId+" is "+account.getBalance());
      }
    } else {
      System.out.println("Account does not exist for "+accountId);
    }
  }

  public synchronized void transfer(String fromAccountId, String toAccountId, int amount) {
    if (accounts.containsKey(fromAccountId) && accounts.containsKey(toAccountId)) {
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
    } else {
      System.out.println("One or both accounts do not exist");
    }
  }

}

