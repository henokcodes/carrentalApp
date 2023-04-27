package com.henokcodes.carrental.adapter;


import com.henokcodes.carrental.Domain.Customer;
import com.henokcodes.carrental.Dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerAdapter {

    // get CustomerDTO from Customer
    public CustomerDTO getCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getCustomerNumber(), customer.getName(), customer.getEmail());
    }

    public Customer getCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getCustomerNumber(), customerDTO.getName(), customerDTO.getEmail());
         return customer;
    }
    public List<Customer> getAllCustomers(List<CustomerDTO> customerDTOS){
           List<Customer> customers = new ArrayList<>();
            for (CustomerDTO customerDTO : customerDTOS) {
                customers.add(getCustomer(customerDTO));
            }
            return customers;
    }
    public List<CustomerDTO> getAllCustomerDTOs(List<Customer> customers){

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(getCustomerDTO(customer));
        }
        return customerDTOS;
    }

}
