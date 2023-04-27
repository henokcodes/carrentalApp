package com.henokcodes.carrental.adapter;



import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.ReservationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationAdapter {

    // get ReservationDTO from Reservation
    public ReservationDTO getReservationDTO(Reservation reservation){
        return new ReservationDTO( reservation.getCustomerNumber(), reservation.getCarLicensePlate(),reservation.getPickupDate(),reservation.getReturnDate());
    }
    //get Reservation from ReservationDTO
    public Reservation getReservation(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation( reservationDTO.getCustomerNumber(), reservationDTO.getCarLicensePlate(),reservationDTO.getPickupDate() , reservationDTO.getReturnDate());
         return reservation;
    }
    //get all reservations from all ReservationDTOs
    public List<Reservation> getAllReservations(List<ReservationDTO> reservationDTOs){
           List<Reservation> reservations = new ArrayList<>();
            for (ReservationDTO reservationDTO : reservationDTOs) {
                reservations.add(getReservation(reservationDTO));
            }
            return reservations;
    }
    //get all ReservationDTOs from all reservations
    public List<ReservationDTO> getAllReservationDTOs(List<Reservation> reservations){

        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOs.add(getReservationDTO(reservation));
        }
        return reservationDTOs;
    }



}
