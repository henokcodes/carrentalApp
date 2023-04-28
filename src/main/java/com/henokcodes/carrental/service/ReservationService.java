package com.henokcodes.carrental.service;



import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.Car;
import com.henokcodes.carrental.Dto.Cars;
import com.henokcodes.carrental.Dto.ReservationDTO;
import com.henokcodes.carrental.Dto.Reservations;
import com.henokcodes.carrental.adapter.ReservationAdapter;
import com.henokcodes.carrental.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationAdapter reservationAdapter;

    Logger  logger = Logger.getLogger(ReservationService.class.getName());
    private static String serverUrl="http://localhost:8082/api/v1/cars";
    RestTemplate restTemplate = new RestTemplate();

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
        logger.info("Reservation added");
        return reservationDTO;
    }

    //get car by brand and type
    public Car getCarByBrandAndType(String brand, String type){
        Cars res = restTemplate.getForObject(
                serverUrl+"/search?brand="+brand+"&type="+type+"",
                Cars.class);
        List<Car> cars = res.getCars();
        Car car = cars.get(0);
logger.info("Car found");
        return car;
    }


    //remove reservation
    public void removeReservation(String customerNumber, String carLicensePlate){
        Reservation reservation = reservationRepository.findByCustomerNumberAndCarLicensePlate(customerNumber, carLicensePlate);
        logger.info("Reservation removed");
        reservationRepository.delete(reservation);
    }
    //update reservation
    public ReservationDTO updateReservation(ReservationDTO reservationDTO){
        Reservation reservation = reservationRepository.findByCustomerNumberAndCarLicensePlate(reservationDTO.getCustomerNumber(),reservationDTO.getCarLicensePlate());
        reservation.setPickupDate(reservationDTO.getPickupDate());
        reservation.setReturnDate(reservationDTO.getReturnDate());
        reservationRepository.save(reservation);
        logger.info("Reservation updated");
        return reservationDTO;
    }

    //get all reservations
    public Reservations getAllReservations(){
        Collection<Reservation> reservations =  reservationRepository.findAll();
        Collection<ReservationDTO> reservationDTOs = reservationAdapter.getAllReservationDTOs(reservations);
        Reservations reservationDTOs1 = new Reservations(reservationDTOs);
        logger.info("All reservations found");
        return reservationDTOs1;
    }
    // get reservation by customerNumber
    public Reservations getAllReservationsByCustomerNumber(String customerNumber){
        Collection<Reservation> reservations = reservationRepository.findByCustomerNumber(customerNumber);
        Collection<ReservationDTO> reservationDTOs = reservationAdapter.getAllReservationDTOs(reservations);
        Reservations reservationDTOs1 = new Reservations(reservationDTOs);
        logger.info("All reservations found");
        return reservationDTOs1;
    }

    // get reservation by carLicensePlate
    public Reservations getAllReservationsByCarLicensePlate(String carLicensePlate){
        Collection<Reservation> reservations = reservationRepository.findByCarLicensePlate(carLicensePlate);
        Collection<ReservationDTO> reservationDTOs = reservationAdapter.getAllReservationDTOs(reservations);
        Reservations reservationDTOs1 = new Reservations(reservationDTOs);
        logger.info("All reservations found");
        return reservationDTOs1;
    }

    // get reservation by customerNumber and carLicensePlate
    public ReservationDTO getReservationByCustomerNumberAndCarLicensePlate(String customerNumber, String carLicensePlate){
        Reservation reservation = reservationRepository.findByCustomerNumberAndCarLicensePlate(customerNumber, carLicensePlate);
        ReservationDTO reservationDTO = reservationAdapter.getReservationDTO(reservation);
        logger.info("Reservation found");
        return reservationDTO;
    }





}
