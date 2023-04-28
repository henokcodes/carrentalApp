package com.henokcodes.carrental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henokcodes.carrental.Dto.Car;
import com.henokcodes.carrental.Dto.Cars;
import com.henokcodes.carrental.Dto.CustomerDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ManagerService {

    private static String serverUrl="http://localhost:8082/api/v1/cars";
    RestTemplate restTemplate = new RestTemplate();




    public  void manager() throws JsonProcessingException {

        // add a new car
        Car car = new Car("TK135", "Van", "Toyota", "Red", 1000);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(car);
        System.out.println("Adding a new car");
        ResponseEntity<Car> response = restTemplate.postForEntity(serverUrl, car, Car.class);


        System.out.println("All Cars");
        Cars res = restTemplate.getForObject(
                serverUrl,
                Cars.class);
        List<Car> cars = res.getCars();
        System.out.println(cars);


        System.out.println("Car with license plate HO125 ");
        Car singleCar = (Car)restTemplate.getForObject(serverUrl+"/HO125", Car.class);
        System.out.println(singleCar);

        String type = "Van";
        String brand = "Toyota";
        System.out.println("Car with brand:"+ brand+" and type:"+type);

        //find by brand and type
        Cars resp = restTemplate.getForObject(
                serverUrl+"/search?brand="+brand+"&type="+type,
                Cars.class);
        List<Car> hCars = resp.getCars();
        if(hCars.isEmpty())
            System.out.println("No cars found");
        System.out.println(hCars.size()+" car(s) found");

        //delete a car
        System.out.println("Deleting a car");
        restTemplate.delete(serverUrl+"/TK135");

        //update a car
        System.out.println("Updating a car");

        singleCar.setBrand("Hyundai");
        restTemplate.put(serverUrl, singleCar);
        System.out.println("Car with license plate HO125 ");
        Object toyotaCars2 = restTemplate.getForObject(serverUrl+"/HO125", Car.class);
        System.out.println(toyotaCars2);

        System.out.println("All Cars");
        Cars all = restTemplate.getForObject(
                serverUrl,
                Cars.class);
        List<Car> allCa = all.getCars();
        System.out.println(allCa);

    }

}
