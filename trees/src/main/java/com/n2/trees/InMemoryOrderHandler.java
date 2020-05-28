package com.n2.trees;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderHandler implements OrderHandler {

  final Map<Long, Order> orderBook = new HashMap<>();//ID vs Order
  final Map<String, AggregatedDepth> aggregatedOrderLevelBook = new HashMap<>();//Symbol vs Book

  @Override
  public void addOrder(Order order) {
    final String symbol = order.getSymbol();
    orderBook.put(order.getOrderId(), order);
    if (aggregatedOrderLevelBook.containsKey(symbol)) {
      AggregatedDepth aggregatedDepth = aggregatedOrderLevelBook.get(symbol);
      aggregatedDepth
          .add(order.getSide(), order.getOrderId(), order.getQuantity(), order.getPrice());
    } else {
      AggregatedDepth aggregatedDepth = new AggregatedDepth();
      aggregatedDepth
          .add(order.getSide(), order.getOrderId(), order.getQuantity(), order.getPrice());
      aggregatedOrderLevelBook.put(symbol, aggregatedDepth);
    }
  }

  @Override
  public void modifyOrder(OrderModification orderModification) {
    final long orderId = orderModification.getOrderId();
    if (!orderBook.containsKey(orderId)) {
      return;
    }
    final Order existingOrder = orderBook.get(orderId);
    final int oldPrice = existingOrder.getPrice();
    final Side side = existingOrder.getSide();
    final String symbol = existingOrder.getSymbol();
    final int newPrice = orderModification.getNewPrice();
    final int newQuantity = orderModification.getNewQuantity();
    final Order newOrder = new Order(orderId, symbol, side, newPrice, newQuantity);
    orderBook.put(orderId, newOrder);

    modifyAggregateLevelBook(orderId, oldPrice, side, symbol, newPrice, newQuantity);

  }

  private void modifyAggregateLevelBook(long orderId, int oldPrice, Side side, String symbol,
      int newPrice, int newQuantity) {
    final AggregatedDepth aggregatedDepth = aggregatedOrderLevelBook.get(symbol);
    aggregatedDepth.modify(side, orderId, oldPrice, newQuantity, newPrice);
  }

  @Override
  public void removeOrder(long orderId) {
    if (!orderBook.containsKey(orderId)) {
      return;
    }
    Order existingOrder = orderBook.get(orderId);
    final Side side = existingOrder.getSide();
    final String symbol = existingOrder.getSymbol();
    final int price = existingOrder.getPrice();
    orderBook.remove(orderId);

    cleanupAggregatedLevelBook(orderId, side, symbol, price);
  }

  private void cleanupAggregatedLevelBook(long orderId, Side side, String symbol, int price) {
    final AggregatedDepth aggregatedDepth = aggregatedOrderLevelBook.get(symbol);
    aggregatedDepth.remove(side, orderId, price);
    if (aggregatedDepth.isEmpty()) {
      aggregatedOrderLevelBook.remove(symbol);
    }
  }

  @Override
  public double getCurrentPrice(String symbol, int quantity, Side side) {
    return 0;
  }

}
