package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("NAME_NOT_IMPORTANT");
    }

    @Test
    public void assertThatCustomerIsNotNull() {
        Assertions.assertNotNull(customer);
    }

    @Test
    public void testNoRental() {
        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\nAmount owed is 0.0\nYou earned 0 frequent renter pointers", statement);
    }

    @Test
    public void testRentalDaysRented2() {
        // arrange
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
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int daysRented = 1;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 3.0\nYou earned 1 frequent renter pointers", statement);
    }

    @Test
    public void testMoviePriceCodeChildrensRentalDaysRented4() {
        // arrange
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        int daysRented = 4;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 3.0\nYou earned 1 frequent renter pointers", statement);
    }

    @Test
    public void testMoviePriceCodeChildrensRentalDaysRented3() {
        // arrange
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        int daysRented = 3;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t1.5(TITLE_NOT_IMPORTANT)\nAmount owed is 1.5\nYou earned 1 frequent renter pointers", statement);
    }

    @Test
    public void testMoviePriceCodeNewReleaseRentalDaysRented2() {
        // arrange
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int daysRented = 2;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t6.0(TITLE_NOT_IMPORTANT)\nAmount owed is 6.0\nYou earned 2 frequent renter pointers", statement);
    }

    @Test
    public void testMovie3() {
        // arrange
        Movie regularMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        Movie newReleaseMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        Movie childrensMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 4));
        customer.addRental(new Rental(childrensMovie, 4));

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t2.0(TITLE_NOT_IMPORTANT)\n\t12.0(TITLE_NOT_IMPORTANT)\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 17.0\nYou earned 4 frequent renter pointers", statement);
    }
}