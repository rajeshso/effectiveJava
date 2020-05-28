package com.n2.trees;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderHandler implements OrderHandler {
  private final Map<Long, Order> orders = new HashMap<>();//ID vs Order
  private final Map<String, AggregatedDepth> orderBooks = new HashMap<>();//Symbol vs Book

  @Override
  public void addOrder(Order order) {

  }

  @Override
  public void modifyOrder(OrderModification orderModification) {

  }

  @Override
  public void removeOrder(long orderId) {

  }

  @Override
  public double getCurrentPrice(String symbol, int quantity, Side side) {
    return 0;
  }


}
