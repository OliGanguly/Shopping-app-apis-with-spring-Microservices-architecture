package com.codebyoli.productservice.repo;

import com.codebyoli.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {

}
