package com.madeira.dao.product;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.madeira.entity.Product;
import com.madeira.entity.Video;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductDao {
    
    final private ProductRepository productRepository;

    public UUID saveProduct(Product product) {
        Product savedProduct =  productRepository.save(product);
        return savedProduct.getProductId();
    }
    
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Boolean existsProductByName(String name) {
        return productRepository.existsProductByName(name);
    }

    public Product getByProductId(UUID id) {
        Product product = productRepository.findById(id).get();
        return product; 
    }

    public List<Video> getVideosByProductId(UUID id) {
        return productRepository.findVideosByProductId(id);
    }

}
