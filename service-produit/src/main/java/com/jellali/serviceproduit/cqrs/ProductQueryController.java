package com.jellali.serviceproduit.cqrs;

import com.jellali.serviceproduit.Entity.Product;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/{id}")
    public Product getProductByUUID(@PathVariable String id){
        return queryGateway.query(new GetProductByIdQuery(id) , Product.class).join();
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return queryGateway.query(new FindAllProducts() , ResponseTypes.multipleInstancesOf(Product.class)).join();
    }
}
