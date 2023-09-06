package com.codebyoli.productservice.services;

import com.codebyoli.productservice.dto.ProductRequest;
import com.codebyoli.productservice.dto.ProductRespponse;
import com.codebyoli.productservice.model.Product;
import com.codebyoli.productservice.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public void createProduct(ProductRequest productRequest){
        //convert dto to actual product to save data in db
        //product request to product model
        Product product = new Product();
        Product product1 = product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepo.save(product1);
        log.info("Product {} is Saved!",product1.getId());
    }
    public List<ProductRespponse> allProducts(){
        List<Product> products =  productRepo.findAll();
        //map to product response
        return products.stream().map(product -> mapTpProductResponse(product)).toList();

    }
    private ProductRespponse mapTpProductResponse(Product p){
        return ProductRespponse.builder().id(p.getId()).
                name(p.getName()).description(p.getDescription()).
                price(p.getPrice()).build();

    }
}
