package com.jellali.serviceproduit.cqrs;

import com.jellali.serviceproduit.Repository.ProductRepository;
import com.jellali.serviceproduit.Entity.Product;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProjection {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public Product handle(GetProductByIdQuery query) {
        return productRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<Product> handle(FindAllProducts query){
        return productRepository.findAll();
    }
}
