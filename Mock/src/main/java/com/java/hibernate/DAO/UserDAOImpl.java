package com.java.hibernate.DAO;

public class UserDAOImpl implements UserDAO{
    @Override
    public String findNameById(Integer id) {
        return "cos4h";
    }

    @Override
    public String findEmailById(Integer id) {
        return "cos4h@gmail.com";
    }
}
