package com.microservice.user.DAO;

import com.microservice.user.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserEntity, String> {
}
