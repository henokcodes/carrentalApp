package com.henokcodes.carrental;

import com.henokcodes.carrental.Domain.Borrow;
import com.henokcodes.carrental.Domain.Customer;
import com.henokcodes.carrental.Domain.Reservation;
import com.henokcodes.carrental.Dto.*;
import com.henokcodes.carrental.adapter.BorrowAdapter;
import com.henokcodes.carrental.adapter.CustomerAdapter;
import com.henokcodes.carrental.adapter.ReservationAdapter;
import com.henokcodes.carrental.repository.BorrowRepository;
import com.henokcodes.carrental.repository.CustomerRepository;
import com.henokcodes.carrental.repository.ReservationRepository;
import com.henokcodes.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EnableJms
@SpringBootApplication
public class CarrentalApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private BorrowRepository borrowRepository;
	@Autowired
	private ReservationAdapter reservationAdapter;
	@Autowired
	private CustomerAdapter customerAdapter;
	@Autowired
	ReservationService reservationService;
	@Autowired
	private BorrowAdapter borrowAdapter;

	private static String serverUrl="http://localhost:8082/api/v1/cars";

	public static void main(String[] args) {
		SpringApplication.run(CarrentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		System.out.println("All Cars");
		Cars res = restTemplate.getForObject(
				serverUrl,
				Cars.class);
		List<Car> cars = res.getCars();
		System.out.println(cars.get(1));


		System.out.println("Car with license plate GHT134 ");
		Object toyotaCars = restTemplate.getForObject(serverUrl+"/GHT134", Car.class);
		System.out.println(toyotaCars);

		CustomerDTO customerDTO = new CustomerDTO("123","Julie", "julie@domain.com");

		String type = "Van";
		String brand = "Hyundai";
		System.out.println("Car with brand Hyundai");

		Cars resp = restTemplate.getForObject(
				serverUrl+"/search?brand="+brand+"&type="+type,
				Cars.class);
		List<Car> hCars = resp.getCars();
		System.out.println(hCars.size());

		if (hCars.size()<){
			System.out.println("Less than 3 cars found");

		}

		Car carToRent = hCars.get(0);

		ReservationDTO reservationDTO = new ReservationDTO(customerDTO.getCustomerNumber(), carToRent.getLicensePlate(), LocalDate.now(), LocalDate.of(2023,03,20));
		BorrowDTO borrowDTO = new BorrowDTO(reservationDTO.getCustomerNumber(), reservationDTO.getCarLicensePlate(), LocalDate.now(), LocalDate.of(2023,03,20), carToRent.getPrice(), 2345);
		customerRepository.save(customerAdapter.getCustomer(customerDTO));
		reservationService.addReservation(reservationDTO);
//		reservationRepository.save(reservationAdapter.getReservation(reservationDTO));
		borrowRepository.save(borrowAdapter.getBorrow(borrowDTO));

		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer.toString());
		}
		System.out.println("Reservations");
		// print reservations
		for (Reservation reservation : reservationRepository.findAll()) {
			System.out.println(reservation.toString());
		}
		System.out.println("Borrows");
		// print borrows
		for (Borrow borrow : borrowRepository.findAll()) {
			System.out.println(borrow.toString());
		}

	}
}
