//package com.jellali.serviceproduit.Service;
//
//import com.jellali.serviceproduit.Entity.Product;
//import com.jellali.serviceproduit.Repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class
//ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    // Create a new product
////    public Product createProduct(Product product) {
////        return productRepository.save(product);
////    }
//
//    // Get all products
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    // Get a product by ID
//    public Optional<Product> getProductById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    // Update a product
//    public Product updateProduct(Long id, Product productDetails) {
//        Optional<Product> existingProduct = productRepository.findById(id);
//        if (existingProduct.isPresent()) {
//            Product product = existingProduct.get();
//            product.setName(productDetails.getName());
//            product.setDescription(productDetails.getDescription());
//            product.setPrice(productDetails.getPrice());
//            return productRepository.save(product);
//        }
//        return null;
//    }
//
//    // Delete a product
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//}
//
