package com.example.order.storage;

import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;
import com.example.order.processing.service.OrderRepository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class InMemoryOrderRepository<T extends Product> implements OrderRepository<T> {
    private final List<Order<T>> storage = new CopyOnWriteArrayList<>();

    @Override
    public void save(Order<T> order) {
        storage.add(order);
    }

    @Override
    public List<Order<T>> findAll() {
        return List.copyOf(storage);
    }

    @Override
    public List<Order<T>> findByPredicate(Predicate<Order<T>> predicate) {
        return storage.stream().filter(predicate).toList();
    }
}
