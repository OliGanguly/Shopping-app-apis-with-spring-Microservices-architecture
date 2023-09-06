package com.codebyoli.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/*
* seperate model and dto dont expose entity to real world
* */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRespponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
