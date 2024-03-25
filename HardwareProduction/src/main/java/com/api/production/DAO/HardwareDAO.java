package com.api.production.DAO;

import com.api.production.model.HardwareEntity;
import com.api.production.wrapper.HardwareWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HardwareDAO extends JpaRepository<HardwareEntity, Integer> {
    HardwareWrapper findOne(@Param(("id"))Integer id);
    List<HardwareWrapper> getAll();
    List<HardwareWrapper> findByType(@Param(("type_id")) Integer type_id);
    List<HardwareWrapper> searchPaging(@Param(("keyword")) String keyword, Pageable pageable);
    Integer totalQuantity();
}
