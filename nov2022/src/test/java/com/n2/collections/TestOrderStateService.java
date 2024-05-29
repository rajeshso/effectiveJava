package com.n2.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 *
 *    Problem: Given 3, return a collection of top 3 items
 *    if there are 20 orders of itemID 1 and 10 orders of item ID 2, 9 orders of item id 4 and 8 orders of item id 5, then the
 *    returned collection should be 1 20, 2 0, 4 9
 *    make o(1) for getTopOrders method, but addOrder can be o(n) or more
class OrderStateService {

 public void addOrder(String itemID) {
  }
 //return a collection of top n items
 public Collection<String> getTopOrders(int n) {
   return Collections.emptySet<String>();
 }
 }
**/

public class TestOrderStateService {

  private final Comparator<String> valueComparator = new Comparator<>() {
    @Override
    public int compare(String o1, String o2) {
      return orderMap.get(o2).compareTo(orderMap.get(o1));
    }
  };

  private final TreeMap<String, Integer> orderMap = new TreeMap<>();
  private final TreeMap<String, Integer> orderMapSorted = new TreeMap<>(valueComparator);

  public void addOrder(String itemID) {
    orderMap.compute(itemID,(key, value)-> (value==null)?1:value+1);
    orderMapSorted.clear();
    orderMapSorted.putAll(orderMap);
  }
  public Collection<String> getTopOrders(int n) {
    return orderMapSorted.keySet().stream().limit(n).toList();
  }
  
  @Test
  public void testOrderStateService() {
    //20 orders of itemID 1 and 10 orders of item ID 2, 9 orders of item id 4 and 8 orders of item id 5
    IntStream.rangeClosed(1,20).forEach(i->addOrder("10"));
    IntStream.rangeClosed(1,10).forEach(i->addOrder("2"));
    IntStream.rangeClosed(1,8).forEach(i->addOrder("5"));
    IntStream.rangeClosed(1,9).forEach(i->addOrder("4"));
    //returned collection should be 1, 2, 4
    Collection<String> result = getTopOrders(3);
    assertEquals(List.of("10","2","4"), result);
  }
}
