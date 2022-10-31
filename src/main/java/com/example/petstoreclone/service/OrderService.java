package com.example.petstoreclone.service;

import com.example.petstoreclone.entity.Order;
import com.example.petstoreclone.entity.Pet;
import com.example.petstoreclone.exceptions.NoIdFoundException;
import com.example.petstoreclone.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    public Order deleteById(long id) {
        Optional<Order> byId = findById(id);
        if (byId.isPresent()) {
            orderRepository.deleteById(id);
            return byId.get();
        } else throw new NoIdFoundException();
    }
}
