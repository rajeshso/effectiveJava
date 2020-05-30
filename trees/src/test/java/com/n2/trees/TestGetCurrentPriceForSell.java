package com.n2.trees;

import static com.n2.trees.Side.SELL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGetCurrentPriceForSell {

  private OrderHandler orderHandler = null;

  @BeforeEach
  public void setUp() {
    orderHandler = OrderHandler.createInstance();
    ExampleData.buildExampleOrderBookFromReadMe(orderHandler);
  }

  @Test
  public void testGetCurrentPriceForQuantityLesserThanFirstLevel() {
    final double price = orderHandler.getCurrentPrice("MSFT", 6, SELL);
    assertThat(price).isEqualTo(19);
  }

  @Test
  public void testGetCurrentPriceForQuantityLesserThanSecondLevel() {
    final double price = orderHandler.getCurrentPrice("MSFT", 17, SELL);
    assertThat(price).isEqualTo(19.588);
  }

  @Test
  public void testGetCurrentPriceForQuantityUptoThirdLevel() {
    final double price = orderHandler.getCurrentPrice("MSFT", 30, SELL);
    assertThat(price).isEqualTo(20.233);
  }

  @Test
  public void testGetCurrentPriceForUnavailableQuantity() {
    final double price = orderHandler.getCurrentPrice("MSFT", 1000, SELL);
    assertThat(price).isEqualTo(-1.00);
  }

  @Test
  public void testGetCurrentPriceForUnavailableSymbol() {
    final double price = orderHandler.getCurrentPrice("APPL", 1000, SELL);
    assertThat(price).isEqualTo(-1.00);
  }
}
