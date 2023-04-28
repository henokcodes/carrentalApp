package com.henokcodes.carrental.controller;


import com.henokcodes.carrental.Dto.CustomerDTO;
import com.henokcodes.carrental.Dto.Customers;
import com.henokcodes.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    //get all cars
    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<Customers>( customerService.getAllCustomers(), HttpStatus.OK);
    }
    // get customer by customerNumber
    @GetMapping("/{customerNumber}")
    public ResponseEntity<?> getCustomerByCustomerNumber(@PathVariable String customerNumber){
        return new ResponseEntity<CustomerDTO>( customerService.getCustomerByCustomerNumber(customerNumber), HttpStatus.OK);
    }
    //search customer
    @GetMapping("/search")
    public ResponseEntity<?> getCustomerByKey(@RequestParam String key, @RequestParam String value){
        if(key.equals("name"))
        return new ResponseEntity<Customers>(customerService.getCustomersByName(value), HttpStatus.OK);
        else if(key.equals("email"))
        return new ResponseEntity<Customers>(customerService.getCustomersByEmail(value),  HttpStatus.OK);
        else return new ResponseEntity<String>("Invalid key",  HttpStatus.OK);
    }

    //add customer
    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.CREATED);
    }

    //update customer
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO){
        return  new ResponseEntity<CustomerDTO>(customerService.updateCustomer(customerDTO), HttpStatus.OK);
    }
    //remove customer
    @DeleteMapping("/{customerNumber}")
    public void removeCustomer(@PathVariable String customerNumber){
        customerService.removeCustomer(customerNumber);
    }
 

}
