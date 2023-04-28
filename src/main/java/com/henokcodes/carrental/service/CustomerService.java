package com.henokcodes.carrental.service;


import com.henokcodes.carrental.Domain.Customer;
import com.henokcodes.carrental.Dto.BorrowDTO;
import com.henokcodes.carrental.Dto.Borrows;
import com.henokcodes.carrental.Dto.CustomerDTO;
import com.henokcodes.carrental.Dto.Customers;
import com.henokcodes.carrental.adapter.BorrowAdapter;
import com.henokcodes.carrental.adapter.CustomerAdapter;
import com.henokcodes.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAdapter customerAdapter;

    @Autowired
            private BorrowAdapter borrowAdapter;

    Logger logger = Logger.getLogger(CustomerService.class.getName());


    //add customer
    public CustomerDTO addCustomer(CustomerDTO customerDTO){

        Customer customer = customerAdapter.getCustomer(customerDTO);
        customerRepository.save(customer);
        logger.info("Customer added");
        return customerDTO;
    }
    //remove customer
    public void removeCustomer(String customerNumber){
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);
        logger.info("Customer removed");
        customerRepository.delete(customer);
    }
    //update customer
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        Customer customer = customerRepository.findByCustomerNumber(customerDTO.getCustomerNumber());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customerRepository.save(customer);
        logger.info("Customer updated");
        return customerDTO;
    }

    //get all customers
    public Customers getAllCustomers(){
        Collection<Customer> customers =  customerRepository.findAll();
        Collection<CustomerDTO> customerDTOS = customerAdapter.getAllCustomerDTOs(customers);
        Customers allCustomers = new Customers(customerDTOS);
        logger.info("All customers returned");
        return allCustomers;

    }
    // get customer by customerNumber
    public CustomerDTO getCustomerByCustomerNumber(String customerNumber){
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);
        CustomerDTO customerDTO = customerAdapter.getCustomerDTO(customer);
        logger.info("Customer returned");
        return customerDTO;
    }
    //get customer by name
    public Customers getCustomersByName(String name){
        Collection<Customer> customer = customerRepository.findByName(name);
        Collection<CustomerDTO> customerDTOS = customerAdapter.getAllCustomerDTOs(customer);
        Customers allCustomers = new Customers(customerDTOS);
        logger.info("Customer returned");
        return allCustomers;
    }
    //get customer by email
    public Customers getCustomersByEmail(String email){

        Collection<Customer> customer = customerRepository.findByEmail(email);
        Collection<CustomerDTO> customerDTOS = customerAdapter.getAllCustomerDTOs(customer);
        Customers allCustomers = new Customers(customerDTOS);
        logger.info("Customer returned");
        return allCustomers;
    }

    //get customer and reservations by customerNumber
//    public CustomerDTO getCustomerAndReservationsByCustomerNumber(String customerNumber){
//        Customer customer = customerRepository.findByCustomerNumber(customerNumber);
//        CustomerDTO customerDTO = customerAdapter.getCustomerDTO(customer);
//        Collection<BorrowDTO> borrowDTOS = borrowAdapter.getAllBorrowDTOs(customer.getBorrows());
//        customerDTO.setBorrows(borrowDTOS);
//        logger.info("Customer and reservations returned");
//        return customerDTO;
//    }


}
