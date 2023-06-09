package com.henokcodes.carrental.Domain;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String customerNumber;
    private String carLicensePlate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private long price;
    private int cardEndingNumber;
    private boolean isPaid=false;



    public Borrow(String customerNumber, String carLicensePlate, LocalDate pickupDate, LocalDate returnDate, long price, int cardEndingNumber, boolean isPaid) {
        super();
        this.customerNumber = customerNumber;
        this.carLicensePlate = carLicensePlate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.price = price;
        this.cardEndingNumber = cardEndingNumber;
        this.isPaid = isPaid;
    }
    public Borrow(String customerNumber, String carLicensePlate, LocalDate pickupDate, LocalDate returnDate, long price, int cardEndingNumber) {
        super();
        this.customerNumber = customerNumber;
        this.carLicensePlate = carLicensePlate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.price = price;
        this.cardEndingNumber = cardEndingNumber;
    }



}
