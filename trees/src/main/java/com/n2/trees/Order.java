package com.n2.trees;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Order {

    private final long orderId;
    private final String symbol;
    private final Side side;
    private final int price;
    private final int quantity;
}
