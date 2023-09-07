package com.codebyoli.orderservice.services;

import com.codebyoli.orderservice.dto.*;
import com.codebyoli.orderservice.model.Order;
import com.codebyoli.orderservice.model.OrderLineItems;
import com.codebyoli.orderservice.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private  WebClient webClient;



    /*call Inventory servic and place order if it is available - webclient
         orderRepo.save(order);
         oredr object setField done
         call Inventory service
         and place order if it is available - webclient
        read the data from web client - bodyToMono
        .block - synchronous call
        -------------------------------------------------
        INVENTORY CONTROLLER - PREVIOUS
     public boolean isInStock(@PathVariable("sku-code") String skuCode){
     return inventoryService.isInStock(skuCode);
     }
     Boolean result = webClient.get().uri("").retrieve().bodyToMono(Boolean.class).block();
    if(result){
        //save
    }else {
        throw new Exception();
    }
    * */
    /*
    * OUR REQUIRMENT
    * ORDER has many ORDERLINEITEMS
    * Each ORDERLINEITEM has skucode
    * */
    public void createOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
         List<OrderLineItems> listData =
                 orderRequest.getOrderLineItemsDto().stream().
                 map(orderLineItemsDto -> DtotoOrderListItems(orderLineItemsDto))
                .toList();
         order.setOrderLineItems(listData);
         //Collect all skucode from order obj
         List<String> skuCodes = order.
                 getOrderLineItems()
                 .stream()
                 .map(orderLineItems -> orderLineItems.getSkucode())
                 .toList();
/*we have to create inventoryResponse in order service as well , cause we can not access Inverntory service class here
        but the return type is InventoryResponse*/
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        System.out.println("inventoryResponseArray"+inventoryResponseArray);

        boolean allProducts=Arrays.
                stream(inventoryResponseArray).
                allMatch(inventoryResponse ->inventoryResponse.isInStock());
        if(allProducts){
            orderRepo.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in stock");
        }

    }
    //covert order line dto to orderLineItems
    public OrderLineItems DtotoOrderListItems(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkucode(orderLineItemsDto.getSkucode());
        orderLineItems.setQty(orderLineItemsDto.getQty());
        return  orderLineItems;
    }

    public List<OrderResponse> allOrders(){
        List<Order> order = orderRepo.findAll();
        OrderResponse orderResponse = new OrderResponse();
        List<OrderResponse> orderResponse1 =
                order.stream().
                        map(order1 -> mapToOrderResponse(order1)).toList();
//        orderResponse.setOrderNumber();
        return (List<OrderResponse>) orderResponse1;
//                order.stream().map(order -> mapToOrderResponse(order)).toList();
    }
    public OrderResponse mapToOrderResponse(Order o){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderNumber(o.getOrderNumber());
        List<OrderLineItemsDtoResponse> orderResponse1 = o.getOrderLineItems()
                        .stream()
                                .map(orderLineItems -> orderToResponseDto(orderLineItems)).toList();
         orderResponse.setOrderLineItemsDtoResponses(orderResponse1);
         return orderResponse;
    }

    public OrderLineItemsDtoResponse orderToResponseDto(OrderLineItems orderLineItems){
        OrderLineItemsDtoResponse orderLineItemsDtoResponse = OrderLineItemsDtoResponse.builder().id(orderLineItems.getId())
                .qty(orderLineItems.getQty())
                .price(orderLineItems.getPrice())
                .skucode(orderLineItems.getSkucode()).build();
        return  orderLineItemsDtoResponse;
    }
}


