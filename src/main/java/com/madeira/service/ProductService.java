package com.madeira.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.madeira.dao.product.ProductDao;
import com.madeira.dto.employee.EmployeeData;
import com.madeira.dto.product.CreateProductRequest;
import com.madeira.dto.product.CreateProductResponse;
import com.madeira.dto.product.ProductData;
import com.madeira.entity.Product;
import com.madeira.entity.Video;
import com.madeira.exception.ConflictException;
import com.madeira.exception.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {
    
    final static private String PRODUCT_NOT_FOUND_MESSAGE = "The Product was not found";
    final static private String USER_CREATION_MESSGAE = "The Product has been created";
    final static private String PRODUCT_TAKEN_MESSAGE = "The Product name is already taken";
    final private ProductDao productDao;

    public CreateProductResponse addProduct(CreateProductRequest productRequest) {

        if (productDao.existsProductByName(productRequest.getName())) {
            throw new ConflictException(PRODUCT_TAKEN_MESSAGE);
        }
        Product product = new Product(
            productRequest.getName(),
            productRequest.getDescription()
        ); 
        UUID id = productDao.saveProduct(product);
        return new CreateProductResponse(
            id,
            USER_CREATION_MESSGAE
        );
    }

    public ProductData patchProduct(UUID id, CreateProductRequest patch) {
        Product product = this.getProductById(id);
        product.setName(patch.getName());
        product.setDescription(patch.getDescription());
        productDao.saveProduct(product);
        return this.mapToProductData(product);
    }

    public List<ProductData> getProducts() {
        List<Product> products = productDao.getAllProduct(); 
        return products.stream()
            .map(product -> mapToProductData(product))
            .toList();
    }

    public Product getProductById(UUID id) {
        try {
            return productDao.getByProductId(id);
        } catch (Exception e) {
            throw new NotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
        }
    }

    public List<Video> getVideosByProductId(UUID id) {
        this.getProductById(id);
        return productDao.getVideosByProductId(id);
    }

    private ProductData mapToProductData(Product product) {
        return new ProductData(
            product.getProductId(), 
            product.getName(), 
            product.getDescription(),
            product.getTags(), 
            product.getEmployees().stream()
                .filter(employee -> employee.isPersonOfContact())
                .map(employee -> new EmployeeData(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.isPersonOfContact()
                )).toList()
        );
    }

}
