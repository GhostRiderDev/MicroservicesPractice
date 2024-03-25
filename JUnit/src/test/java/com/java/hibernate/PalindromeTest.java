package com.java.hibernate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PalindromeTest {
    @ParameterizedTest
    @ValueSource(strings = {"madam", "liril", "radar"})
    public void testIsPalindrome(String word) {
        PalindromeCheck palindrome = new PalindromeCheck();
        boolean actual = palindrome.isPalindrome(word);
        assertTrue(actual);
    }
}
