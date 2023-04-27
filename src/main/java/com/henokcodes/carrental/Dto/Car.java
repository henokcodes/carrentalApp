package com.henokcodes.carrental.Dto;

public class Car {

	private String licensePlate;
	private String type;
	private String brand;
	private String color;
	private long price;

	public Car() {

	}

	public Car(String licensePlate, String type, String brand, String color, long price) {
		this.licensePlate = licensePlate;
		this.type = type;
		this.brand = brand;
		this.color = color;
		this.price = price;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car{" +
				"licensePlate='" + licensePlate + '\'' +
				", type='" + type + '\'' +
				", brand='" + brand + '\'' +
				", color='" + color + '\'' +
				", price=" + price +
				'}';
	}
}
