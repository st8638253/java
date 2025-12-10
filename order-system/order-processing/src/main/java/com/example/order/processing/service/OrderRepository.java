package com.example.order.processing.service;

import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;
import java.util.List;
import java.util.function.Predicate;

public interface OrderRepository<T extends Product> {
    void save(Order<T> order);
    List<Order<T>> findAll();
    List<Order<T>> findByPredicate(Predicate<Order<T>> predicate);
}
