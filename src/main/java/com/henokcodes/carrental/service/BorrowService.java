package com.henokcodes.carrental.service;




import com.henokcodes.carrental.Domain.Borrow;
import com.henokcodes.carrental.Dto.BorrowDTO;
import com.henokcodes.carrental.adapter.BorrowAdapter;

import com.henokcodes.carrental.repository.BorrowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BorrowAdapter borrowAdapter;

    //add borrw
    public BorrowDTO addBorrow(BorrowDTO borrowDTO){

        Borrow borrow = borrowAdapter.getBorrow(borrowDTO);
        borrowRepository.save(borrow);
        return borrowDTO;
    }

    //remove borrow
    public void removeBorrow(String customerNumber, String carLicensePlate){
        Borrow borrow = borrowRepository.findByCustomerNumberAndCarLicensePlate(customerNumber, carLicensePlate);
        borrowRepository.delete(borrow);
    }
    //update borrow
    public BorrowDTO updateBorrow(BorrowDTO borrowDTO){
        Borrow borrow = borrowRepository.findByCustomerNumberAndCarLicensePlate(borrowDTO.getCustomerNumber(),borrowDTO.getCarLicensePlate());
        borrow.setPickupDate(borrowDTO.getPickupDate());
        borrow.setReturnDate(borrowDTO.getReturnDate());
        borrowRepository.save(borrow);
        return borrowDTO;
    }



    //get all borrows
    public List<BorrowDTO> getAllBorrows(){
        List<Borrow> borrows =  borrowRepository.findAll();
        System.out.println(borrows);
        List<BorrowDTO> borrowDTOs = (List<BorrowDTO>) borrowAdapter.getAllBorrowDTOs(borrows);
        return borrowDTOs;
    }
    // get borrow by customerNumber
    public List<BorrowDTO> getAllBorrowsByCustomerNumber(String customerNumber){
        List<Borrow> borrows = borrowRepository.findByCustomerNumber(customerNumber);
        List<BorrowDTO> borrowDTOs = borrowAdapter.getAllBorrowDTOs(borrows);
        return borrowDTOs;
    }







}
