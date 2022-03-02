package com.example.customernew.service;

import com.example.customernew.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Integer countCustomerByStatus();
    Integer countCustomerById();
    List<Customer> findAll();
    void deleteById(Long id);
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
}
