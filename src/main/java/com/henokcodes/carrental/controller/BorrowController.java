package com.henokcodes.carrental.controller;


import com.henokcodes.carrental.Domain.Borrow;
import com.henokcodes.carrental.Dto.*;
import com.henokcodes.carrental.adapter.BorrowAdapter;
import com.henokcodes.carrental.service.BorrowService;
import com.henokcodes.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BorrowAdapter borrowAdapter;

    //get all borrows
    @GetMapping
    public ResponseEntity<?> getAllBorrows(){
        Borrows borrows=  borrowService.getAllBorrows();
        return new ResponseEntity<Borrows>(borrows, HttpStatus.OK);
    }
    //add borrow
    @PostMapping
    public ResponseEntity<?> addBorrow(@RequestBody BorrowDTO borrowDTO){
        return new ResponseEntity<BorrowDTO>(borrowService.addBorrow(borrowDTO), HttpStatus.CREATED);
    }
    //update borrow
    @PutMapping
    public ResponseEntity<?> updateBorrow(@RequestBody BorrowDTO borrowDTO){
        return new ResponseEntity<BorrowDTO>(borrowService.updateBorrow(borrowDTO), HttpStatus.OK);
    }
    //remove borrow
    @DeleteMapping("/{customerNumber}/{carLicensePlate}")
    public void removeBorrow(@PathVariable String customerNumber, @PathVariable String carLicensePlate){

        borrowService.removeBorrow(customerNumber,carLicensePlate);

    }










}
