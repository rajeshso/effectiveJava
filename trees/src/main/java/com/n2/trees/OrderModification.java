package com.n2.trees;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderModification {

    private final long orderId;
    private final int newPrice;
    private final int newQuantity;
}
