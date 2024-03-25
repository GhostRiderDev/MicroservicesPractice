package com.api.production.service.impl;

import com.api.production.DAO.HardwareDAO;
import com.api.production.DAO.TypeDAO;
import com.api.production.constants.ResponseConstants;
import com.api.production.model.HardwareEntity;
import com.api.production.model.TypeEntity;
import com.api.production.service.HardwareService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.HardwareWrapper;
import com.api.production.wrapper.TypeWrapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@Slf4j
public class HardwareServiceImpl implements HardwareService {
  @Autowired private TypeDAO typeDAO;

  @Autowired private HardwareDAO hardwareDAO;

  @Override
  public ResponseEntity<String> addHardware(Map<String, Object> reqMap) {
    log.info("dentro de addHardware {}", reqMap);
    try {
      return validReqMap(reqMap, "Hardware added success");
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(
        ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<HardwareWrapper> getHardware(Integer id) {
    log.info("dentro de getHardware");
    try {
      HardwareWrapper hardware = hardwareDAO.findOne(id);
      if (!Objects.isNull(hardware)) {
        log.info("si existe");
        return new ResponseEntity<>(hardware, HttpStatus.OK);
      }
      log.info("no existe");
      return new ResponseEntity<>(new HardwareWrapper(), HttpStatus.BAD_REQUEST);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new HardwareWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<List<HardwareWrapper>> getAllHardware() {
    log.info("dentro de getAllHardware");
    try {
      return new ResponseEntity<>(hardwareDAO.getAll(), HttpStatus.OK);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> updateHardware(Integer id, Map<String, Object> reqMap) {
    log.info("dentro  de updateHardware {}", reqMap);
    try {
      Optional<HardwareEntity> hardwareEntity = hardwareDAO.findById(id);
      if (hardwareEntity.isPresent()) {
        reqMap.put("hardware_id", id);
        return validReqMap(reqMap, "Hardware updated success");
      } else {

      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(
        ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> deleteHardware(Integer id) {
    log.info("Into deleteHardware {}", id);
    try {
      boolean flag = hardwareDAO.existsById(id);
      log.info("flag: {}", flag);
      if (flag) {
        hardwareDAO.deleteById(id);
        return ResponseUtils.getResponseEntity("Hardware deleted success", HttpStatus.NO_CONTENT);
      }
      return ResponseUtils.getResponseEntity(
          "Not found any hardware with id: " + id, HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(
        ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<List<HardwareWrapper>> findByType(Integer type_id) {
    log.info("dentro de findByType");
    try {
      return new ResponseEntity<>(hardwareDAO.findByType(type_id), HttpStatus.OK);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<String> validReqMap(Map<String, Object> reqMap, String message) {
    if (reqMap.containsKey("type_id")
        && reqMap.containsKey("unit_cost")
        && reqMap.containsKey("hardware_name")
        && reqMap.containsKey("image_url")) {
      if (reqMap.get("type_id") instanceof Integer
          && reqMap.get("hardware_name") instanceof String
          &&  reqMap.get("unit_cost")instanceof Number
          && reqMap.get("image_url") instanceof String) {
        Optional<TypeEntity> typeEntity = typeDAO.findById((Integer) reqMap.get("type_id"));
        if (typeEntity.isPresent()) {
          HardwareEntity hardwareEntity =
              HardwareEntity.builder()
                  .typeEntity(typeEntity.get())
                  .unit_cost((Double) reqMap.get("unit_cost"))
                  .hardware_name((String) reqMap.get("hardware_name"))
                  .image_url((String) reqMap.get("image_url"))
                  .build();
          if (reqMap.containsKey("hardware_id")) {
            hardwareEntity.setHardware_id((Integer) reqMap.get("hardware_id"));
          }
          hardwareDAO.save(hardwareEntity);
          return ResponseUtils.getResponseEntity(message, HttpStatus.CREATED);
        } else {
          return ResponseUtils.getResponseEntity(
              ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
      } else {
        return ResponseUtils.getResponseEntity(
            ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
      }
    } else {
      return ResponseUtils.getResponseEntity(
          ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }
  }
}
