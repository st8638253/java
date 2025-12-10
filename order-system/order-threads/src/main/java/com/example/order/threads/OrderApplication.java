package com.example.order.threads;

import com.example.order.processing.model.Clothing;
import com.example.order.processing.model.Electronics;
import com.example.order.processing.model.Order;
import com.example.order.processing.service.OrderProcessingException;
import com.example.order.processing.service.OrderProcessor;
import com.example.order.storage.InMemoryOrderRepository;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderApplication {
    public static void main(String[] args) throws InterruptedException {
        Faker faker = new Faker();

        InMemoryOrderRepository<Electronics> electronicsRepository = new InMemoryOrderRepository<>();
        InMemoryOrderRepository<Clothing> clothingRepository = new InMemoryOrderRepository<>();

        OrderProcessor<Electronics> electronicsProcessor = new OrderProcessor<>(electronicsRepository);
        OrderProcessor<Clothing> clothingProcessor = new OrderProcessor<>(clothingRepository);

        List<Order<Electronics>> electronicsOrders = new ArrayList<>();
        List<Order<Clothing>> clothingOrders = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Electronics electronics = Electronics.builder()
                    .id(UUID.randomUUID().toString())
                    .name(faker.commerce().productName())
                    .price(new BigDecimal(faker.commerce().price()))
                    .brand(faker.company().name())
                    .model(faker.bothify("Model-####"))
                    .build();

            Order<Electronics> order = Order.<Electronics>builder()
                    .id(UUID.randomUUID().toString())
                    .product(electronics)
                    .quantity(faker.number().numberBetween(1, 5))
                    .createdAt(LocalDateTime.now())
                    .build();

            electronicsOrders.add(order);
        }

        for (int i = 0; i < 5; i++) {
            Clothing clothing = Clothing.builder()
                    .id(UUID.randomUUID().toString())
                    .name(faker.commerce().productName())
                    .price(new BigDecimal(faker.commerce().price()))
                    .size(faker.options().option("S", "M", "L", "XL"))
                    .color(faker.color().name())
                    .build();

            Order<Clothing> order = Order.<Clothing>builder()
                    .id(UUID.randomUUID().toString())
                    .product(clothing)
                    .quantity(faker.number().numberBetween(1, 5))
                    .createdAt(LocalDateTime.now())
                    .build();

            clothingOrders.add(order);
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable electronicsTask = () -> electronicsOrders.forEach(order -> {
            try {
                electronicsProcessor.processOrder(order);
            } catch (OrderProcessingException e) {
                System.err.println("Electronics order error: " + e.getMessage());
            }
        });

        Runnable clothingTask = () -> clothingOrders.forEach(order -> {
            try {
                clothingProcessor.processOrder(order);
            } catch (OrderProcessingException e) {
                System.err.println("Clothing order error: " + e.getMessage());
            }
        });

        executor.submit(electronicsTask);
        executor.submit(clothingTask);

        electronicsOrders.forEach(electronicsProcessor::logOrderSummary);
        clothingOrders.forEach(clothingProcessor::logOrderSummary);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        List<Order<Electronics>> expensiveElectronicsOrders =
                electronicsRepository.findByPredicate(o -> o.getProduct().getPrice().compareTo(new BigDecimal("500")) > 0);

        System.out.println("Expensive electronics orders: " + expensiveElectronicsOrders.size());
    }
}
