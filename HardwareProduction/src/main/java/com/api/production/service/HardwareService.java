package com.api.production.service;

import com.api.production.model.HardwareEntity;
import com.api.production.wrapper.HardwareWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface HardwareService {
    ResponseEntity<String> addHardware(Map<String, Object> reqMap);
    ResponseEntity<HardwareWrapper> getHardware(Integer id);
    ResponseEntity<List<HardwareWrapper>> getAllHardware();
    ResponseEntity<String> updateHardware(Integer id, Map<String, Object> reqMap);
    ResponseEntity<String> deleteHardware(Integer id);
    ResponseEntity<List<HardwareWrapper>> findByType(Integer type_id);
}
