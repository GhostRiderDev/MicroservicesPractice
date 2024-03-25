package com.microservice.user.service;

import com.microservice.user.Entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    Set<UserEntity> listAllUsers();
    UserEntity getUserById(String idUser);
}
