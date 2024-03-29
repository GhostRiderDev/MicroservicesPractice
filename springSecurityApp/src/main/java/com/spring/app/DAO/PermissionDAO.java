package com.spring.app.DAO;
import com.spring.app.entity.PermissionEntity;
import com.spring.app.entity.PermissionEnum;
import com.spring.app.entity.RoleEnum;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface PermissionDAO extends JpaRepository<PermissionEntity, Long> {

    @Query(value = "SELECT p FROM  PermissionEntity p where p.name =:name")
    PermissionEntity findByName(PermissionEnum name);
}

