package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    public void assertThatCustomerIsNotNull() {
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        Assertions.assertNotNull(customer);
    }
}
