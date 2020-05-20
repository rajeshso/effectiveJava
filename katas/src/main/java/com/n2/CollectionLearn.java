package com.n2;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CollectionLearn {

  List<Operation> operations = List.of(
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

  //https://dzone.com/articles/one-method-to-rule-them-all-mapmerge
  public static void compute() {
    //old way
    var words = List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
    var map = new HashMap<String, Integer>();
    words.forEach(word -> {
      var prev = map.get(word);
      if (prev == null) {
        map.put(word, 1);
      } else {
        map.put(word, prev + 1);
      }
    });
    System.out.println(map);
    //new way 1
    map.clear();
    words.forEach(word -> {
      map.putIfAbsent(word, 0);
      map.put(word, map.get(word) + 1);
    });
    System.out.println(map);
    //new way 2
    map.clear();
    words.forEach(word -> {
      map.putIfAbsent(word, 0);
      map.computeIfPresent(word, (w, count) -> count + 1);
    });
    System.out.println(map);
    //new way 3
    map.clear();
    words.forEach(word -> {
      map.compute(word, (w, count) -> count == null ? 0 : count + 1);
    });
    System.out.println(map);
    //new way 4
    map.clear();
    words.forEach(word -> {
      map.merge(word, 1, (previousCount, one) -> previousCount + 1);
    });
    System.out.println(map);
  }

  public static void main(String[] args) {
    compute();
    new CollectionLearn().computeBalance();
  }

  public void computeIfAbsent() {
    Map<String, Integer> stringLength = new HashMap<>();
    //stringLength.put("John", 5);
    final Integer johnLen = stringLength.computeIfAbsent("John", s -> s.length());
    System.out.printf("\n johnLen %d", johnLen);
  }

  private void computeBalance() {
    var balances = new HashMap<String, BigDecimal>(10);
    //slightly older way
    operations.forEach(operation -> {
      final String key = operation.accountNumber;
      final BigDecimal value = operation.amount;
      balances.putIfAbsent(key, ZERO);
      balances.computeIfPresent(key, (acc, bal) -> bal.add(value));
    });
    System.out.println(balances);
    //new way 2
    balances.clear();
    operations.forEach(operation -> {
      final String key = operation.accountNumber;
      final BigDecimal value = operation.amount;
      balances.merge(key, value, (count, newValue) -> count.add(newValue));
    });
    System.out.println(balances);
    //new way 3
    balances.clear();
    operations.forEach(operation -> {
      final String key = operation.accountNumber;
      final BigDecimal value = operation.amount;
      balances.merge(key, value, BigDecimal::add);
    });
    System.out.println(balances);
  }

  @AllArgsConstructor
  @Getter
  private class Operation {

    private final String accountNumber;
    private final BigDecimal amount;
  }
}
