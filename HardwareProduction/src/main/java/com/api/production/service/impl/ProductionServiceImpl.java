package com.api.production.service.impl;

import com.api.production.DAO.HardwareDAO;
import com.api.production.DAO.ProductionDAO;
import com.api.production.DAO.RegionDAO;
import com.api.production.constants.ResponseConstants;
import com.api.production.model.HardwareEntity;
import com.api.production.model.ProductionEntity;
import com.api.production.model.RegionEntity;
import com.api.production.service.ProductionService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.ProductionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class ProductionServiceImpl implements ProductionService {
  @Autowired private ProductionDAO productionDAO;
  @Autowired private RegionDAO regionDAO;
  @Autowired private HardwareDAO hardwareDAO;

  @Override
  public ResponseEntity<String> addProduction(Map<String, Object> reqMap) {
    try {
      if (validReqMap(reqMap)) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse((String) reqMap.get("date"));
        ProductionEntity production = new ProductionEntity();
        Optional<RegionEntity> region = regionDAO.findById((Integer) reqMap.get("region_id"));
        Optional<HardwareEntity> hardware =
            hardwareDAO.findById((Integer) reqMap.get("hardware_id"));
        if (region.isPresent() && hardware.isPresent()) {
          production.setRegionEntity(region.get());
          production.setHardwareEntity(hardware.get());
          production.setQuantity((Integer) reqMap.get("quantity"));
          production.setProduction_date(date);
          productionDAO.save(production);
          return ResponseUtils.getResponseEntity("Production added sucess", HttpStatus.CREATED);
        } else {
          return ResponseUtils.getResponseEntity(
              "Not found region or hardware", HttpStatus.NOT_FOUND);
        }
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
  public ResponseEntity<ProductionWrapper> getProduction(Integer id) {
    try {
      ProductionWrapper productionWrapper = productionDAO.findOne(id);
      if (!Objects.isNull(productionWrapper)) {
        return new ResponseEntity<>(productionWrapper, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ProductionWrapper(), HttpStatus.NOT_FOUND);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new ProductionWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<List<ProductionWrapper>> getProductions() {
    try {
      return new ResponseEntity<>(productionDAO.getProductions(), HttpStatus.OK);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> updateProduction(Integer id, Map<String, Object> reqMap) {
    try {
      Optional<ProductionEntity> production = productionDAO.findById(id);
      if (production.isPresent()) {
        reqMap.put("id", production.get().getProduction_id());
        if (validReqMap(reqMap)) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
          Date date = dateFormat.parse((String) reqMap.get("date"));
          Optional<RegionEntity> region = regionDAO.findById((Integer) reqMap.get("region_id"));
          Optional<HardwareEntity> hardware =
              hardwareDAO.findById((Integer) reqMap.get("hardware_id"));
          if (region.isPresent() && hardware.isPresent()) {
              ProductionEntity productionDb =
                      ProductionEntity.builder()
                              .production_id((Integer) reqMap.get("id"))
                              .production_date(date)
                              .quantity((Integer) reqMap.get("quantity"))
                              .hardwareEntity(hardware.get())
                              .regionEntity(region.get())
                              .build();
              return ResponseUtils.getResponseEntity("Production updated sucess", HttpStatus.OK);
          }else {
              return ResponseUtils.getResponseEntity(
                      "Not found region or hardware", HttpStatus.NOT_FOUND);
          }
        }else {
            return ResponseUtils.getResponseEntity(
                    ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
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
  public ResponseEntity<String> deleteProduction(Integer id) {
    try {
        Optional<ProductionEntity> production = productionDAO.findById(id);
        if(production.isPresent()) {
            productionDAO.delete(production.get());
            return ResponseUtils.getResponseEntity("Production deleted success", HttpStatus.NO_CONTENT);
        } else {
            return ResponseUtils.getResponseEntity("Production not found", HttpStatus.NOT_FOUND);
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Boolean validReqMap(Map<String, Object> reqMap) {
    return (reqMap.containsKey("region_id")
        && reqMap.get("region_id") instanceof Integer
        && reqMap.containsKey("hardware_id")
        && reqMap.get("hardware_id") instanceof Integer
        && reqMap.containsKey("quantity")
        && reqMap.get("quantity") instanceof Integer
        && reqMap.containsKey("date")
        && reqMap.get("date") instanceof String);
  }
}
