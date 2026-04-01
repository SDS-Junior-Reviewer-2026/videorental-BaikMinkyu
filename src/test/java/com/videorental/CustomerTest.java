package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    private static final String NAME = "NAME_NOT_IMPORTANT";
    private static final String TITLE = "TITLE_NOT_IMPORTANT";

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(NAME);
    }

    @Test
    public void assertThatCustomerIsNotNull() {
        Assertions.assertNotNull(customer);
    }

    @Test
    public void testNoRental() {
        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\nAmount owed is 0.0\nYou earned 0 frequent renter pointers", customer.statement());
    }

    @Test
    public void testRentalDaysRented2() {
        // arrange
        customer.addRental(createRentalFor(2, Movie.REGULAR));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t2.0(TITLE_NOT_IMPORTANT)\nAmount owed is 2.0\nYou earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void testRentalDaysRented3() {
        // arrange
        customer.addRental(createRentalFor(3, Movie.REGULAR));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.5(TITLE_NOT_IMPORTANT)\nAmount owed is 3.5\nYou earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void testMoviePriceCodeNewRelease() {
        // arrange
        customer.addRental(createRentalFor(1, Movie.NEW_RELEASE));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 3.0\nYou earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void testMoviePriceCodeChildrensRentalDaysRented4() {
        // arrange
        customer.addRental(createRentalFor(4, Movie.CHILDRENS));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 3.0\nYou earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void testMoviePriceCodeChildrensRentalDaysRented3() {
        // arrange
        customer.addRental(createRentalFor(3, Movie.CHILDRENS));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t1.5(TITLE_NOT_IMPORTANT)\nAmount owed is 1.5\nYou earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void testMoviePriceCodeNewReleaseRentalDaysRented2() {
        // arrange
        customer.addRental(createRentalFor(2, Movie.NEW_RELEASE));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t6.0(TITLE_NOT_IMPORTANT)\nAmount owed is 6.0\nYou earned 2 frequent renter pointers", customer.statement());
    }

    @Test
    public void testMovie3() {
        // arrange
        customer.addRental(createRentalFor(1, Movie.REGULAR));
        customer.addRental(createRentalFor(4, Movie.NEW_RELEASE));
        customer.addRental(createRentalFor(4, Movie.CHILDRENS));

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n\t2.0(TITLE_NOT_IMPORTANT)\n\t12.0(TITLE_NOT_IMPORTANT)\n\t3.0(TITLE_NOT_IMPORTANT)\nAmount owed is 17.0\nYou earned 4 frequent renter pointers", customer.statement());
    }

    @Test
    public void testMovieSetPriceCode() {
        // arrange
        Rental rental = createRentalFor(1, Movie.REGULAR);
        rental.getMovie()
                .setPriceCode(Movie.NEW_RELEASE);

        // assert
        Assertions.assertEquals(Movie.NEW_RELEASE, rental.getMovie().getPriceCode());
    }

    private Rental createRentalFor(int daysRented, int priceCode) {
        return new Rental(getMovie(priceCode), daysRented);
    }

    private Movie getMovie(int priceCode) {
        return switch (priceCode) {
            case Movie.REGULAR -> new RegularMovie(TITLE);
            case Movie.NEW_RELEASE -> new NewReleaseMovie(TITLE);
            case Movie.CHILDRENS -> new ChildrensMovie(TITLE);
            default -> null;
        };
    }
}