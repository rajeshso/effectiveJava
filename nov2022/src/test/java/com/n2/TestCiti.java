package com.n2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TestCiti {
  public int solutionBefore(int[] A, String[] D) {
    // Implement your solution here
    //sum all transactions
    int trx = 0;
    for(int i=0;i<A.length;i++) {
      trx+=A[i];
    }
    //Iterate through months
    Map<String, Integer> monthVsTotal = new HashMap();
    for(int i=0;i<D.length;i++) {
      String month = D[i].split("-")[1];
      monthVsTotal.put(month, monthVsTotal.getOrDefault(month,0)+A[i]);
    }
    System.out.println(monthVsTotal);
    //Find monthly discount
    //Count  card payments per month
    Map<String, Integer> monthVsTotalCardPayments = new HashMap();
    for(int i=0;i<D.length;i++) {
      String month = D[i].split("-")[1];
      if (A[i]<0) monthVsTotalCardPayments.put(month, monthVsTotalCardPayments.getOrDefault(month,0)+A[i]);
    }
    //Count the number of card payment per month
    Map<String, Integer> monthVsNoCardPayments = new HashMap();
    for(int i=0;i<D.length;i++) {
      String month = D[i].split("-")[1];
      if (A[i]<0) monthVsTotalCardPayments.put(month, monthVsTotalCardPayments.getOrDefault(month,0)+1);
    }

    int monthlyCardFeeDiscount = 0;
    for (int i=0;i<A.length;i++) {
      if (monthVsTotalCardPayments.get(i) > 100 && monthVsNoCardPayments.get(i)>3)         {
        //discount on monthlyCardFee
      }else {
        monthlyCardFeeDiscount+=5;
      }
    }
    int cardFee = (5*12)-monthlyCardFeeDiscount;
    int total = 0;
    for(int i=0;i<D.length;i++) {
      total+=monthVsTotalCardPayments.get(i);
    }
    return cardFee+total;
  }
  public int solution(int[] A, String[] D) {

    // Iterate through months and calculate total transactions per month
    Map<String, Integer> monthVsTotal = new HashMap<>();
    for (int i = 0; i < D.length; i++) {
      String month = D[i].split("-")[1];
      monthVsTotal.put(month, monthVsTotal.getOrDefault(month, 0) + A[i]);
    }
    System.out.println(monthVsTotal);

    // Count card payments per month
    Map<String, Integer> monthVsTotalCardPayments = new HashMap<>();
    for (int i = 0; i < D.length; i++) {
      String month = D[i].split("-")[1];
      if (A[i] < 0) {
        monthVsTotalCardPayments.put(month, monthVsTotalCardPayments.getOrDefault(month, 0) + A[i]);
      }
    }

    // Count the number of card payments per month
    Map<String, Integer> monthVsNoCardPayments = new HashMap<>();
    for (int i = 0; i < D.length; i++) {
      String month = D[i].split("-")[1];
      if (A[i] < 0) {
        monthVsNoCardPayments.put(month, monthVsNoCardPayments.getOrDefault(month, 0) + 1);
      }
    }

    int monthlyCardFeeDiscount = 0;
    for (String month : monthVsTotalCardPayments.keySet()) {
      if (Math.abs(monthVsTotalCardPayments.get(month)) >= Math.abs(100) && monthVsNoCardPayments.getOrDefault(month, 0) > 3) {
        monthlyCardFeeDiscount += 5;
      }
    }

    int cardFee = (5 * 12) - monthlyCardFeeDiscount;

    int total = 0;
    for (int i = 0; i < D.length; i++) {
      total += A[i];
    }

    return cardFee + total;
  }
  @Test
  public void testSimple1() {
    int[] A = {100, 100, -10, -20, -30};
    String[] D = {"2024-01-01", "2024-01-02", "2024-01-03", "2024-01-04", "2024-01-05"};
    int actual = solution(A, D);
    assertEquals(200, actual);
  }
}


