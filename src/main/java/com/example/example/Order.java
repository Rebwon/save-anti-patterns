package com.example.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "orders")
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private List<OrderLine> orderLines = new ArrayList<>();
    private Integer totalAmount;

    public Order(Long customerId, OrderStatus status, List<OrderLine> orderLines) {
        this.customerId = customerId;
        this.status = status;
        this.orderLines = orderLines;
    }

    public void completed() {
        this.status = OrderStatus.COMPLETED;
        this.orderLines.get(0).setProductName("오뜨!");
        this.totalAmount = this.orderLines.stream()
            .mapToInt(OrderLine::getAmount)
            .sum();
        registerEvent(new OrderCompletedEvent(this.id));
    }

    protected Order() {
    }

    public enum OrderStatus {CREATED, COMPLETED, CANCELED}

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
