package com.example.order.processing.internal;

import java.math.BigDecimal;

public final class PriceValidator {
    private PriceValidator() {
    }

    public static boolean isValid(BigDecimal price) {
        return price != null && price.signum() >= 0;
    }
}
