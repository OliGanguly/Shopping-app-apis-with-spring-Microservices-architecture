package com.codebyoli.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDtoResponse {
    private Long id;
    private String skucode;
    private BigDecimal price ;
    private int qty ;
}
