package com.microservice.inventory.inventoryservice.service;

import com.microservice.inventory.inventoryservice.DAO.InventoryDAO;
import com.microservice.inventory.inventoryservice.DTO.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    private InventoryDAO inventoryDAO;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> codesSku) {
        return inventoryDAO.findByCodeSkuIn(codesSku).stream()
                .map(inventory -> InventoryResponse.builder()
                        .codeSku(inventory.getCodeSku())
                        .inStock(inventory.getQuantity() > 0)
                        .build())
                .collect(Collectors.toList());
    }
}
