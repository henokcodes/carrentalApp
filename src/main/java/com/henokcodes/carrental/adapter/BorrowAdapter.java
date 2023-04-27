package com.henokcodes.carrental.adapter;



import com.henokcodes.carrental.Domain.Borrow;

import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.BorrowDTO;
import com.henokcodes.carrental.Dto.ReservationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowAdapter {

    // get BorrowDTO from Borrow
    public BorrowDTO getBorrowDTO(Borrow borrow){
        return new BorrowDTO(borrow.getCustomerNumber(), borrow.getCarLicensePlate(), borrow.getPickupDate(),borrow.getReturnDate(),borrow.getPrice(),borrow.getCardEndingNumber());
    }

    public Borrow getBorrow(BorrowDTO borrowDTO){
        return new Borrow(borrowDTO.getCustomerNumber(), borrowDTO.getCarLicensePlate(), borrowDTO.getPickupDate(),borrowDTO.getReturnDate(),borrowDTO.getPrice(),borrowDTO.getCardEndingNumber());
    }

    //get all borrows from all borrowDTOs
    public List<Borrow> getAllBorrows(List<BorrowDTO> borrowDTOS){
        List<Borrow> borrows = new ArrayList<>();
        for (BorrowDTO borrowDTO : borrowDTOS) {
            borrows.add(getBorrow(borrowDTO));
        }
        return borrows;
    }
    //get all BorrowDTOs from all borrows
    public List<BorrowDTO> getAllBorrowDTOs(List<Borrow> borrows){

        List<BorrowDTO> borrowDTOS = new ArrayList<>();
        for (Borrow borrow : borrows) {
            borrowDTOS.add(getBorrowDTO(borrow));
        }
        return borrowDTOS;
    }




}
