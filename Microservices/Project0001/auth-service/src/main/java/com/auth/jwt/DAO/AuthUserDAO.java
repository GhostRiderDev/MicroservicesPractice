package com.auth.jwt.DAO;

import com.auth.jwt.entity.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserDAO extends MongoRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsername(String username);
}
