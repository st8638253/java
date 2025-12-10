package com.example.order.processing.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Clothing extends Product {
    private final String size;
    private final String color;

    @Builder
    public Clothing(String id, String name, BigDecimal price, String size, String color) {
        super(id, name, price);
        this.size = size;
        this.color = color;
    }
}
