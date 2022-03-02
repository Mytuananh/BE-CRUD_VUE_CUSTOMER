package com.example.customernew.repository;

import com.example.customernew.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
     @Query(value = "select count(customer.id) from customer", nativeQuery = true)
     Integer countCustomerById();

     @Query(value = "select count(customer.id) from customer where status = true", nativeQuery = true)
     Integer countCustomerByStatus();
}
