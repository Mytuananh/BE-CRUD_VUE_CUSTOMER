package com.example.customernew.controller;

import com.example.customernew.model.Customer;
import com.example.customernew.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        List<Customer> customers = customerService.findAll();
        if(customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<Customer> cOptional = customerService.findById(id);
        if(!cOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> cOptional = customerService.findById(id);
        if(!cOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cOptional.get().setCompany(customer.getCompany());
        cOptional.get().setFirstName(customer.getFirstName());
        cOptional.get().setLastName(customer.getLastName());
        cOptional.get().setEmail(customer.getEmail());
        cOptional.get().setStatus(customer.getStatus());
        customerService.save(cOptional.get());
        return new ResponseEntity<>(cOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countCustomer() {
        Integer count = customerService.countCustomerById();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<?> countCustomerByStatus() {
        Integer count = customerService.countCustomerByStatus();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> cOptional = customerService.findById(id);
        if(!cOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
