package com.n2.trees;

import static com.n2.trees.OrderHandler.ERROR_CODE;
import static com.n2.trees.Side.BUY;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGetCurrentPriceForBuy {

  private OrderHandler orderHandler = null;

  @BeforeEach
  public void setUp() {
    orderHandler = OrderHandler.createInstance();
    ExampleData.buildExampleOrderBookFromReadMe(orderHandler);
  }

  @Test
  public void testGetCurrentPriceForQuantityLesserThanFirstLevel() {
    final double price = orderHandler.getCurrentPrice("MSFT", 6, BUY);
    assertThat(price).isEqualTo(15.000);
  }

  @Test
  public void testGetCurrentPriceForQuantityEqualToFirstLevel() {
    final double price = orderHandler.getCurrentPrice("MSFT", 10, BUY);
    assertThat(price).isEqualTo(15.000);
  }

  @Test
  public void testGetCurrentPriceForQuantityLessThanSecondLevel() {
    final double price = orderHandler.getCurrentPrice("MSFT", 20, BUY);
    assertThat(price).isEqualTo(12.500);
  }

  @Test
  public void testGetCurrentPriceForQuantityForAllLevels() {
    final double price = orderHandler.getCurrentPrice("MSFT", 36, BUY);
    assertThat(price).isEqualTo(11.389);
  }

  @Test
  public void testGetCurrentPriceForUnavailableQuantity() {
    final double price = orderHandler.getCurrentPrice("MSFT", 1000, BUY);
    assertThat(price).isEqualTo(ERROR_CODE);
  }

  @Test
  public void testGetCurrentPriceForUnavailableSymbol() {
    final double price = orderHandler.getCurrentPrice("APPL", 1000, BUY);
    assertThat(price).isEqualTo(ERROR_CODE);
  }
}
