package com.example.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of = "id")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Integer amount;

    @Builder
    public OrderLine(String productName, Integer amount) {
        this.productName = productName;
        this.amount = amount;
    }

    protected OrderLine() {}

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
