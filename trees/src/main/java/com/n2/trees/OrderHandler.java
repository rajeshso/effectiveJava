package com.n2.trees;

public interface OrderHandler {

    double ERROR_CODE = -1.000;

    void addOrder(Order order);

    void modifyOrder(OrderModification orderModification);

    void removeOrder(long orderId);

    double getCurrentPrice(String symbol, int quantity, Side side);

    /**
     * Please implement this method so we are able to create an instance of your OrderHandler
     * implementation.
     */
    static OrderHandler createInstance() {
        return new InMemoryOrderHandler();
    }
}
