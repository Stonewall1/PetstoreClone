package com.example.petstoreclone.service;

import com.example.petstoreclone.entity.Order;
import com.example.petstoreclone.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        order.setShipDate(LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }
}
