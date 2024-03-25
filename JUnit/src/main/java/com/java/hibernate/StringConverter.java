package com.java.hibernate;

public class StringConverter {
    public static Integer converterToInt(String string) {
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("Given string should not be null or empty");
        }
        return Integer.parseInt(string);
    }
}
