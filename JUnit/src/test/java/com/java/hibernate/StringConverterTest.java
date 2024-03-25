package com.java.hibernate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringConverterTest {
    @Test
    public void testConverterToInt() {
        String str= null;
        assertThrows(IllegalArgumentException.class, () -> StringConverter.converterToInt(str));
    }
}
