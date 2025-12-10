package com.example.order.processing.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Order<T extends Product> {
    private final String id;
    private final T product;
    private final int quantity;
    private final LocalDateTime createdAt;

    @Builder
    public Order(String id, T product, int quantity, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }
}
