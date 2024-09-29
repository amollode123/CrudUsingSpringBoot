package com.demo.service;

import com.demo.model.Product;
import com.demo.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
        entityManager.createNativeQuery("alter table product auto_increment = 1").executeUpdate();
    }
    
}
