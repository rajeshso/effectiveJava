package com.n2.trees;

public class Order {
    private final long orderId;
    private final String symbol;
    private final Side side;
    private final int price;
    private final int quantity;

    public Order(long orderId, String symbol, Side side, int price, int quantity) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public Side getSide() {
        return side;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        if (orderId != order.orderId) {
            return false;
        }
        if (price != order.price) {
            return false;
        }
        if (quantity != order.quantity) {
            return false;
        }
        if (!symbol.equals(order.symbol)) {
            return false;
        }
        return side == order.side;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + symbol.hashCode();
        result = 31 * result + side.hashCode();
        result = 31 * result + price;
        result = 31 * result + quantity;
        return result;
    }
}
