package com.api.production.service;

import com.api.production.wrapper.RegionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RegionService {
    ResponseEntity<String> addRegion(Map<String, Object> reqMap);
    ResponseEntity<RegionWrapper> getRegion(Integer id);
    ResponseEntity<List<RegionWrapper>> getRegions();
    ResponseEntity<String> updateRegion(Integer id, Map<String, Object> reqMap);
    ResponseEntity<String> deleteRegion(Integer id);
}
