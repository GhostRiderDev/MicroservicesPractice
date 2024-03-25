package com.microservice.inventory.inventoryservice.DAO;

import com.microservice.inventory.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByCodeSkuIn(List<String> codeSku);
}
