package com.java.hibernate.service;

import com.java.hibernate.DAO.UserDAO;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public UserService(){}

    public String getNameById(Integer id) {
        String name = userDAO.findNameById(id);
        return name;
    }

    public String getEmailById(Integer id) {
        String email = userDAO.findNameById(id);
        return email;
    }


}
