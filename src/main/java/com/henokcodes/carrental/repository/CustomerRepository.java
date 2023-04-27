package com.henokcodes.carrental.repository;

import com.henokcodes.carrental.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

      //find by name
        public List<Customer> findByName(String name);
        //find by email
        public List<Customer> findByEmail(String email);
        //find by customerNumber
        public Customer findByCustomerNumber(String customerNumber);

}
