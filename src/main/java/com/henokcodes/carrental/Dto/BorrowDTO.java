package com.henokcodes.carrental.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDTO {

    private String customerNumber;
    private String carLicensePlate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private long price;
    private int cardEndingNumber;
    private boolean isPaid;

    public BorrowDTO(String customerNumber, String carLicensePlate, LocalDate pickupDate, LocalDate returnDate, long price, int cardEndingNumber) {
        super();
        this.customerNumber = customerNumber;
        this.carLicensePlate = carLicensePlate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.price = price;
        this.cardEndingNumber = cardEndingNumber;
    }



}
