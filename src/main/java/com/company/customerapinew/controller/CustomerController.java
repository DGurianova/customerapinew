package com.company.customerapinew.controller;

import com.company.customerapinew.entity.Customer;
import com.company.customerapinew.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> index() {
        return customerRepository.findAll();
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);

    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer replaceCustomer(@RequestBody Customer customer) {
        Customer oldCustomer = customerRepository.getById(customer.getId());
        if (oldCustomer != null) {
            customerRepository.deleteById(customer.getId());
        }
        return customerRepository.save(customer);

    }

    @DeleteMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCustomer(@RequestBody Customer customer) {
        customer = customerRepository.getById(customer.getId());
        if (customer != null) {
            customerRepository.deleteById(customer.getId());
        }
        return "this customer is deleted";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerById(@PathVariable int id) throws Exception {
        Customer customer = customerRepository.findById(id).get();
        if (customer == null) {
            throw new Exception();
        }
        return customer;

    }


}
