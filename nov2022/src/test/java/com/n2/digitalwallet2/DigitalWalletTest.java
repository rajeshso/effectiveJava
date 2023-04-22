package com.n2.digitalwallet2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DigitalWalletTest {

  private DigitalWallet digitalWallet;
  private Account mockAccount1;
  private Account mockAccount2;

  @BeforeEach
  void setUp() {
    digitalWallet = new DigitalWallet();
    digitalWallet.createAccounts(2);

    mockAccount1 = Mockito.mock(Account.class);
    when(mockAccount1.getId()).thenReturn(0);
    when(mockAccount1.getBalance()).thenReturn(10);

    mockAccount2 = Mockito.mock(Account.class);
    when(mockAccount2.getId()).thenReturn(1);
    when(mockAccount2.getBalance()).thenReturn(10);

    digitalWallet.getAccountMap().put(0, mockAccount1);
    digitalWallet.getAccountMap().put(1, mockAccount2);
  }

  @Test
  void depositTest() {
    digitalWallet.deposit(0, 5);
    Mockito.verify(mockAccount1).deposit(5);
  }

  @Test
  void withdrawTest() {
    when(mockAccount1.withdraw(5)).thenReturn(true);
    boolean result = digitalWallet.withdraw(0, 5);
    assertTrue(result);
  }

  @Test
  void transferTest() {
    when(mockAccount1.transfer(mockAccount2, 5)).thenReturn(true);
    boolean result = digitalWallet.transfer(0, 1, 5);
    assertTrue(result);
  }

  @Test
  void getAccountsTest() {
    Collection<Account> accounts = digitalWallet.getAccounts();
    assertEquals(2, accounts.size());
  }

  @Test
  void getAccountTest() {
    Account account = digitalWallet.getAccount(0);
    assertEquals(mockAccount1, account);
  }

}
