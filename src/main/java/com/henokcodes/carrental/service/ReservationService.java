package com.henokcodes.carrental.service;



import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.ReservationDTO;
import com.henokcodes.carrental.adapter.ReservationAdapter;
import com.henokcodes.carrental.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationAdapter reservationAdapter;

//    @Autowired
//    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
//        jmsTemplate.convertAndSend("my-queue", message);
        System.out.println("Message sent");
    }
    //add reservation
    public ReservationDTO addReservation(ReservationDTO reservationDTO){

        Reservation reservation = reservationAdapter.getReservation(reservationDTO);
        reservationRepository.save(reservation);
//        sendMessage(reservationDTO.getCarLicensePlate());
        return reservationDTO;
    }


    //remove reservation
    public void removeReservation(String customerNumber, String carLicensePlate){
        Reservation reservation = reservationRepository.findByCustomerNumberAndCarLicensePlate(customerNumber, carLicensePlate);
        reservationRepository.delete(reservation);
    }
    //update reservation
    public ReservationDTO updateReservation(ReservationDTO reservationDTO){
        Reservation reservation = reservationRepository.findByCustomerNumberAndCarLicensePlate(reservationDTO.getCustomerNumber(),reservationDTO.getCarLicensePlate());
        reservation.setPickupDate(reservationDTO.getPickupDate());
        reservation.setReturnDate(reservationDTO.getReturnDate());
        reservationRepository.save(reservation);
        return reservationDTO;
    }

    //get all reservations
    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservations =  reservationRepository.findAll();
        System.out.println(reservations);
        List<ReservationDTO> reservationDTOs = (List<ReservationDTO>) reservationAdapter.getAllReservationDTOs(reservations);
        return reservationDTOs;
    }
    // get reservation by customerNumber
    public List<ReservationDTO> getAllReservationsByCustomerNumber(String customerNumber){
        List<Reservation> reservations = reservationRepository.findByCustomerNumber(customerNumber);
        List<ReservationDTO> reservationDTOs = reservationAdapter.getAllReservationDTOs(reservations);
        return reservationDTOs;
    }








}
