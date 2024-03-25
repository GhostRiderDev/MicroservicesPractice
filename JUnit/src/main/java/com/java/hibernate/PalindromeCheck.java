package com.java.hibernate;

public class PalindromeCheck {
    public boolean isPalindrome(String word) {
        StringBuilder reverse = new StringBuilder();
        for (int i = word.length() - 1; i >= 0 ; i--){
            reverse.append(word.charAt(i));
        }
        return word.contentEquals(reverse);
    }
}
