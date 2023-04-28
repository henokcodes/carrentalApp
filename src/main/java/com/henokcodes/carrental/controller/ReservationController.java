package com.henokcodes.carrental.controller;


import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.Car;
import com.henokcodes.carrental.Dto.CustomerDTO;
import com.henokcodes.carrental.Dto.ReservationDTO;
import com.henokcodes.carrental.Dto.Reservations;
import com.henokcodes.carrental.service.CustomerService;
import com.henokcodes.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //get all reservations
    @GetMapping
    public ResponseEntity<?> getAllReservations(){
        return new ResponseEntity<Reservations>(reservationService.getAllReservations(), HttpStatus.OK);
    }
    //add reservation
    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody ReservationDTO reservationDTO){
        return new ResponseEntity<ReservationDTO>(reservationService.addReservation(reservationDTO), HttpStatus.CREATED);
    }
    //update reservation
    @PutMapping
    public ResponseEntity<?> updateReservation(@RequestBody ReservationDTO reservationDTO){
        return new ResponseEntity<ReservationDTO>(reservationService.updateReservation(reservationDTO), HttpStatus.OK);
    }
    //remove reservation
    @DeleteMapping("/{customerNumber}/{reservationNumber}")
    public void removeReservation(@PathVariable String customerNumber, @PathVariable String reservationNumber){
        reservationService.removeReservation(customerNumber,reservationNumber);
    }

    //get reservation by customerNumber and reservationNumber
    @GetMapping("/{customerNumber}/{reservationNumber}")
    public ResponseEntity<?> getReservationByCustomerNumberAndReservationNumber(@PathVariable String customerNumber, @PathVariable String reservationNumber){
        return new ResponseEntity<ReservationDTO>(reservationService.getReservationByCustomerNumberAndCarLicensePlate(customerNumber,reservationNumber), HttpStatus.OK);
    }

    //get car to rent
    @GetMapping("/search")
    public ResponseEntity<?> getCarToRent(@RequestParam String brand, @RequestParam String type){
        return new ResponseEntity<Car>(reservationService.getCarByBrandAndType(brand,type), HttpStatus.OK);
    }







}
