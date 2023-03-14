package com.n2;

import static com.n2.TestSupermarketCheckout.ShopItem.APPLES;
import static com.n2.TestSupermarketCheckout.ShopItem.COFFEE;
import static com.n2.TestSupermarketCheckout.ShopItem.PEARS;
import static com.n2.TestSupermarketCheckout.ShopItem.TEA;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Supermarket checkout - calculate total after applying special offer rules
 *
 * Prices:
 * Apples   - £1.60
 * Pears    - £1.80
 * Tea      - £2.00
 * Coffee   - £2.50
 * Bread    - £1.20
 *
 * Special offer:
 * Apples and pears mix and match - buy one get the cheaper one half price
 * This means you can just buy apples or just pears, you still get the offer
 *
 * Basket example 1:
 * [ apples, apples, tea ] - total : 1.60 + 0.80 + 2 = 4.40
 *
 * Basket example 2:
 * [ apples, pears, tea ] - total : 0.80 + 1.80 + 2 = 4.60
 *
 * Basket example 3:
 * [ apples, apples, pears, tea ] - total : 1.60 + 0.80 + 1.80 + 2 = 6.20
 *
 * Basket example 4:
 * [ apples, pears, pears, tea ] - total : 0.80 + 1.80 + 1.80 + 2 = 6.40
 */
public class TestSupermarketCheckout {
  enum ShopItem {
    APPLES(1.60),
    PEARS(1.80),
    TEA(2.00),
    COFFEE(2.50),
    BREAD(1.20),
    UNKNOWN(null);
    private final Double price;
    ShopItem(Double price) {
      this.price = price;
    }
  }

  List<ShopItem> basket = new ArrayList<>();
  Comparator<ShopItem> byPrice = (o1, o2) -> {
    int comp = o2.price.compareTo(o1.price);
    if (comp == 0)
      return 1;
    else
      return comp;
  };
  void add(ShopItem item) {
    basket.add(item);
  }
  Double total() {
    LinkedList<ShopItem> applesAndPears = basket.stream()
        .filter(a-> a.equals(APPLES) || a.equals(PEARS))
        .sorted(byPrice)
        .collect(Collectors.toCollection(LinkedList::new));
    Double buyOneGetHalfSecond = 0.0;
    while (!applesAndPears.isEmpty()) {
      ShopItem first = applesAndPears.getFirst();
      buyOneGetHalfSecond+=first.price;
      applesAndPears.removeFirst();
      if (applesAndPears.isEmpty()) break;
      ShopItem last = applesAndPears.getLast();
      buyOneGetHalfSecond+=(last.price/2);
      applesAndPears.removeLast();
    }

    Double nonDiscountTotal = basket.parallelStream()
        .filter(a -> !a.equals(APPLES))
        .filter(a -> !a.equals(PEARS))
        .map(a -> a.price).reduce(0.0, (partialSum, a) -> partialSum + a, Double::sum);
    return buyOneGetHalfSecond+nonDiscountTotal;
  }
  @Test
  void singleItemNoDiscount() {
    add(APPLES);
    assertThat(total()).isEqualTo(1.60);
  }
  @Test
  void twoItemsNoDiscount() {
    add(TEA);
    add(COFFEE);
    assertThat(total()).isEqualTo(4.5);
  }
  @Test
  void twoBasketExample1() {
    add(APPLES);
    add(APPLES);
    add(TEA);
    assertThat(total()).isEqualTo(4.40);
  }
  @Test
  void twoBasketExample2() {
    add(APPLES);
    add(PEARS);
    add(TEA);
    assertThat(total()).isEqualTo(4.60);
  }
  @Test
  void twoBasketExample3() {
    add(APPLES);
    add(APPLES);
    add(PEARS);
    add(TEA);
    assertThat(total()).isEqualTo(6.20);
  }
  @Test
  void twoBasketExample4() {
    add(APPLES);
    add(PEARS);
    add(PEARS);
    add(TEA);
    assertThat(total()).isEqualTo(6.40);
  }
}
