package com.java.hibernate;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int actualValue = calculator.sum(2,3);
        int expectedValue = 5;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();
        int actualValue = calculator.multiply(20,3);
        int expectedValue = 60;
        assertEquals(expectedValue, actualValue);
    }
}
