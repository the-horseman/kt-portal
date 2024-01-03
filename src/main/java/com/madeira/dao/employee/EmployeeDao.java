package com.madeira.dao.employee;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.madeira.entity.Employee;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class EmployeeDao {
    
    private final EmployeeRepository employeeRepository;

    public UUID saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getEmployeeId();
    }

    public boolean employeeExistsByUsername(String username) {
        return employeeRepository.existsEmployeeByEmail(username);
    } 

    public Optional<Employee> findEmployeeByUsername(String username) {
        return employeeRepository.findByEmail(username);
    }

}
