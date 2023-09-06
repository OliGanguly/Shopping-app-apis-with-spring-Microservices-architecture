package com.codebyoli.inventoryservice.service;

import com.codebyoli.inventoryservice.dto.InventoryResponse;
import com.codebyoli.inventoryservice.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

//        @Transactional(readOnly = true)
//    public boolean isInStock(List<String> skuCode){
//        return inventoryRepository.findBySkuCodeIn(skuCode).isPresent();
//    }
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(
                inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQty() > 0)
                        .build()
        ).toList();

    }
}
