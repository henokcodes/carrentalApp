package com.henokcodes.carrental.Dto;

import java.util.ArrayList;
import java.util.List;


public class Cars {
	private List<Car> cars;
	

	public Cars() {
		cars = new ArrayList<Car>();
	}

	public Cars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
		
}
