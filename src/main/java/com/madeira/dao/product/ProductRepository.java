package com.madeira.dao.product;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.madeira.entity.Product;
import com.madeira.entity.Video;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    
    boolean existsProductByName(String name);

    List<Product> findByProductId(UUID productId);

    @Query("SELECT v FROM Video v JOIN v.tags t JOIN t.products p WHERE p.productId = :id")
    List<Video> findVideosByProductId(@Param("id") UUID id);

}
