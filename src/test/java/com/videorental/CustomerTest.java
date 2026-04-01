package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    public void assertThatCustomerIsNotNull() {
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        Assertions.assertNotNull(customer);
    }

    @Test
    public void testNoRental() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\nAmount owed is 0.0\nYou earned 0 frequent renter pointers", statement);
    }

    @Test
    public void testRentalDaysRented2() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        int daysRented = 2;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t2.0(TITLE_NOT_IMPORTANT)\nAmount owed is 2.0\nYou earned 1 frequent renter pointers", statement);
    }

    @Test
    public void testRentalDaysRented3() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        int daysRented = 3;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.5(TITLE_NOT_IMPORTANT)\nAmount owed is 3.5\nYou earned 1 frequent renter pointers", statement);
    }

    @Test
    public void testMoviePriceCodeNewRelease() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int daysRented = 1;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 3.0\nYou earned 1 frequent renter pointers", statement);
    }
}
