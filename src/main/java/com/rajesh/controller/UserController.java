package com.rajesh.controller;

import com.rajesh.domain.GroceryItem;
import com.rajesh.domain.Order;
import com.rajesh.service.GroceryItemRepository;
import com.rajesh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

// UserController.java
@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return new ResponseEntity<>("Welcome application ready", HttpStatus.OK);
    }

    @Autowired
    private GroceryItemRepository groceryItemRepository;
    @Autowired
    private OrderService orderService;

    // View the list of available grocery items
    @GetMapping("/viewAvailableItems")
    public ResponseEntity<List<GroceryItem>> viewAvailableItems() {
        List<GroceryItem> availableItems = orderService.findAvailableItems();
        return new ResponseEntity<>(availableItems, HttpStatus.OK);
    }

    // Create order (book multiple grocery items in a single order)
    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Set<Long> itemIds) {
        Order savedOrder = orderService.createOrder(itemIds);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}

