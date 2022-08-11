package com.example.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void completed(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(IllegalArgumentException::new);
        order.completed();
        orderRepository.save(order);
    }
}
