package com.n2.digitalWallet1;

import java.util.ArrayList;
import java.util.List;

public class Runner {
  DigitalWallet digitalWallet = new DigitalWallet();
  List<WithdrawalThread> withdrawalThreadList = new ArrayList();
  List<DepositThread> depositThreadList = new ArrayList();
  List<TransferThread> transferThreadList = new ArrayList();

  List<Account> accountList = new ArrayList();


  Runner() {
    for (int i = 0; i < 3; i++) {
      Account account = new Account(Integer.toString(i),
          Integer.toString(i),
          i,
          Integer.toString(i),
          100);
      accountList.add(account);
      digitalWallet.createAccount(account);
      withdrawalThreadList.add(new WithdrawalThread(digitalWallet, i, 10));
      depositThreadList.add(new DepositThread(digitalWallet, i, 10));
    }

    for (int i = 1; i < 3; i++) {
      transferThreadList.add(new TransferThread(digitalWallet, i-1, i,10));
    }
  }
  void run() throws InterruptedException {
    depositThreadList.stream().forEach(depositThread -> depositThread.start());
    withdrawalThreadList.stream().forEach(withdrawalThread -> withdrawalThread.start());
    transferThreadList.stream().forEach(transferThread -> transferThread.start());
    for (DepositThread depositThread : depositThreadList) {
      depositThread.join();
    }
    for (WithdrawalThread withdrawalThread : withdrawalThreadList) {
      withdrawalThread.join();
    }
    for (TransferThread transferThread : transferThreadList) {
      transferThread.join();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Runner runner = new Runner();
    runner.run();
    runner.accountList.stream().forEach(account -> System.out.println(account.getBalance()));
  }
  class WithdrawalThread extends Thread {

    private final DigitalWallet digitalWallet;
    private final int accountId;
    private final int amount;

    WithdrawalThread(DigitalWallet digitalWallet, int accountId, int amount) {

      this.digitalWallet = digitalWallet;
      this.accountId = accountId;
      this.amount = amount;
    }

    @Override
    public void run() {
      digitalWallet.withdraw(String.valueOf(accountId), amount);
    }
  }
  class DepositThread extends Thread {

    private final DigitalWallet digitalWallet;
    private final int accountId;
    private final int amount;

    DepositThread(DigitalWallet digitalWallet, int accountId, int amount) {

      this.digitalWallet = digitalWallet;
      this.accountId = accountId;
      this.amount = amount;
    }

    @Override
    public void run() {
      digitalWallet.deposit(String.valueOf(accountId), amount);
    }
  }
  class TransferThread extends Thread {

    private final DigitalWallet digitalWallet;
    private final int fromAccountId;
    int toAccountId;
    private final int amount;

    TransferThread(DigitalWallet digitalWallet, int fromAccountId, int toAccountId,int amount) {

      this.digitalWallet = digitalWallet;
      this.fromAccountId = fromAccountId;
      this.toAccountId = toAccountId;
      this.amount = amount;
    }

    @Override
    public void run() {
      digitalWallet.transfer(String.valueOf(fromAccountId),String.valueOf(toAccountId), amount);
    }
  }

}
