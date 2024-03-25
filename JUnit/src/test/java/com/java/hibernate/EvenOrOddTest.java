package com.java.hibernate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class EvenOrOddTest {
    @ParameterizedTest
    @CsvSource(value = {"10, even", "5, odd", "20, even", "13, odd"})
    public void testOvenOrdOddNumber(Integer number, String expected) {
        EvenOrOdd evenOrOdd = new EvenOrOdd();
        String result = evenOrOdd.ovenOrdOddNumber(number);
        assertEquals(result, expected);
    }
}
