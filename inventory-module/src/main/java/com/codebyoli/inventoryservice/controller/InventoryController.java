package com.codebyoli.inventoryservice.controller;

import com.codebyoli.inventoryservice.dto.InventoryResponse;
import com.codebyoli.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /*
    * Each order has multiple OrderList Items
    * Ecah Order List items has skucode
    * if user add 100 produvts , For each product we dont want to call inventory service
    * so that it will take a list of orders -skucode
    * Pathvariable
    * //http:localhost:8082/api/inventory/iphone12,iphone13
    * RequestParam
    * http:localhost:8082/api/inventory/skuCode=iphone12&Skucode=iphone13
    * */
//    public boolean isInStock(@PathVariable("sku-code") String skuCode){
//        return inventoryService.isInStock(skuCode);
//    }
    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
