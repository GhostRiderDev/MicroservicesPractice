package com.api.production.DAO;

import com.api.production.model.ProductionEntity;
import com.api.production.wrapper.ProductionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionDAO extends JpaRepository<ProductionEntity, Integer> {
    ProductionWrapper findOne(@Param(("id")) Integer id);
    List<ProductionWrapper> getProductions();
}
