package com.rajesh.service;

import com.rajesh.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// OrderRepository.java
public interface OrderRepository extends JpaRepository<Order, Long> {
}
