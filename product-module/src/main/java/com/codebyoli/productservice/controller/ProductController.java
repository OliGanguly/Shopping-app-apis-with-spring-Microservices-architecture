package com.codebyoli.productservice.controller;

import com.codebyoli.productservice.dto.ProductRequest;
import com.codebyoli.productservice.dto.ProductRespponse;
import com.codebyoli.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    /*
    * ProductRequest work as a dto
    * @RequiredArgsConstructor - constructor autowiring
    * */
    private final ProductService productService;

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
  @PostMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
    public void createProduct(@RequestBody ProductRequest products){
      productService.createProduct(products);
   }

   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<ProductRespponse> ListOfProducts(){
     return productService.allProducts();
   }
}
