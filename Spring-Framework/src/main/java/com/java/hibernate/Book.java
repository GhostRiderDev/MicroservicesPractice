package com.java.hibernate;

public class Book {
    private int id_book;
    private String name;

    public Book(int id_book, String name) {
        this.id_book = id_book;
        this.name = name;
    }
    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id_book +
                ", name='" + name + '\'' +
                '}';
    }
}
