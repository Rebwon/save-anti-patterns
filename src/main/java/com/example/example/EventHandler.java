package com.example.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class EventHandler {

    @EventListener
    public void handle(OrderCompletedEvent event) {
        System.out.println("Completed order id : " + event.getOrderId());
    }
}
