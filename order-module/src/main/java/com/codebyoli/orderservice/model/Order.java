package com.codebyoli.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber ;
    // each order has orderlineitem
    /*
    * 3 tables order , OrderLineItems , order_orderLineItems
    * {
    "orderLineItemsDto":[
      {
      "skucode":"iphone-13",
      "price" :1200,
       "qty" :1
      },

      {
      "skucode":"iphone-14",
      "price" :1300,
       "qty" :2
      }
    ]
}
    * */
    @OneToMany(cascade = CascadeType.ALL,targetEntity = OrderLineItems.class,fetch = FetchType.EAGER)
    private List<OrderLineItems> orderLineItems;
}
