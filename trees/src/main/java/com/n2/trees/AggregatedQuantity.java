package com.n2.trees;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class AggregatedQuantity {

  protected Map<Long, Integer> orderIdVsQuantityMap = new HashMap<>();//orderid, quantity
  protected Integer aggregatedQuantity = 0;

  void addOrderQuantity(long orderid, int quantity) {
    orderIdVsQuantityMap.put(orderid, quantity);
    aggregatedQuantity = aggregatedQuantity + quantity;
  }

  void modifyOrderQuantity(long orderId, int quantity) {
    removeOrder(orderId);
    addOrderQuantity(orderId, quantity);
  }

  void removeOrder(long orderid) {
    final Integer oldQuantity = this.orderIdVsQuantityMap.get(orderid);
    if (oldQuantity != null) {
      aggregatedQuantity = aggregatedQuantity - oldQuantity;
      this.orderIdVsQuantityMap.remove(orderid);
    }
  }

  public boolean isEmpty() {
    return aggregatedQuantity == 0 && orderIdVsQuantityMap.isEmpty();
  }

  @Override
  public String toString() {
    return "AggregatedQuantity{" +
        "eachOrder=" + orderIdVsQuantityMap +
        ", aggregatedQuantity=" + aggregatedQuantity +
        '}';
  }
}
