package com.spring.app.DAO;
import com.spring.app.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleDAO extends JpaRepository<RoleEntity, Integer> {
}
