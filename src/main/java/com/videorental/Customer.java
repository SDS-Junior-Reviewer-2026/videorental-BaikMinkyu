package com.videorental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		Iterator<Rental> iterator = rentals.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

		while ( iterator.hasNext() ) {
			Rental each = iterator.next();
			// determine amounts for each line
			// show figures
			result.append("\t").append(each.getCharge()).append("(").append(each.getMovie().getTitle()).append(")").append("\n");
		}

        result.append("Amount owed is ").append(getTotalAmount()).append("\n");
		result.append("You earned ").append(getFrequentRenterPoints()).append(" frequent renter pointers");

		return result.toString();
	}

	private double getTotalAmount() {
		double totalAmount = 0;
		for (Rental rental : rentals) {
			totalAmount += rental.getCharge();
		}
		return totalAmount;
	}

	private int getFrequentRenterPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			frequentRenterPoints += getFrequentRenterPointsFor(rental);
		}
		return frequentRenterPoints;
	}

	private int getFrequentRenterPointsFor(Rental rental) {
		// add frequent renter points
		// add bonus for a two days new release rental
		if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
			return 2;
		}

		return 1;
	}
}