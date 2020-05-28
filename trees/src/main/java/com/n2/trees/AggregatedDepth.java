package com.n2.trees;

import java.util.Map;

public class AggregatedDepth {
  private int priceLevel;
  private AggregatedQuantity buyAggregatedQuantity;
  private AggregatedQuantity sellAggregatedQuantity;
  private Map<Side, AggregatedQuantity> aggregatedQuantityMap;

  AggregatedDepth(int priceLevel) {
    this.priceLevel = priceLevel;
    buyAggregatedQuantity = new AggregatedQuantity();
    sellAggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantityMap.put(Side.SELL, sellAggregatedQuantity);
    aggregatedQuantityMap.put(Side.BUY, buyAggregatedQuantity);
  }

  void add(Side side, int orderid, int quantity) {
    aggregatedQuantityMap.get(side).addOrder(orderid,quantity);
  }
  void remove(Side side, int orderid) {
    aggregatedQuantityMap.get(side).removeOrder(orderid);
  }
}
