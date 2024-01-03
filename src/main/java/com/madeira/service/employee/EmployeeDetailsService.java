package com.madeira.service.employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.madeira.dao.employee.EmployeeDao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeDao employeeDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeDao.findEmployeeByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }


}  