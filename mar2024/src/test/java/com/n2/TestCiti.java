package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  public int solution1(int[] A, String[] D) {
    Map<String, List<Integer>> monthlyCardPayments = new HashMap<>();
    int balance = 0;
    int numberofcardPayments = 0;
    int totalCardPaymentAmount = 0;
    //Calculate the balance and store the card payments for each month
    for (int i = 0; i < A.length; i++) {
      String date = D[i].substring(0, 7);//"2020-12-31" becomes "2020-12"
      if (A[i] < 0) { //Card Payment only
        monthlyCardPayments.putIfAbsent(date, new ArrayList<>());
        monthlyCardPayments.get(date).add(A[i]);
      }
      balance += A[i];
    }
    final Collection<List<Integer>> monthlyTx = monthlyCardPayments.values();
    for (List<Integer> txList : monthlyTx) {
      numberofcardPayments = txList.size();
      totalCardPaymentAmount = txList.stream().mapToInt(Integer::intValue).sum();
      if (numberofcardPayments >= 3 && totalCardPaymentAmount <= -100) {
        //No card fee for the month
      } else {
        //Card fee for the month
        balance -= 5;
      }
    }
    return balance;
  }


    static Stream<Arguments> testCases() {
    return Stream.of(
        Arguments.of(new int[]{100, 100, 100, -10}, new String[]{"2020-12-31", "2020-12-22", "2020-12-03", "2020-12-29"}, 230),
        Arguments.of(new int[]{180, -50, -25, -25}, new String[]{"2020-01-01", "2020-01-01", "2020-01-01", "2020-01-31"}, 25),
        Arguments.of(new int[]{1, -1, 0, -105, 1}, new String[]{"2020-12-31", "2020-04-04", "2020-04-04", "2020-04-14", "2020-07-12"}, -164),
        Arguments.of(new int[]{100, 100, -10, -20, -30}, new String[]{"2020-01-01", "2020-02-01", "2020-02-11", "2020-02-05", "2020-02-08"}, 80),
        Arguments.of(new int[]{-60, 60, -40, -20}, new String[]{"2020-10-01", "2020-02-02", "2020-10-10", "2020-10-30"}, -115)
    );
  }

  @ParameterizedTest
  @MethodSource("testCases")
  public void testSolution(int[] amounts, String[] dates, int expected) {
    //assertThat(solution(amounts, dates)).isEqualTo(expected);
    assertThat(solution1(amounts, dates)).isEqualTo(expected);
  }
}
