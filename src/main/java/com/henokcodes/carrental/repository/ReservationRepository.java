package com.henokcodes.carrental.repository;

import com.henokcodes.carrental.Domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


            public List<Reservation> findByCustomerNumber(String customerNumber);
            public List<Reservation> findByCarLicensePlate(String carLicensePlate);
            public List<Reservation> findByPickupDate(String pickupDate);
            public List<Reservation> findByReturnDate(String returnDate);
            public Reservation findByCustomerNumberAndCarLicensePlate(String customerNumber, String carLicensePlate);
            public List<Reservation> findByCustomerNumberAndPickupDate(String customerNumber, String pickupDate);
            public List<Reservation> findByCustomerNumberAndReturnDate(String customerNumber, String returnDate);
            public List<Reservation> findByCarLicensePlateAndPickupDate(String carLicensePlate, String pickupDate);

}
