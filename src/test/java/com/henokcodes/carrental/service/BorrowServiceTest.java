package com.henokcodes.carrental.service;

import com.henokcodes.carrental.Domain.Borrow;
import com.henokcodes.carrental.Dto.BorrowDTO;
import com.henokcodes.carrental.Dto.Borrows;
import com.henokcodes.carrental.adapter.BorrowAdapter;
import com.henokcodes.carrental.repository.BorrowRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

    @RunWith(MockitoJUnitRunner.class)
    public class BorrowServiceTest {

        @Mock
        private BorrowRepository borrowRepository;

        @Mock
        private BorrowAdapter borrowAdapter;

        @InjectMocks
        private BorrowService borrowService;

        @Test
        public void addBorrowTest() {
            BorrowDTO borrowDTO = new BorrowDTO();
            Borrow borrow = new Borrow();
            when(borrowAdapter.getBorrow(borrowDTO)).thenReturn(borrow);
            when(borrowRepository.save(borrow)).thenReturn(borrow);
            BorrowDTO result = borrowService.addBorrow(borrowDTO);
            assertEquals(borrowDTO, result);
            verify(borrowAdapter).getBorrow(borrowDTO);
            verify(borrowRepository).save(borrow);
        }

        @Test
        public void removeBorrowTest() {
            String customerNumber = "123";
            String carLicensePlate = "ABC-123";
            Borrow borrow = new Borrow();
            when(borrowRepository.findByCustomerNumberAndCarLicensePlate(customerNumber, carLicensePlate)).thenReturn(borrow);
            borrowService.removeBorrow(customerNumber, carLicensePlate);
            verify(borrowRepository).delete(borrow);
        }

        @Test
        public void updateBorrowTest() {
            BorrowDTO borrowDTO = new BorrowDTO();
            borrowDTO.setCustomerNumber("123");
            borrowDTO.setCarLicensePlate("ABC-123");
            borrowDTO.setPickupDate(LocalDate.now());
            borrowDTO.setReturnDate(LocalDate.now().plusDays(1));
            Borrow borrow = new Borrow();
            borrow.setCustomerNumber("123");
            borrow.setCarLicensePlate("ABC-123");
            borrow.setPickupDate(LocalDate.now());
            borrow.setReturnDate(LocalDate.now().plusDays(1));
            when(borrowRepository.findByCustomerNumberAndCarLicensePlate(borrowDTO.getCustomerNumber(), borrowDTO.getCarLicensePlate())).thenReturn(borrow);
            when(borrowRepository.save(borrow)).thenReturn(borrow);
            BorrowDTO result = borrowService.updateBorrow(borrowDTO);
            assertEquals(borrowDTO, result);
            verify(borrowRepository).findByCustomerNumberAndCarLicensePlate(borrowDTO.getCustomerNumber(), borrowDTO.getCarLicensePlate());
            verify(borrowRepository).save(borrow);
        }

        @Test
        public void getAllBorrowsTest() {
            Collection<Borrow> borrows = Arrays.asList(new Borrow(), new Borrow());
            Collection<BorrowDTO> borrowDTOs = Arrays.asList(new BorrowDTO(), new BorrowDTO());
            Borrows allBorrows = new Borrows(borrowDTOs);
            when(borrowRepository.findAll()).thenReturn((List<Borrow>) borrows);
            when(borrowAdapter.getAllBorrowDTOs(borrows)).thenReturn(borrowDTOs);
            Borrows result = borrowService.getAllBorrows();
            assertEquals(allBorrows, result);
            verify(borrowRepository).findAll();
            verify(borrowAdapter).getAllBorrowDTOs(borrows);
        }

        @Test
        public void getAllBorrowsByCustomerNumberTest() {

            String customerNumber = "123";
            Collection<Borrow> borrows = Arrays.asList(new Borrow(), new Borrow());
            Collection<BorrowDTO> borrowDTOs = Arrays.asList(new BorrowDTO(), new BorrowDTO());
            Borrows allBorrows = new Borrows(borrowDTOs);
            when(borrowRepository.findByCustomerNumber(customerNumber)).thenReturn(borrows);
            when(borrowAdapter.getAllBorrowDTOs(borrows)).thenReturn(borrowDTOs);
            Borrows result = borrowService.getAllBorrowsByCustomerNumber(customerNumber);
            assertEquals(allBorrows, result);
            verify(borrowRepository).findByCustomerNumber(customerNumber);
            verify(borrowAdapter).getAllBorrowDTOs(borrows);

        }

        }