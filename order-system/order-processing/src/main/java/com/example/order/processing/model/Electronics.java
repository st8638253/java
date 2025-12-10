package com.example.order.processing.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Electronics extends Product {
    private final String brand;
    private final String model;

    @Builder
    public Electronics(String id, String name, BigDecimal price, String brand, String model) {
        super(id, name, price);
        this.brand = brand;
        this.model = model;
    }
}
