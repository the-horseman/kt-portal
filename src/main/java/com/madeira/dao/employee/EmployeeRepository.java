package com.madeira.dao.employee;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madeira.entity.Employee;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);

    boolean existsEmployeeByEmail(String email);

}
