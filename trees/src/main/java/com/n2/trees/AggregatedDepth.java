package com.n2.trees;

import static com.n2.trees.Side.BUY;
import static com.n2.trees.Side.SELL;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

import java.util.Map;
import java.util.TreeMap;

public class AggregatedDepth {

  Map<Integer, AggregatedQuantity> buyAggregatedQuantity; //price vs quantity
  Map<Integer, AggregatedQuantity> sellAggregatedQuantity;//price vs quantity

  AggregatedDepth() {
    buyAggregatedQuantity = new TreeMap<>(reverseOrder());
    sellAggregatedQuantity = new TreeMap<>(naturalOrder());
  }

  void add(Side side, long orderid, int quantity, int price) {
    if (side == BUY) {
      createNewEntryOrUpdateExistingQuantity(orderid, quantity, price, buyAggregatedQuantity);
    } else if (side == SELL) {
      createNewEntryOrUpdateExistingQuantity(orderid, quantity, price, sellAggregatedQuantity);
    }
  }

  private void createNewEntryOrUpdateExistingQuantity(long orderid, int quantity, int price,
      Map<Integer, AggregatedQuantity> buyAggregatedQuantity) {
    //Check if the price already exists
    AggregatedQuantity aggregatedQuantity = buyAggregatedQuantity.get(price);
    if (aggregatedQuantity == null) {
      //if the price does not exist, create a new price entry, create a new AggregatedQuantity and add to Map
      createNewEntryForPrice(buyAggregatedQuantity, orderid, quantity, price);
    } else {
      //if it does, add to existing AggregatedQuantity
      aggregatedQuantity.addOrderQuantity(orderid, quantity);
    }
  }

  private void createNewEntryForPrice(Map<Integer, AggregatedQuantity> buyAggregatedQuantity,
      long orderid, int quantity, int price) {
    AggregatedQuantity newAggregatedQuantity = new AggregatedQuantity();
    newAggregatedQuantity.addOrderQuantity(orderid, quantity);
    buyAggregatedQuantity.put(price, newAggregatedQuantity);
  }

  void modify(Side side, long orderid, int oldPrice, int newQuantity, int newPrice) {
    remove(side, orderid, oldPrice);
    add(side, orderid, newQuantity, newPrice);
  }

  void remove(Side side, long orderid, int price) {
    if (side == BUY) {
      removeAndCleanupAggregatedQuantity(buyAggregatedQuantity, orderid, price);
    } else if (side == SELL) {
      removeAndCleanupAggregatedQuantity(sellAggregatedQuantity, orderid, price);
    }
  }

  private void removeAndCleanupAggregatedQuantity(
      Map<Integer, AggregatedQuantity> buyAggregatedQuantity, long orderid, int price) {
    final AggregatedQuantity oldAggregatedQuantity = buyAggregatedQuantity.get(price);
    oldAggregatedQuantity.removeOrder(orderid);
    if (oldAggregatedQuantity.isEmpty()) {
      buyAggregatedQuantity.remove(price);
    }
  }

  boolean isEmpty() {
    return buyAggregatedQuantity.isEmpty() && sellAggregatedQuantity.isEmpty();
  }

  @Override
  public String toString() {
    return "AggregatedDepth{" +
        "buyAggregatedQuantity=" + buyAggregatedQuantity +
        ", sellAggregatedQuantity=" + sellAggregatedQuantity +
        '}';
  }
}