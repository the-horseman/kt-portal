package com.madeira.service.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.madeira.dao.employee.EmployeeDao;
import com.madeira.dto.employee.CreateEmployeeRequest;
import com.madeira.dto.employee.CreateEmployeeResponse;
import com.madeira.entity.Employee;
import com.madeira.entity.Product;
import com.madeira.exception.ConflictException;
import com.madeira.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeService {
    
    private static final String USERNAME_TAKEN_MESSAGE = "This Username is already created"; 
    private static final String  EMPLOYEE_CREATION_MESSAGE = "The Employee has been created"; 
    private final EmployeeDao employeeDao;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;


    public CreateEmployeeResponse addEmployee(CreateEmployeeRequest employeeRequest) {
        if (employeeDao.employeeExistsByUsername(employeeRequest.getEmail())) {
            throw new ConflictException(USERNAME_TAKEN_MESSAGE);
        }
        Employee employee = new Employee(
            employeeRequest.getName(),
            employeeRequest.getEmail(),
            passwordEncoder.encode(employeeRequest.getPassword()),
            employeeRequest.getRole(),
            employeeRequest.isPersonOfContact()
        );
        employee = this.addProductToEmployee(employee, employeeRequest.getProducts());
        UUID employeeId = employeeDao.saveEmployee(employee);
        List<UUID> addedProducts = employee.getProducts().stream()
                .map(product -> { return product.getProductId();})
                .toList();
        return new CreateEmployeeResponse(
            employeeId,
            EMPLOYEE_CREATION_MESSAGE,
            addedProducts
        );
    }

    public Employee addProductToEmployee(Employee employee, List<UUID> products) {
        Set<Product> savedProducts = new HashSet<>();
        for (UUID id : products) {
            Product currentProduct = productService.getProductById(id);
            savedProducts.add(currentProduct);
        }
        employee.setProducts(new ArrayList<>(savedProducts));
        return employee;
    }

}
