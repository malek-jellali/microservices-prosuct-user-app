package com.jellali.serviceproduit.cqrs;

public class GetProductByIdQuery {
    private final String id;

    public GetProductByIdQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
