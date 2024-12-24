package com.jellali.serviceproduit.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jellali.serviceproduit.Entity.Product;
public interface ProductRepository extends JpaRepository<Product, String> {
}

