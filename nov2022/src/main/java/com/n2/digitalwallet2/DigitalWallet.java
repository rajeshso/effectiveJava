package com.n2.digitalwallet2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DigitalWallet {

  private final Map<Integer, Account> accountMap = new HashMap<>();

  void createAccounts(int n) {
    for (int i = 0; i < n; i++) {
      Account account = new Account(i, 10);
      accountMap.put(i, account);
    }
  }

  public void deposit(int accountId, int amount) {
    if (!accountMap.containsKey(accountId)) {
      return;
    }
    Account account = accountMap.get(accountId);
    account.deposit(amount);
  }
  public boolean withdraw(int accountId, int amount) {
    if (!accountMap.containsKey(accountId)) {
      return false;
    }
    Account account = accountMap.get(accountId);
    return account.withdraw(amount);
  }
  public boolean transfer(int fromAccountId, int toAccountId, int amount) {
    if (!accountMap.containsKey(fromAccountId) || !accountMap.containsKey(toAccountId)) {
      return false;
    }
    Account fromAccount = accountMap.get(fromAccountId);
    Account toAccount = accountMap.get(toAccountId);
    return fromAccount.transfer(toAccount,amount);
  }

  public Collection<Account> getAccounts() {
    return Collections.unmodifiableCollection(accountMap.values());
  }

  public Account getAccount(int i) {
    return accountMap.get(i);
  }

  public Map<Integer, Account> getAccountMap() {
    return accountMap;
  }
}