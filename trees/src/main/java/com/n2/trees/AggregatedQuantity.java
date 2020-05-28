package com.n2.trees;

import java.util.HashMap;
import java.util.Map;

public class AggregatedQuantity {

  private Map<Integer, Integer> eachOrder = new HashMap<>();//orderid, quantity
  private Integer aggregatedQuantity  = 0;

  void addOrder(int orderid, int quantity)  {
    Integer oldQuantity = eachOrder.get(orderid);
    if (oldQuantity == null) {
      eachOrder.put(orderid, 0);
      oldQuantity = eachOrder.get(orderid);
    }
    eachOrder.put(orderid, oldQuantity + quantity);
    aggregatedQuantity+=oldQuantity;
  }
  void removeOrder(int orderid)  {
    Integer oldQuantity = eachOrder.get(orderid);
    if (oldQuantity != null) {
      aggregatedQuantity-=oldQuantity;
      eachOrder.remove(orderid);
    }
  }
}
