package com.jellali.serviceproduit.cqrs;

import com.jellali.serviceproduit.Entity.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;


    @PostMapping
    public String createProduct(@RequestBody Product product) {

        String id = UUID.randomUUID().toString();
        CreateProductCommand command = new CreateProductCommand(
                id, product.getName(), product.getDescription(), product.getPrice(),
                product.getStock());
        commandGateway.sendAndWait(command);
        return id;
    }



}
