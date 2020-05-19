package com.n2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://www.nurkiewicz.com/2019/03/mapmerge-one-method-to-rule-them-all.html
public class PreferMethodReferencesToLambdas {
  private List<String> words = List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
  private List<Operation> operations = List.of(
      new Operation("123", new BigDecimal("10")),
      new Operation("456", new BigDecimal("1200")),
      new Operation("123", new BigDecimal("-4")),
      new Operation("123", new BigDecimal("8")),
      new Operation("456", new BigDecimal("800")),
      new Operation("456", new BigDecimal("-1500")),
      new Operation("123", new BigDecimal("2")),
      new Operation("123", new BigDecimal("-6.5")),
      new Operation("456", new BigDecimal("-600"))
  );

  public static void main(String[] args) {
    PreferMethodReferencesToLambdas test = new PreferMethodReferencesToLambdas();
    System.out.println(test.countWordOccurenceOldWay());
    System.out.println(test.countWordOccurenceOldWayRefactored1());
    System.out.println(test.countWordOccurenceOldWayRefactored2());
    System.out.println(test.countWordOccurenceOldWayRefactored3());
    System.out.println(test.computeBalance());
    System.out.println(test.computeBalanceRefactored1());
  }
  Map<String, Integer> countWordOccurenceOldWay() {
    Map<String, Integer> map = new HashMap();
    words.forEach(
        word -> {
          var prev = map.get(word);
          if (prev == null) {
            map.put(word,1);
          }else {
            map.put(word, prev+1);
          }
        }
    );
    return map;
  }
  Map<String, Integer> countWordOccurenceOldWayRefactored1() {
    Map<String, Integer> map = new HashMap();
    words.forEach(
        word -> {
          map.putIfAbsent(word,0);
          map.put(word, map.get(word)+1);
        }
    );
    return map;
  }
  Map<String, Integer> countWordOccurenceOldWayRefactored2() {
    Map<String, Integer> map = new HashMap();
    words.forEach(
        word -> {
          map.putIfAbsent(word,0);
          //map.put(word, map.get(word)+1);
          map.computeIfPresent(word, (w, previousCount)->previousCount+1);
        }
    );
    return map;
  }
  Map<String, Integer> countWordOccurenceOldWayRefactored3() {
    Map<String, Integer> map = new HashMap();
    words.forEach(
        word -> {
          //map.put(word, map.get(word)+1);
          //map.computeIfPresent(word, (w, previousCount)->previousCount+1);
          map.compute(word, (w, previousCount)-> previousCount!=null ? previousCount+1: 1);
        }
    );
    return map;
  }

  class Operation {
      String accNo;
      BigDecimal amount;

    public Operation(String s, BigDecimal bigDecimal) {
      this.accNo = s;
      this.amount = bigDecimal;
    }
  }
  Map<String, BigDecimal> computeBalance() {
    var balances = new HashMap<String, BigDecimal>(10);
    operations.forEach(
        (op) -> {
          var key = op.accNo;
          balances.putIfAbsent(key, BigDecimal.ZERO);
          balances.computeIfPresent(key, (acc, bal) -> bal.add(op.amount));
        }
    );
    return balances;
  }
  Map<String, BigDecimal> computeBalanceRefactored1() {
    var balances = new HashMap<String, BigDecimal>(10);
    operations.forEach(
        (op) -> {
          var key = op.accNo;
          var value = op.amount;
/*        balances.putIfAbsent(key, null);
          balances.computeIfPresent(key, (acc, bal) -> bal.add(op.amount));*/
          balances.merge(op.accNo, op.amount, (accumulatedAmount, amount )-> accumulatedAmount.add(amount));
        }
    );
    return balances;
  }// 900 chf
   // 850 chf
   // kafka streams | germany
}
