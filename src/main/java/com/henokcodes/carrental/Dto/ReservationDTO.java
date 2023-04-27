package com.henokcodes.carrental.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private String customerNumber;
    private String carLicensePlate;
    private LocalDate pickupDate;
    private LocalDate returnDate;



}
