package com.codebyoli.inventoryservice.controller;

import com.codebyoli.inventoryservice.dto.InventoryResponse;
import com.codebyoli.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
//        System.out.println("jjjjjjjjjjjj"+skuCode);
        return inventoryService.isInStock(skuCode);
    }
    /*
        public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }
    * Each order has multiple OrderList Items
    * Each Order List items has skucode
    * if user add 100 produvts , For each product we dont want to call inventory service
    * so that it will take a list of orders -skucode
    * Pathvariable
    * //http:localhost:8082/api/inventory/iphone12,iphone13
    * RequestParam
    * http:localhost:8082/api/inventory/skuCode=iphone12&Skucode=iphone13
    * */


    /*
    * ORDER - MANY ORDERLINE ITEMS
    *       ONE - ORDERLINEITEMS
    *          MANY SKUCODES
    * */

}
