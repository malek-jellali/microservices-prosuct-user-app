//package com.jellali.serviceproduit.Controller;
//
//import com.jellali.serviceproduit.Entity.Product;
//import com.jellali.serviceproduit.Service.ProductService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
//import io.github.resilience4j.retry.annotation.Retry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.Resp
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Autowired
//    private ProductService productService;onseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//
//    @Autowired
//    private RestTemplate restTemplate; // RestTemplate with Ribbon integration
//
//
//
//    // Create a new product
//    @PostMapping
//    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//        Product newProduct = productService.createProduct(product);
//        return ResponseEntity.ok(newProduct);
//    }
//
//    // Get all products
//    @GetMapping
//    @Retry(name = "myRetry", fallbackMethod = "fallbackForGetAllProducts")
//    @RateLimiter(name = "myRateLimiter", fallbackMethod = "fallbackForGetAllProducts")
//    @CircuitBreaker(name = "productmicroService", fallbackMethod = "fallbackForGetAllProducts")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    // Fallback method for getAllProducts
//    public ResponseEntity<List<Product>> fallbackForGetAllProducts(Throwable throwable) {
//        // Log the exception (optional)
//        System.err.println("Erreur lors de l'appel de getAllProducts: " + throwable.getMessage());
//        // Return a generic response or empty list
//        return ResponseEntity.ok(Collections.emptyList());
//    }
//
//    // Get a product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Optional<Product> product = productService.getProductById(id);
//        return product.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Example of using Ribbon to call another microservice (e.g., User Service)
//    @GetMapping("/users")
//    public ResponseEntity<String> getUsers() {
//        // Call the user-microservice using Ribbon
//        String userServiceUrl = "http://user-microservice/users"; // Service name is resolved via Ribbon
//        String response = restTemplate.getForObject(userServiceUrl, String.class);
//        return ResponseEntity.ok(response);
//    }
//
//    // Update an existing product
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
//        Product updatedProduct = productService.updateProduct(id, productDetails);
//        return updatedProduct != null ? ResponseEntity.ok(updatedProduct)
//                : ResponseEntity.notFound().build();
//    }
//
//    // Delete a product
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//}
