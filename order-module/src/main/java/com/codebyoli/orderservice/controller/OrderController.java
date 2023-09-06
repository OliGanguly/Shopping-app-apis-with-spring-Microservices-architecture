package com.codebyoli.orderservice.controller;

import com.codebyoli.orderservice.dto.OrderRequest;
import com.codebyoli.orderservice.dto.OrderResponse;
import com.codebyoli.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders(){
        System.out.println(orderService.allOrders());
        return orderService.allOrders();
    }
}
