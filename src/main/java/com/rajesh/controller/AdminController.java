package com.rajesh.controller;

import com.rajesh.domain.GroceryItem;
import com.rajesh.service.GroceryItemRepository;
import com.rajesh.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// AdminController.java
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private GroceryItemService groceryItemService;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    // Add new grocery item
    @PostMapping("/addGroceryItem")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem savedItem = groceryItemService.addGroceryItem(groceryItem);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    // View existing grocery items
    @GetMapping("/viewGroceryItems")
    public ResponseEntity<List<GroceryItem>> viewGroceryItems() {
        List<GroceryItem> items = groceryItemService.viewGroceryItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Remove grocery item by ID
    @DeleteMapping("/removeGroceryItem/{id}")
    public ResponseEntity<Void> removeGroceryItem(@PathVariable Long id) {
        groceryItemService.removeGroceryItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update details of existing grocery item by ID
    @PutMapping("/updateGroceryItem/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem updatedItem) {
        if (!groceryItemRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedItem.setId(id);
        GroceryItem savedItem = groceryItemRepository.save(updatedItem);
        return new ResponseEntity<>(savedItem, HttpStatus.OK);
    }

    // Manage inventory levels of grocery item by ID
    @PutMapping("/manageInventory/{id}")
    public ResponseEntity<GroceryItem> manageInventory(@PathVariable Long id, @RequestParam int inventoryChange) {
        Optional<GroceryItem> optionalItem = groceryItemRepository.findById(id);

        if (optionalItem.isPresent()) {
            GroceryItem item = optionalItem.get();
            item.setInventory(item.getInventory() + inventoryChange);
            GroceryItem savedItem = groceryItemRepository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

