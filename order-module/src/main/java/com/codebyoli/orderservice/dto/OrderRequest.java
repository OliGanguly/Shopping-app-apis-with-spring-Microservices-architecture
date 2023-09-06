package com.codebyoli.orderservice.dto;
import lombok.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderNumber ;
    private List<OrderLineItemsDto> orderLineItemsDto;
}
