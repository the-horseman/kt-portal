package com.trellix.madeira.dao.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.dao.product.ProductDao;
import com.madeira.dao.product.ProductRepository;
import com.madeira.entity.Product;

@ExtendWith(MockitoExtension.class)
public class ProductDaoTest {

    @InjectMocks
    ProductDao productDao;

    @Mock
    ProductRepository productRepository;

    @Test
    void testSaveProduct() {

        Product mockProduct = new Product();
        mockProduct.setName("EDR");
        mockProduct.setDescription("Endpoint Detection and Response");

        UUID mockId = UUID.randomUUID();

        Product savedMockProduct = new Product();
        savedMockProduct.setProductId(mockId);
        savedMockProduct.setName(mockProduct.getName());
        savedMockProduct.setDescription(mockProduct.getDescription());
        savedMockProduct.setTags(Collections.emptyList());

        when(productRepository.save(mockProduct)).thenReturn(savedMockProduct);

        UUID actualId = productDao.saveProduct(mockProduct);

        assertEquals(actualId, mockId);

    }

    @Test
    void testGetAllProduct() {

        Product mockProduct = new Product();
        mockProduct.setProductId(UUID.randomUUID());
        mockProduct.setName("EDR");
        mockProduct.setDescription("Endpoint Detection and Response");
        mockProduct.setTags(Collections.emptyList());

        List<Product> mockProductList = List.of(mockProduct); 

        when(productRepository.findAll()).thenReturn(mockProductList);

        List<Product> actualProductList = productDao.getAllProduct();

        assertEquals(actualProductList, mockProductList);

    }

    @Test
    void testExistsProductByName() {

        String mockName = "EDR";
        boolean mockResponse = true;

        when(productRepository.existsProductByName(mockName)).thenReturn(mockResponse);

        boolean actualResponse = productDao.existsProductByName(mockName);

        assertEquals(actualResponse, mockResponse);

    }

    @Test
    void testGetByProductId() {

        UUID mockId = UUID.randomUUID();
        Product mockProduct = new Product();
        Optional<Product> mockOptionalProduct = Optional.of(mockProduct);

        when(productRepository.findById(mockId)).thenReturn(mockOptionalProduct);
        Product actualResponse = productDao.getByProductId(mockId);

        assertEquals(actualResponse, mockProduct);

    }

}
