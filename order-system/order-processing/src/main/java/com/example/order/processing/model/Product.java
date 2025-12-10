package com.example.order.processing.model;

import java.math.BigDecimal;
import com.example.order.processing.internal.PriceValidator;
import lombok.Getter;

@Getter
public abstract class Product {
    private final String id;
    private final String name;
    private final BigDecimal price;

    protected Product(String id, String name, BigDecimal price) {
        if (!PriceValidator.isValid(price)) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
