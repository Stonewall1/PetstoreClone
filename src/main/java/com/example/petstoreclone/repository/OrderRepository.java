package com.example.petstoreclone.repository;

import com.example.petstoreclone.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
