package com.rajesh.service;

import com.rajesh.domain.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

// GroceryItemRepository.java
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}

