package com.jellali.serviceproduit.cqrs;

import com.jellali.serviceproduit.Entity.Product;
import com.jellali.serviceproduit.Repository.ProductRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;

    private final ProductRepository productRepository;

    @Autowired
    public ProductAggregate(ProductRepository productRepository) {
        // Default constructor required by Axon
        this.productRepository = productRepository;
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command, ProductRepository productRepository) {
        this.productRepository = productRepository;
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getPrice(),
                command.getStock()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.stock = event.getStock();

        productRepository.save(new Product(this.id,this.name,this.description,this.price,this.stock));

    }
}
