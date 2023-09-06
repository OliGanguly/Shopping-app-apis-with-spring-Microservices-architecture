package com.codebyoli.inventoryservice.repo;

import com.codebyoli.inventoryservice.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory , Long> {
//    Optional<Inventory> findBySkuCode(String skuCode);

//    Optional<Object> findBySkuCodeIn(List<String> skuCode);
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}

