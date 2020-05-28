package com.n2.trees;

import java.util.HashMap;
import java.util.Map;

public class AggregatedQuantity {

  Map<Long, Integer> eachOrder = new HashMap<>();//orderid, quantity
  Integer aggregatedQuantity  = 0;

  void addOrderQuantity(long orderid, int quantity)  {
    eachOrder.put(orderid, quantity);
    aggregatedQuantity= aggregatedQuantity+quantity;
  }
  void modifyOrderQuantity(long orderId, int quantity) {
    removeOrder(orderId);
    addOrderQuantity(orderId,quantity);
  }
  void removeOrder(long orderid)  {
    Integer oldQuantity = eachOrder.get(orderid);
    if (oldQuantity != null) {
      aggregatedQuantity=aggregatedQuantity-oldQuantity;
      eachOrder.remove(orderid);
    }
  }

  public boolean isEmpty() {
    return aggregatedQuantity == 0 && eachOrder.isEmpty();
  }

  //TODO: Refactor
  public Integer getQuantity(long orderId) {
    return eachOrder.get(orderId) == null ? 0 : eachOrder.get(orderId);
  }

  @Override
  public String toString() {
    return "AggregatedQuantity{" +
        "eachOrder=" + eachOrder +
        ", aggregatedQuantity=" + aggregatedQuantity +
        '}';
  }
}
