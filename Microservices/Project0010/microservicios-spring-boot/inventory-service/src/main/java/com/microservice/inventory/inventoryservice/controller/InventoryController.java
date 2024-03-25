package com.microservice.inventory.inventoryservice.controller;

import com.microservice.inventory.inventoryservice.DTO.InventoryResponse;
import com.microservice.inventory.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("")
    public List<InventoryResponse> isInStock(@RequestParam List<String> codesSku) {
        return inventoryService.isInStock(codesSku);
    }

}
