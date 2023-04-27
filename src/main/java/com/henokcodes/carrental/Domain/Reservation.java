package com.henokcodes.carrental.Domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String customerNumber;
    private String carLicensePlate;
    private LocalDate pickupDate;
    private LocalDate returnDate;

    public Reservation(String customerNumber, String carLicensePlate, LocalDate pickupDate, LocalDate returnDate) {
        super();
        this.customerNumber = customerNumber;
        this.carLicensePlate = carLicensePlate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
    }



}
