package com.henokcodes.carrental.repository;

import com.henokcodes.carrental.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

      //find by name
        public Collection<Customer> findByName(String name);
        //find by email
        public Collection<Customer> findByEmail(String email);
        //find by customerNumber
        public Customer findByCustomerNumber(String customerNumber);

        //get customer and reservation by customerNumber
  @Query(value = "SELECT * FROM customer c, reservation r WHERE c.customer_number = r.customer_number AND c.customer_number = ?1", nativeQuery = true)
          public Customer findByCustomerNumberAndReservations(String customerNumber);

}
