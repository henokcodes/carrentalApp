package com.henokcodes.carrental.Dto;

import com.henokcodes.carrental.Domain.Borrow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Borrows {
	private Collection<BorrowDTO> borrows;


	public Borrows() {
		borrows = new ArrayList<BorrowDTO>();
	}

	public Borrows(Collection<BorrowDTO> borrows) {
		this.borrows = borrows;
	}

	public Collection<BorrowDTO> getBorrows() {
		return borrows;
	}
		
}
