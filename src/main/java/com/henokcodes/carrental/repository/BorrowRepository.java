package com.henokcodes.carrental.repository;

import com.henokcodes.carrental.Domain.Borrow;
import com.henokcodes.carrental.Domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

        //find by customerNumber
        public List<Borrow> findByCustomerNumber(String customerNumber);
        //find by carLicensePlate
        public List<Borrow> findByCarLicensePlate(String carLicensePlate);


        public Borrow findByCustomerNumberAndCarLicensePlate(String customerNumber, String carLicensePlate);
        public List<Borrow> findByCustomerNumberAndPickupDate(String customerNumber, String pickupDate);
        public List<Borrow> findByCarLicensePlateAndPickupDate(String carLicensePlate, String pickupDate);


}
