package com.n2.trees;

import static com.n2.trees.Side.BUY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.junit.jupiter.api.Test;

public class TestInMemoryOrderHandler {

  @Test
  public void addNewOrder_shouldAddInOrderBookAndAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order = new Order(1, "MSFT", BUY, 10, 5);
    inMemoryOrderHandler.addOrder(order);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, order));
  }

  @Test
  public void addTwoOrderWithSameSymbol_shouldTwoEntriesInOrderBookAndOneAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order1 = new Order(1, "MSFT", BUY, 10, 5);
    Order order2 = new Order(2, "MSFT", BUY, 25, 15);

    inMemoryOrderHandler.addOrder(order1);
    inMemoryOrderHandler.addOrder(order2);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, order1), entry(2L, order2));
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook.keySet()).containsExactly("MSFT");
  }

  @Test
  public void addTwoOrderWithTwoSymbols_shouldTwoEntriesInOrderBookAndTwoInAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order1 = new Order(1, "MSFT", BUY, 10, 5);
    Order order2 = new Order(2, "APPL", BUY, 25, 15);

    inMemoryOrderHandler.addOrder(order1);
    inMemoryOrderHandler.addOrder(order2);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, order1), entry(2L, order2));
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook.keySet())
        .containsExactly("MSFT", "APPL");
  }

  @Test
  public void modifyOrder_shouldUpdateOrderBookAndAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order = new Order(1, "MSFT", BUY, 10, 5);
    OrderModification orderModification = new OrderModification(1, 11, 6);
    inMemoryOrderHandler.addOrder(order);
    inMemoryOrderHandler.modifyOrder(orderModification);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, new Order(1, "MSFT", BUY, 11, 6)));
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook).hasSize(1);
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook.get("MSFT").buyAggregatedQuantity
        .get(11).aggregatedQuantity).isEqualTo(6);
    assertThat(
        inMemoryOrderHandler.aggregatedOrderLevelBook.get("MSFT").buyAggregatedQuantity.get(10))
        .isNull();
  }

  @Test
  public void removeOrder_shouldUpdateOrderBookAndAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order = new Order(1, "MSFT", BUY, 10, 5);
    inMemoryOrderHandler.addOrder(order);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, new Order(1, "MSFT", BUY, 10, 5)));
    inMemoryOrderHandler.removeOrder(1);
    assertThat(inMemoryOrderHandler.orderBook.entrySet()).isEmpty();
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook).isEmpty();
  }

  @Test
  public void giveTwoOrdersOfSameSymbol_removeOneOrder_shouldUpdateOrderBookAndAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order1 = new Order(1, "MSFT", BUY, 10, 5);
    Order order2 = new Order(2, "MSFT", BUY, 10, 15);
    inMemoryOrderHandler.addOrder(order1);
    inMemoryOrderHandler.addOrder(order2);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, new Order(1, "MSFT", BUY, 10, 5)),
            entry(2L, new Order(2, "MSFT", BUY, 10, 15)));
    inMemoryOrderHandler.removeOrder(1);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(2L, new Order(2, "MSFT", BUY, 10, 15)));
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook).hasSize(1);
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook).containsKey("MSFT");
  }

  @Test
  public void giveTwoOrdersOfDifferentSymbol_removeOneOrder_shouldUpdateOrderBookAndAggregate() {
    InMemoryOrderHandler inMemoryOrderHandler = new InMemoryOrderHandler();
    Order order1 = new Order(1, "MSFT", BUY, 10, 5);
    Order order2 = new Order(2, "APPL", BUY, 10, 15);
    inMemoryOrderHandler.addOrder(order1);
    inMemoryOrderHandler.addOrder(order2);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(1L, new Order(1, "MSFT", BUY, 10, 5)),
            entry(2L, new Order(2, "APPL", BUY, 10, 15)));
    inMemoryOrderHandler.removeOrder(1);
    assertThat(inMemoryOrderHandler.orderBook.entrySet())
        .containsExactly(entry(2L, new Order(2, "APPL", BUY, 10, 15)));
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook).hasSize(1);
    assertThat(inMemoryOrderHandler.aggregatedOrderLevelBook).containsKey("APPL");
  }

}
