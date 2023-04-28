package com.henokcodes.carrental.Dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Customers {
	private Collection<CustomerDTO> customers;


	public Customers() {
		customers = new ArrayList<CustomerDTO>();
	}

	public Customers(Collection<CustomerDTO> customers) {
		this.customers = customers;
	}

	public Collection<CustomerDTO> getCustomers() {
		return customers;
	}
		
}
