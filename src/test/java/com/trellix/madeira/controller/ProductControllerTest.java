package com.trellix.madeira.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.controller.ProductController;
import com.madeira.dto.product.CreateProductRequest;
import com.madeira.dto.product.CreateProductResponse;
import com.madeira.dto.product.ProductData;
import com.madeira.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    ProductController productController; 

    @Mock
    ProductService productService;


    @Test
    void testCreateProduct() {

        CreateProductRequest mockProductRequest = new CreateProductRequest();
        mockProductRequest.setName("EDR");
        mockProductRequest.setDescription("Endpoint Detection and Response");
        mockProductRequest.setTags(Collections.emptyList());

        CreateProductResponse mockProductResponse = new CreateProductResponse();
        mockProductResponse.setId(UUID.randomUUID());
        mockProductResponse.setMessage("The Product has been created");

        when(productService.addProduct(mockProductRequest)).thenReturn(mockProductResponse);
        
        CreateProductResponse actualProductResponse = productController.createProduct(mockProductRequest);

        assertEquals(actualProductResponse, mockProductResponse);
    }

    @Test
    void testGetProducts() {

        ProductData mockProduct = new ProductData();
        mockProduct.setProductId(UUID.randomUUID());
        mockProduct.setName("EDR");
        mockProduct.setDescription("Endpoint Detection and Response");
        mockProduct.setTags(Collections.emptyList());
        mockProduct.setEmployees(Collections.emptyList());

        List<ProductData> mockResponseProducts = List.of(mockProduct); 

        when(productService.getProducts()).thenReturn(mockResponseProducts);

        List<ProductData> actualResponseProducts = productController.getProducts();

        assertEquals(actualResponseProducts, mockResponseProducts);

    }
}
