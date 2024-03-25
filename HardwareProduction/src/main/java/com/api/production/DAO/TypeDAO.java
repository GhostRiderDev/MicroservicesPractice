package com.api.production.DAO;

import com.api.production.model.TypeEntity;
import com.api.production.wrapper.TypeWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDAO extends JpaRepository<TypeEntity, Integer> {
    TypeWrapper getType(@Param(("id")) Integer id);
    List<TypeWrapper> getAll();
}
