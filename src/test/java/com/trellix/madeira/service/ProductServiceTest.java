package com.trellix.madeira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.dao.product.ProductDao;
import com.madeira.dto.product.CreateProductRequest;
import com.madeira.dto.product.CreateProductResponse;
import com.madeira.dto.product.ProductData;
import com.madeira.entity.Product;
import com.madeira.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @InjectMocks
    ProductService productService;

    @Mock
    ProductDao productDao;

    @Test
    void testAddProduct() {

        CreateProductRequest mockProductRequest = new CreateProductRequest();
        mockProductRequest.setName("EDR");
        mockProductRequest.setDescription("Endpoint Detection and Response");

        UUID id = UUID.randomUUID();
        CreateProductResponse mockProductResponse = new CreateProductResponse();
        mockProductResponse.setId(id);
        mockProductResponse.setMessage("The Product has been created");

        Product mockProduct = new Product();
        mockProduct.setName(mockProductRequest.getName());
        mockProduct.setDescription(mockProductRequest.getDescription());
        
        when(productDao.saveProduct(mockProduct)).thenReturn(id);
        
        CreateProductResponse actualProductResponse = productService.addProduct(mockProductRequest);

        assertEquals(actualProductResponse, mockProductResponse);

    }

    @Test
    void testGetProducts() {

        Product mockProduct = new Product();
        mockProduct.setProductId(UUID.randomUUID());
        mockProduct.setName("EDR");
        mockProduct.setDescription("Endpoint Detection and Response");
        mockProduct.setTags(Collections.emptyList());
        mockProduct.setEmployees(Collections.emptyList());
        List<Product> mockResponseProducts = List.of(mockProduct); 

        ProductData mockResponseProductData = new ProductData();
        mockResponseProductData.setProductId(mockProduct.getProductId());
        mockResponseProductData.setName(mockProduct.getName());
        mockResponseProductData.setDescription(mockProduct.getDescription());
        mockResponseProductData.setTags(mockProduct.getTags());
        mockResponseProductData.setEmployees(Collections.emptyList());
        List<ProductData> mockResponseProductDatas = List.of(mockResponseProductData);

        doReturn(mockResponseProducts).when(productDao).getAllProduct();

        List<ProductData> actualResponseProducts = productService.getProducts();

        assertEquals(actualResponseProducts, mockResponseProductDatas);

    }

    @Test
    void testGetProductById() {
        
        UUID mockId = UUID.randomUUID();
        Product mockProduct = new Product();
        mockProduct.setProductId(mockId);
        mockProduct.setName("EDR");
        mockProduct.setDescription("Endpoint Detection and Response");
        mockProduct.setTags(Collections.emptyList());

        when(productService.getProductById(mockId)).thenReturn(mockProduct);
        Product actualResponse = productService.getProductById(mockId);

        assertEquals(actualResponse, mockProduct);
    }
}
