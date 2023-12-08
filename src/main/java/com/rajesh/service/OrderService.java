package com.rajesh.service;

import com.rajesh.domain.GroceryItem;
import com.rajesh.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GroceryItemRepository groceryItemRepository;
    public Order createOrder(Set<Long> itemIds) {
        Set<GroceryItem> selectedItems = new HashSet<>();
        for (Long itemId : itemIds) {
            Optional<GroceryItem> optionalItem = groceryItemRepository.findById(itemId);
            optionalItem.ifPresent(selectedItems::add);
        }
        Order order = new Order();
        order.setItems(selectedItems);
        return orderRepository.save(order);
    }

    public List<GroceryItem> findAvailableItems() {
        return groceryItemRepository.findAll();
    }
}
