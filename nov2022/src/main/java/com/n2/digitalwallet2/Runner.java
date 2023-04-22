package com.n2.digitalwallet2;

import java.util.ArrayList;
import java.util.List;

public class Runner {
  DigitalWallet digitalWallet =new DigitalWallet();
  int accounts=4;
  List<DepositThread> depositThreadList = new ArrayList<>();
  List<WithdrawalThread> withdrawalThreadList = new ArrayList<>();
  List<TransferThread> transferThreadList = new ArrayList<>();
  Runner() {
    digitalWallet.createAccounts(accounts);
    for (int i = 0; i < accounts; i++) {
      depositThreadList.add(new DepositThread(digitalWallet,i,10));
      withdrawalThreadList.add(new WithdrawalThread(digitalWallet,i,5));
    }
    for (int i = 1; i < accounts; i++) {
      transferThreadList.add(new TransferThread(digitalWallet,i-1,i,5));
    }
  }
  void start() {
    depositThreadList.forEach(depositThread -> depositThread.start());
    withdrawalThreadList.forEach(withdrawalThread -> withdrawalThread.start());
    transferThreadList.forEach(transferThread -> transferThread.start());

    depositThreadList.forEach(depositThread -> {
      try {
        depositThread.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    withdrawalThreadList.forEach(withdrawalThread -> {
      try {
        withdrawalThread.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    transferThreadList.forEach(transferThread -> {
      try {
        transferThread.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public static void main(String[] args) {
    Runner runner = new Runner();
    runner.start();
  }
}

class DepositThread extends Thread {
  DigitalWallet digitalWallet;
  int accountId;
  int amount;

  public DepositThread(DigitalWallet digitalWallet, int accountId, int amount) {
    this.digitalWallet = digitalWallet;
    this.accountId = accountId;
    this.amount = amount;
  }

  @Override
  public void run() {
    this.digitalWallet.deposit(accountId, amount);
  }
}
class WithdrawalThread extends Thread {
  DigitalWallet digitalWallet;
  int accountId;
  int amount;

  public WithdrawalThread(DigitalWallet digitalWallet, int accountId, int amount) {
    this.digitalWallet = digitalWallet;
    this.accountId = accountId;
    this.amount = amount;
  }

  @Override
  public void run() {
    this.digitalWallet.withdraw(accountId, amount);
  }
}
class TransferThread extends Thread {
  DigitalWallet digitalWallet;
  int fromAccountId;
  int toAccountId;
  int amount;

  public TransferThread(DigitalWallet digitalWallet, int fromAccountId, int toAccountId,int amount) {
    this.digitalWallet = digitalWallet;
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
    this.amount = amount;
  }

  @Override
  public void run() {
    this.digitalWallet.transfer(fromAccountId, toAccountId,amount);
  }
}