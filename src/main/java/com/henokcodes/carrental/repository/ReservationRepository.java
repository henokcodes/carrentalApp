package com.henokcodes.carrental.repository;

import com.henokcodes.carrental.Domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


            public Collection<Reservation> findByCustomerNumber(String customerNumber);
            public Collection<Reservation> findByCarLicensePlate(String carLicensePlate);
          ;
            public Reservation findByCustomerNumberAndCarLicensePlate(String customerNumber, String carLicensePlate);


}
