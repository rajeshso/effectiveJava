package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * You are given a list of all the transactions on a bank account during the year 2020
 * The account was empty at the beginning of the year( the balance was 0)
 *
 * Each transaction specifies the amount and the date of the transaction.
 * If the amount is negative, then it was a card payment, otherwise it was an incoming transfer(amount atleast 0)
 * The date of each transaction is in the format "YYYY-MM-DD": for example, "2020-01-01" represents January 1, 2020.
 *
 * Additionally, there is a fee for having a card (omitted in the given transaction list), which is 5 per month.
 * This fee is deducted from the account balance at the end of each month unless there were atleast three payments made by card for a total cost of atleast 100 within a month.
 *
 * Your task is to calculate the final balance of the account at the end of the year 2020.
 */
public class TestCiti {
  public int solution(int[] A, String[] D) {
    Map<String, List<Integer>> monthlyCardPayments = new HashMap<>();
    int balance = 0;

    //Calculate the balance and store the card payments for each month
    for (int i = 0; i < A.length; i++) {
      String date = D[i];
      int amount = A[i];
      balance += amount;//Calculate the balance

      String yearMonth = date.substring(0, 7);//"2020-12-31" becomes "2020-12"

      if (amount < 0) { //Card Payment only
        monthlyCardPayments.putIfAbsent(yearMonth, new ArrayList<>());
        monthlyCardPayments.get(yearMonth).add(amount);
      }
    }

    int cardFee = 0;
    for (String yearMonth : monthlyCardPayments.keySet()) {
      List<Integer> cardPaymentsForAMonth = monthlyCardPayments.get(yearMonth);
      int count = cardPaymentsForAMonth.size();
      int totalAmount = cardPaymentsForAMonth.stream().mapToInt(Integer::intValue).sum();

      if (count < 3 || totalAmount > -100) {//If there were atleast three payments made by card for a total cost of atleast 100 within a month
        cardFee += 5;
      }
    }

    int totalMonths = 12;
    Set<String> processedMonths = monthlyCardPayments.keySet();
    cardFee += (totalMonths - processedMonths.size()) * 5;

    return balance - cardFee;
  }
  //Total income is 100 + 100 + 100 - 10 = 290. The fee of 60 is applied for the month of December 2020, so the final income is 290 - 60 = 230.
  @Test
  public void testSimple1() {
    int[] A = {100, 100, 100, -10};
    String[] D = {"2020-12-31", "2020-12-22", "2020-12-03", "2020-12-29"};
    int actual = solution(A, D);
    System.out.println(actual);
    assertThat(actual).isEqualTo(230);
  }
  //The income was 180 in January 2020, -50 in January 2020, -25 in January 2020, and -25 in January 2022. The total income is 180 - 50 - 25 - 25 = 80. The fee of 5 is applied for the month of January 2020, so the final income is 80 - 5 = 75.
  @Test
  public void testSimple2() {
    int[] A = {180, -50, -25, -25};
    String[] D = {"2020-01-01", "2020-01-01", "2020-01-01", "2020-01-31"};
    int actual = solution(A, D);
    assertThat(actual).isEqualTo(25);
  }
  //The fee is paid every month. 1-1+0-105+1 -(5*12) = -164. Note that in April, even though the total cost of card payments is 106(more than 100), there were only two payments made by card , so the fee was still applied. A transaction of value 0 is considered a positive, incoming transfer
  @Test
  public void testSimple3() {
    int[] A = {1, -1, 0, -105,1};
    String[] D = {"2020-12-31", "2020-04-04", "2020-04-04","2020-04-14", "2020-07-12"};
    int actual = solution(A, D);
    assertThat(actual).isEqualTo(-164);
  }
  @Test
  public void testSimple4() {
    int[] A = {100,100, -10, -20,-30};
    String[] D = {"2020-01-01", "2020-02-01", "2020-02-11", "2020-02-05", "2020-02-08"};
    int actual = solution(A, D);
    assertThat(actual).isEqualTo(80);
  }
  @Test
  public void testSimple5() {
    int[] A = {-60,60, -40, -20};
    String[] D = {"2020-10-01", "2020-02-02","2020-10-10", "2020-10-30"};
    int actual = solution(A, D);
    assertThat(actual).isEqualTo(-115);
  }
  @Test
  public void testSimple6() {
    int[] A = {100, 200, 150, 100, 200};
    String[] D = {"2022-01-01", "2022-01-01", "2022-02-01", "2022-02-01", "2022-02-01"};
    int actual = solution(A, D);
    System.out.println(actual);
  }
}
