package com.henokcodes.carrental.controller;


import com.henokcodes.carrental.Dto.CustomerDTO;
import com.henokcodes.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    //get all cars
    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    // get customer by customerNumber
    @GetMapping("/{customerNumber}")
    public CustomerDTO getCustomerByCustomerNumber(@PathVariable String customerNumber){
        return customerService.getCustomerByCustomerNumber(customerNumber);
    }
    //search customer
    @GetMapping("/search")
    public List<CustomerDTO> getCustomerByKey(@RequestParam String key, @RequestParam String value){
        if(key.equals("name"))
        return customerService.getCustomersByName(value);
        else if(key.equals("email"))
        return customerService.getCustomersByEmail(value);
        else return null;
    }

    //add customer
    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }

    //update customer
    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(customerDTO);
    }
    //remove customer
    @DeleteMapping("/{customerNumber}")
    public void removeCustomer(@PathVariable String customerNumber){
        customerService.removeCustomer(customerNumber);
    }
 

}
