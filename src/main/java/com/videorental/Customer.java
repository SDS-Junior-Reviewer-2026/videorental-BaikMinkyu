package com.videorental;

import java.util.ArrayList;
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
        return getStatementHeader()
				+ getRentalLineReport()
				+ getStatementFooter();
	}

	private String getStatementHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String getRentalLineReport() {
		StringBuilder result = new StringBuilder();
        for (Rental rental : rentals) {
            // determine amounts for each line
            // show figures
            result.append("\t").append(rental.getCharge()).append("(").append(rental.getMovie().getTitle()).append(")").append("\n");
        }
		return result.toString();
	}

	private String getStatementFooter() {
		return "Amount owed is " + getTotalAmount() + "\n" +
				"You earned " + getFrequentRenterPoints() + " frequent renter pointers";
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
			frequentRenterPoints += rental.getFrequentRenterPointsFor();
		}
		return frequentRenterPoints;
	}
}