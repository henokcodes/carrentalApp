package com.henokcodes.carrental.service;




import com.henokcodes.carrental.Domain.Borrow;
import com.henokcodes.carrental.Dto.BorrowDTO;
import com.henokcodes.carrental.Dto.Borrows;
import com.henokcodes.carrental.adapter.BorrowAdapter;

import com.henokcodes.carrental.repository.BorrowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BorrowService {

    //add logger
    Logger logger = Logger.getLogger(BorrowService.class.getName());

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BorrowAdapter borrowAdapter;

    //add borrw
    public BorrowDTO addBorrow(BorrowDTO borrowDTO){

        Borrow borrow = borrowAdapter.getBorrow(borrowDTO);
        borrowRepository.save(borrow);
        logger.info("Borrow added");
        return borrowDTO;
    }

    //remove borrow
    public void removeBorrow(String customerNumber, String carLicensePlate){
        Borrow borrow = borrowRepository.findByCustomerNumberAndCarLicensePlate(customerNumber, carLicensePlate);
        logger.info("Borrow removed");
        borrowRepository.delete(borrow);
    }
    //update borrow
    public BorrowDTO updateBorrow(BorrowDTO borrowDTO){
        Borrow borrow = borrowRepository.findByCustomerNumberAndCarLicensePlate(borrowDTO.getCustomerNumber(),borrowDTO.getCarLicensePlate());
        borrow.setPickupDate(borrowDTO.getPickupDate());
        borrow.setReturnDate(borrowDTO.getReturnDate());
        borrowRepository.save(borrow);
        logger.info("Borrow updated");
        return borrowDTO;
    }



    //get all borrows
    public Borrows getAllBorrows(){
        Collection<Borrow> borrows =  borrowRepository.findAll();
        Collection<BorrowDTO> borrowDTOs = borrowAdapter.getAllBorrowDTOs(borrows);
        Borrows allBorrows = new Borrows(borrowDTOs);
        logger.info("All borrows returned");
        return allBorrows;
    }
    // get borrow by customerNumber
    public Borrows getAllBorrowsByCustomerNumber(String customerNumber){
        Collection<Borrow> borrows = borrowRepository.findByCustomerNumber(customerNumber);
        Collection<BorrowDTO> borrowDTOs = borrowAdapter.getAllBorrowDTOs(borrows);
        Borrows allBorrows = new Borrows(borrowDTOs);
        logger.info("All borrows by customer number returned");
        return allBorrows;
    }







}
