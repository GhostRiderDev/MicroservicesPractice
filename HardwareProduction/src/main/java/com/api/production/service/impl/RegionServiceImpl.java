package com.api.production.service.impl;

import com.api.production.DAO.RegionDAO;
import com.api.production.constants.ResponseConstants;
import com.api.production.model.RegionEntity;
import com.api.production.service.RegionService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.RegionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {
  @Autowired private RegionDAO regionDAO;

  @Override
  public ResponseEntity<String> addRegion(Map<String, Object> reqMap) {
    try {
      if (validateReqMap(reqMap)) {
        RegionEntity regionEntity =
                RegionEntity.builder().region_name((String) reqMap.get("region_name")).build();
        regionDAO.save(regionEntity);
        return ResponseUtils.getResponseEntity("Region added succes", HttpStatus.CREATED);
      } else {
        return ResponseUtils.getResponseEntity(
                ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(
            ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> updateRegion(Integer id, Map<String, Object> reqMap) {
    log.info("dentro de updateRegion {} {}", reqMap, id);
    try {
      Optional<RegionEntity> regionEntity = regionDAO.findById(id);
      if (regionEntity.isPresent()) {
        reqMap.put("region_id", id);
        if (validateReqMap(reqMap)) {
          regionEntity
              .get()
              .setRegion_name((String) reqMap.get((String) reqMap.get("region_name")));
          regionDAO.save(regionEntity.get());
          return ResponseUtils.getResponseEntity("Region update sucess", HttpStatus.OK);
        } else {
          return ResponseUtils.getResponseEntity(
              ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
      } else {
        return ResponseUtils.getResponseEntity("Region not foung", HttpStatus.NOT_FOUND);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(
        ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> deleteRegion(Integer id) {
    try {
        Optional<RegionEntity> regionEntity = regionDAO.findById(id);
        if(regionEntity.isPresent()) {
            regionDAO.deleteById(id);
            return ResponseUtils.getResponseEntity("Region delete success", HttpStatus.NO_CONTENT);
        } else {
            return ResponseUtils.getResponseEntity("Region not found", HttpStatus.NOT_FOUND);
        }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
      return ResponseUtils.getResponseEntity(
              ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @Override
  public ResponseEntity<RegionWrapper> getRegion(Integer id) {
    try {
      RegionWrapper regionWrapper = regionDAO.findOne(id);
      if (!Objects.isNull(regionWrapper)) {
        return new ResponseEntity<>(regionWrapper, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new RegionWrapper(), HttpStatus.NOT_FOUND);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new RegionWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<List<RegionWrapper>> getRegions() {
    try {
      return new ResponseEntity<>(regionDAO.getRegions(), HttpStatus.OK);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Boolean validateReqMap(Map<String, Object> reqMap) {
    if (reqMap.containsKey("region_name") && reqMap.get("region_name") instanceof String) {
      return true;
    }
    return false;
  }
}
