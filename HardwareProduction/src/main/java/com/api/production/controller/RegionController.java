package com.api.production.controller;

import com.api.production.constants.ResponseConstants;
import com.api.production.service.RegionService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.RegionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/regions")
public class RegionController {
  @Autowired private RegionService regionService;

  @PostMapping("/add")
  public ResponseEntity<String> createRegion(@RequestBody Map<String, Object> reqMap) {
    try {
      return regionService.addRegion(reqMap);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return ResponseUtils.getResponseEntity(
        ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RegionWrapper> getRegion(@PathVariable(name = "id") Integer id) {
    try {
      return regionService.getRegion(id);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new RegionWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping
  public ResponseEntity<List<RegionWrapper>> getRegions() {
    try {
      return regionService.getRegions();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRegion(@PathVariable(name = "id") Integer id, @RequestBody Map<String, Object> reqMap) {
      try {
          return regionService.updateRegion(id, reqMap);
      } catch (Exception exception) {
          exception.printStackTrace();
      }
      return ResponseUtils.getResponseEntity(
              ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable(name = "id") Integer id) {
      try {
          return regionService.deleteRegion(id);
      } catch (Exception exception) {
          exception.printStackTrace();
      }
      return ResponseUtils.getResponseEntity(
              ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
