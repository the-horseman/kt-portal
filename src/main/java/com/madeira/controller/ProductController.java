package com.madeira.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.madeira.dto.product.CreateProductRequest;
import com.madeira.dto.product.CreateProductResponse;
import com.madeira.dto.product.ProductData;
import com.madeira.entity.Video;
import com.madeira.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";
    private final ProductService productService; 

    @PostMapping(consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public CreateProductResponse createProduct(
        @Valid @RequestBody final CreateProductRequest productRequest
    ) {
        return productService.addProduct(productRequest);
    }

    @PatchMapping(path = "/{id}", consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public ProductData patchProduct(
        @PathVariable(value = "id") UUID id, 
        @Valid @RequestBody CreateProductRequest patch
    ) {
        return productService.patchProduct(id, patch);
    }

    @GetMapping(produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public List<ProductData> getProducts() {
        List<ProductData> products = productService.getProducts();
        return products;
    }

    @GetMapping(path = "/{id}/videos", produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public List<Video> getVideos(
        @PathVariable(value = "id") UUID id
    ) {
        return productService.getVideosByProductId(id);
    }
}
