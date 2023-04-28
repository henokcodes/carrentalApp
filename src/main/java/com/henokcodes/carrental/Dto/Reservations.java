package com.henokcodes.carrental.Dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Reservations {
	private Collection<ReservationDTO> reservations;


	public Reservations() {
		reservations = new ArrayList<ReservationDTO>();
	}

	public Reservations(Collection<ReservationDTO> reservations) {
		this.reservations = reservations;
	}

	public Collection<ReservationDTO> getReservations() {
		return reservations;
	}
}
