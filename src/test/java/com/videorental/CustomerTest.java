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
    public void assertThatStatementIsEqual() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        // act
        String statement = customer.statement();

        // assert
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\nAmount owed is 0.0\nYou earned 0 frequent renter pointers", statement);
    }
}
