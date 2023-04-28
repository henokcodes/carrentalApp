package com.henokcodes.carrental.service;

import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.Car;
import com.henokcodes.carrental.Dto.ReservationDTO;
import com.henokcodes.carrental.Dto.Reservations;
import com.henokcodes.carrental.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testAddReservation() {
        // create a new ReservationDTO object
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerNumber("12345");
        reservationDTO.setCarLicensePlate("ABC123");
        reservationDTO.setPickupDate(LocalDate.now());
        reservationDTO.setReturnDate(LocalDate.now().plusDays(3));

        // call the addReservation method
        ReservationDTO addedReservationDTO = reservationService.addReservation(reservationDTO);

        // verify that the returned ReservationDTO object is not null
        assertNotNull(addedReservationDTO);

        // verify that the reservation was added to the repository
        Reservation addedReservation = reservationRepository.findByCustomerNumberAndCarLicensePlate("12345", "ABC123");
        assertNotNull(addedReservation);
    }

    @Test
    public void testGetCarByBrandAndType() {
        // call the getCarByBrandAndType method with known brand and type parameters
        Car car = reservationService.getCarByBrandAndType("Toyota", "SUV");

        // verify that the returned Car object is not null
        assertNotNull(car);

        // verify that the returned Car object has the expected brand and type
        assertEquals("Toyota", car.getBrand());
        assertEquals("SUV", car.getType());
    }

    @Test
    public void testRemoveReservation() {
        // create a new ReservationDTO object and add it to the repository
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerNumber("12345");
        reservationDTO.setCarLicensePlate("ABC123");
        reservationDTO.setPickupDate(LocalDate.now());
        reservationDTO.setReturnDate(LocalDate.now().plusDays(3));
        reservationService.addReservation(reservationDTO);

        // call the removeReservation method with the same parameters
        reservationService.removeReservation("12345", "ABC123");

        // verify that the reservation was removed from the repository
        Reservation removedReservation = reservationRepository.findByCustomerNumberAndCarLicensePlate("12345", "ABC123");
        assertNull(removedReservation);
    }

    @Test
    public void testUpdateReservation() {
        // create a new ReservationDTO object and add it to the repository
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerNumber("12345");
        reservationDTO.setCarLicensePlate("ABC123");
        reservationDTO.setPickupDate(LocalDate.now());
        reservationDTO.setReturnDate(LocalDate.now().plusDays(3));
        reservationService.addReservation(reservationDTO);

        // update the return date of the reservation
        reservationDTO.setReturnDate(LocalDate.now().plusDays(5));
        reservationService.updateReservation(reservationDTO);

        // verify that the reservation was updated in the repository
        Reservation updatedReservation = reservationRepository.findByCustomerNumberAndCarLicensePlate("12345", "ABC123");
        assertNotNull(updatedReservation);
        assertEquals(LocalDate.now().plusDays(5), updatedReservation.getReturnDate());
    }

    @Test
    public void testGetAllReservations() {
        // call the getAllReservations method
        Reservations reservations = reservationService.getAllReservations();

        // verify that the returned Reservations object is not null and contains at least one reservation
        assertNotNull(reservations);
        assertTrue(reservations.getReservations().size() > 0);
    }

    @Test
    public void testGetReservationByCustomerNumber() {
        // call the getReservationByCustomerNumber method with a known customer number
        Reservations reservations = reservationService.getAllReservationsByCustomerNumber("12345");

        // verify that the returned Reservations object is not null and contains at least one reservation
        assertNotNull(reservations);
        assertTrue(reservations.getReservations().size() > 0);
    }

}