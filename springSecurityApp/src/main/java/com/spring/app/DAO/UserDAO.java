package com.spring.app.DAO;
import com.spring.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u  where u.username=:username")
    public Optional<UserEntity> findByUsername(String username);


}
