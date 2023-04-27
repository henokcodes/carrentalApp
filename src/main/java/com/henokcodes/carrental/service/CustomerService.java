package com.henokcodes.carrental.service;


import com.henokcodes.carrental.Domain.Customer;
import com.henokcodes.carrental.Dto.CustomerDTO;
import com.henokcodes.carrental.adapter.CustomerAdapter;
import com.henokcodes.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAdapter customerAdapter;


    //add customer
    public CustomerDTO addCustomer(CustomerDTO customerDTO){

        Customer customer = customerAdapter.getCustomer(customerDTO);
        customerRepository.save(customer);
        return customerDTO;
    }
    //remove customer
    public void removeCustomer(String customerNumber){
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);
        customerRepository.delete(customer);
    }
    //update customer
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        Customer customer = customerRepository.findByCustomerNumber(customerDTO.getCustomerNumber());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customerRepository.save(customer);
        return customerDTO;
    }

    //get all customers
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers =  customerRepository.findAll();
        System.out.println(customers);
        List<CustomerDTO> customerDTOs = (List<CustomerDTO>) customerAdapter.getAllCustomerDTOs(customers);
        return customerDTOs;
    }
    // get customer by customerNumber
    public CustomerDTO getCustomerByCustomerNumber(String customerNumber){
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);
        CustomerDTO customerDTO = customerAdapter.getCustomerDTO(customer);
        return customerDTO;
    }
    //get customer by name
    public List<CustomerDTO> getCustomersByName(String name){
        List<Customer> customer = customerRepository.findByName(name);
        List<CustomerDTO> customerDTO = (List<CustomerDTO>) customerAdapter.getAllCustomerDTOs(customer);
        return customerDTO;
    }
    //get customer by email
    public List<CustomerDTO> getCustomersByEmail(String email){
        List<Customer> customer = customerRepository.findByEmail(email);
        List<CustomerDTO> customerDTO = (List<CustomerDTO>) customerAdapter.getAllCustomerDTOs(customer);
        return customerDTO;
    }




}
