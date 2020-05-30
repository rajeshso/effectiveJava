package com.n2.trees;

import static com.n2.trees.Side.BUY;
import static com.n2.trees.Side.SELL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestAggregatedDepth {

  @Test
  public void addSellWithOrder_shouldAddDataWithSellAggregatedQuantity() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 12, 24);
    assertThat(aggregatedDepth.sellAggregatedQuantity).hasSize(1);
    assertThat(aggregatedDepth.sellAggregatedQuantity.keySet()).containsOnly(24);
    assertThat(aggregatedDepth.buyAggregatedQuantity).isEmpty();
  }

  @Test
  public void addBuyWithOrder_shouldAddDataWithBuyAggregatedQuantity() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(BUY, 1, 12, 24);
    assertThat(aggregatedDepth.sellAggregatedQuantity).isEmpty();
    assertThat(aggregatedDepth.buyAggregatedQuantity).hasSize(1);
    assertThat(aggregatedDepth.buyAggregatedQuantity.keySet()).containsOnly(24);
  }

  @Test
  public void addTwoBuyOrdersWithSamePrice_shouldStoreInTheSameEntry() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(BUY, 1, 12, 24);
    aggregatedDepth.add(BUY, 2, 14, 24);
    assertThat(aggregatedDepth.buyAggregatedQuantity).hasSize(1);
    assertThat(aggregatedDepth.buyAggregatedQuantity.keySet()).containsOnly(24);
  }

  @Test
  public void addTwoBuyOrdersWithSamePriceAndOneWithDifferentPrice_shouldStoreInTwoEntries() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(BUY, 1, 12, 24);
    aggregatedDepth.add(BUY, 2, 14, 24);
    aggregatedDepth.add(BUY, 3, 15, 25);

    assertThat(aggregatedDepth.buyAggregatedQuantity).hasSize(2);
    assertThat(aggregatedDepth.buyAggregatedQuantity.keySet()).containsOnly(24,25);
  }

  @Test
  public void addThreeBuyOrdersWithDifferentPrice_shouldSortHighToLess() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(BUY, 1, 12, 23);
    aggregatedDepth.add(BUY, 2, 14, 20);
    aggregatedDepth.add(BUY, 3, 15, 22);
    assertThat(aggregatedDepth.buyAggregatedQuantity).hasSize(3);
    assertThat(aggregatedDepth.buyAggregatedQuantity.keySet()).containsSequence(23,22,20);
  }

  @Test
  public void addThreeSellOrdersWithDifferentPrices_shouldSortLessToHigh() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 12, 23);
    aggregatedDepth.add(SELL, 2, 14, 20);
    aggregatedDepth.add(SELL, 3, 15, 22);
    assertThat(aggregatedDepth.sellAggregatedQuantity).hasSize(3);
    assertThat(aggregatedDepth.sellAggregatedQuantity.keySet()).containsSequence(20,22,23);
  }

  @Test
  public void removeSellWithOrder_shouldRemoveOrder() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 12, 24);
    aggregatedDepth.remove(SELL,1,24);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(24)).isNull();
  }

  @Test
  public void givenTwoSellOrders_whenRemoveSellWithOrder_shouldRemoveOnlyOneOrder() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 12, 24);
    aggregatedDepth.add(SELL, 2, 10, 24);

    aggregatedDepth.remove(SELL,1,24);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(24).eachOrder).hasSize(1);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(24).aggregatedQuantity).isEqualTo(10);
  }

  @Test
  public void modifySellWithOrder_shouldModifyQuantity() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 12, 24);
    aggregatedDepth.modify(SELL,1,24,10,24);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(24).eachOrder).hasSize(1);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(24).aggregatedQuantity).isEqualTo(10);
  }

  @Test
  public void modifySellWithOrder_shouldModifyPrice() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 12, 24);
    aggregatedDepth.modify(SELL,1,24,12,20);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(24)).isNull();
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(20)).isNotNull();
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(20).aggregatedQuantity).isEqualTo(12);
  }

  @Test
  public void givenThreeSellOrdersWithThreePrices_whenOneOrderIsModifiedWithAnExistingPrice_shouldRebalance() {
    AggregatedDepth aggregatedDepth = new AggregatedDepth();
    aggregatedDepth.add(SELL, 1, 10, 20);
    aggregatedDepth.add(SELL, 2, 11, 21);
    aggregatedDepth.add(SELL, 3, 12, 22);

    aggregatedDepth.modify(SELL,1,20,10,21);
    assertThat(aggregatedDepth.sellAggregatedQuantity).hasSize(2);
    assertThat(aggregatedDepth.sellAggregatedQuantity.keySet()).containsSequence(21,22);
    assertThat(aggregatedDepth.sellAggregatedQuantity.get(21).aggregatedQuantity).isEqualTo(21);
  }
}
