package com.api.production.DAO;

import com.api.production.model.RegionEntity;
import com.api.production.wrapper.RegionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionDAO extends JpaRepository<RegionEntity, Integer> {
    RegionWrapper findOne(@Param(("id")) Integer id);
    List<RegionWrapper> getRegions();
}
