package com.example.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.example.Order.OrderStatus;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    EntityManager em;

    @BeforeEach
    @Transactional
    void setUp() {
        orderRepository.save(new Order(1L, OrderStatus.CREATED,
            List.of(OrderLine.builder().productName("포카칩").amount(1500).build(),
                OrderLine.builder().productName("죠리퐁").amount(1200).build(),
                OrderLine.builder().productName("다이제 초코").amount(2500).build(),
                OrderLine.builder().productName("다이제 기본").amount(2000).build())));

        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void name() {
        Long orderId = 1L;

        orderService.completed(orderId);
    }
}