package com.n2.trees;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.junit.jupiter.api.Test;

public class TestAggregateQuantity {

  @Test
  public void addOrderQuantityOf1Unit_should_add1Unit() {
    AggregatedQuantity aggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantity.addOrderQuantity(1, 2);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(2);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap)
        .containsOnly(entry(1L, 2));
  }

  @Test
  public void addOrderQuantityOf2Unit_should_add2Unit() {
    AggregatedQuantity aggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantity.addOrderQuantity(1, 2);
    aggregatedQuantity.addOrderQuantity(4, 3);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(5);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 2),
        entry(4L, 3)
    );
  }

  @Test
  public void givenAnOrderWith2Qty_whenModifyWith3Qty_shouldModifyOrderAndAggregation() {
    AggregatedQuantity aggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantity.addOrderQuantity(1, 2);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(2);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 2));
    aggregatedQuantity.modifyOrderQuantity(1, 10);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(10);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 10));
  }

  @Test
  public void givenTwoOrders_whenOnly1IsModified_shouldModifyOnlyOneOrderAndAggregation() {
    AggregatedQuantity aggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantity.addOrderQuantity(1, 10);
    aggregatedQuantity.addOrderQuantity(2, 12);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(22);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 10), entry(2L, 12));
    aggregatedQuantity.modifyOrderQuantity(1, 100);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(112);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 100), entry(2L, 12));
  }

  @Test
  public void givenOneOrder_whenOneOrderIsRemoved_shouldRemoveOrderAndUpdateAggregation() {
    AggregatedQuantity aggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantity.addOrderQuantity(1, 2);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(2);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 2));
    aggregatedQuantity.removeOrder(1);
    assertThat(aggregatedQuantity.aggregatedQuantity).isZero();
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).isEmpty();
  }

  @Test
  public void givenTwoOrders_whenOneOrderIsRemoved_shouldRemoveOrderAndUpdateAggregation() {
    AggregatedQuantity aggregatedQuantity = new AggregatedQuantity();
    aggregatedQuantity.addOrderQuantity(1, 2);
    aggregatedQuantity.addOrderQuantity(3, 5);
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(7);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(1L, 2), entry(3L, 5));
    aggregatedQuantity.removeOrder(1);
    assertThat(aggregatedQuantity.orderIdVsQuantityMap).containsOnly(entry(3L, 5));
    assertThat(aggregatedQuantity.aggregatedQuantity).isEqualTo(5);
  }

}
