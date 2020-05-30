package com.n2.trees;

import static com.n2.trees.Side.BUY;
import static com.n2.trees.Side.SELL;
import static java.lang.Math.min;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.Getter;

@Getter
public class AggregatedDepth {

  protected Map<Integer, AggregatedQuantity> buyAggregatedQuantity; //price vs quantity
  protected Map<Integer, AggregatedQuantity> sellAggregatedQuantity;//price vs quantity

  protected AggregatedDepth() {
    this.buyAggregatedQuantity = new TreeMap<>(reverseOrder());
    this.sellAggregatedQuantity = new TreeMap<>(naturalOrder());
  }

  void add(Side side, long orderid, int quantity, int price) {
    if (side == BUY) {
      createNewEntryOrUpdateExistingQuantity(orderid, quantity, price, this.buyAggregatedQuantity);
    } else if (side == SELL) {
      createNewEntryOrUpdateExistingQuantity(orderid, quantity, price, this.sellAggregatedQuantity);
    }
  }

  private void createNewEntryOrUpdateExistingQuantity(long orderid, int quantity, int price,
      Map<Integer, AggregatedQuantity> buyAggregatedQuantity) {
    AggregatedQuantity aggregatedQuantity = buyAggregatedQuantity.get(price);
    if (aggregatedQuantity == null) {
      //if the price does not exist, create a new price entry, create a new AggregatedQuantity and add to Map
      createNewEntryForPrice(buyAggregatedQuantity, orderid, quantity, price);
    } else {
      //if it does, add to existing AggregatedQuantity
      aggregatedQuantity.addOrderQuantity(orderid, quantity);
    }
  }

  private void createNewEntryForPrice(Map<Integer, AggregatedQuantity> aggregatedQuantityMap,
      long orderid, int quantity, int price) {
    AggregatedQuantity newAggregatedQuantity = new AggregatedQuantity();
    newAggregatedQuantity.addOrderQuantity(orderid, quantity);
    aggregatedQuantityMap.put(price, newAggregatedQuantity);
  }

  void modify(Side side, long orderid, int oldPrice, int newQuantity, int newPrice) {
    remove(side, orderid, oldPrice);
    add(side, orderid, newQuantity, newPrice);
  }

  void remove(Side side, long orderid, int price) {
    if (side == BUY) {
      removeAndCleanupAggregatedQuantity(this.buyAggregatedQuantity, orderid, price);
    } else if (side == SELL) {
      removeAndCleanupAggregatedQuantity(this.sellAggregatedQuantity, orderid, price);
    }
  }

  private void removeAndCleanupAggregatedQuantity(
      Map<Integer, AggregatedQuantity> aggregatedQuantityMap, long orderid, int price) {
    final AggregatedQuantity oldAggregatedQuantity = aggregatedQuantityMap.get(price);
    oldAggregatedQuantity.removeOrder(orderid);
    if (oldAggregatedQuantity.isEmpty()) {
      aggregatedQuantityMap.remove(price);
    }
  }

  public double getCurrentPrice(int quantity, Side side) {
    if (side == SELL) {
      return getCurrentPrice(this.sellAggregatedQuantity
          .entrySet().iterator(), quantity);
    } else if (side == BUY) {
      return getCurrentPrice(this.buyAggregatedQuantity
          .entrySet().iterator(), quantity);
    } else {
      return OrderHandler.ERROR_CODE;
    }
  }

  private double getCurrentPrice(Iterator<Entry<Integer, AggregatedQuantity>> levelIterator,
      int requiredQuantity) {
    int availableQuantityAtThisPrice = 0;
    double totalPrice = 0.0;
    while (levelIterator.hasNext()) {
      final Entry<Integer, AggregatedQuantity> priceVsAggQtyEntryAtThisLevel = levelIterator.next();
      final Integer availablePriceAtThisLevel = priceVsAggQtyEntryAtThisLevel.getKey();
      final AggregatedQuantity aggQtyAtThisLevel = priceVsAggQtyEntryAtThisLevel.getValue();
      int balanceRequired = requiredQuantity - availableQuantityAtThisPrice;
      if (balanceRequired > 0) {
        //if aggQtyAtThisLevel has the required balance, take as much to availableQuantityAtThisPrice and accumulate price
        int takeMaxQtyAtThisPriceLevel = min(aggQtyAtThisLevel.getAggregatedQuantity(),
            balanceRequired);
        totalPrice = totalPrice + (takeMaxQtyAtThisPriceLevel * availablePriceAtThisLevel);
        availableQuantityAtThisPrice = availableQuantityAtThisPrice + takeMaxQtyAtThisPriceLevel;
      } else {
        break;
      }
    }
    if (availableQuantityAtThisPrice == requiredQuantity) {
      return BigDecimal.valueOf(totalPrice / availableQuantityAtThisPrice)
          .setScale(3, HALF_UP)
          .doubleValue();
    } else {
      return OrderHandler.ERROR_CODE;
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
