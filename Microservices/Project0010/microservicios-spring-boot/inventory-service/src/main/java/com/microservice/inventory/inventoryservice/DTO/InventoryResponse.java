package com.microservice.inventory.inventoryservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryResponse {
    private String codeSku;
    private boolean inStock;
}
