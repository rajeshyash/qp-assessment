package com.rajesh.service;

import com.rajesh.domain.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem addGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> viewGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public void removeGroceryItem(Long id) {
        groceryItemRepository.deleteById(id);
    }
}
