package com.example.example;

public class OrderCompletedEvent {

    private Long orderId;

    public OrderCompletedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
