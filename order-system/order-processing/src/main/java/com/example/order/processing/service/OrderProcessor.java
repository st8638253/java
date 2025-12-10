package com.example.order.processing.service;

import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;
import java.util.Objects;

public class OrderProcessor<T extends Product> {
    private final OrderRepository<T> repository;

    public OrderProcessor(OrderRepository<T> repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void processOrder(Order<T> order) throws OrderProcessingException {
        if (order == null) {
            throw new OrderProcessingException("Order cannot be null");
        }
        if (order.getProduct() == null) {
            throw new OrderProcessingException("Product cannot be null");
        }
        if (order.getQuantity() <= 0) {
            throw new OrderProcessingException("Quantity must be positive");
        }
        repository.save(order);
        System.out.println("Processed order " + order.getId() + " for " + order.getProduct().getName() + " in thread " + Thread.currentThread().getName());
    }

    public void logOrderSummary(Order<T> order) {
        System.out.println("Order summary: " + order);
    }
}
