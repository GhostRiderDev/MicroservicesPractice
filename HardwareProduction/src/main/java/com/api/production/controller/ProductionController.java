package com.api.production.controller;

import com.api.production.constants.ResponseConstants;
import com.api.production.service.ProductionService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.ProductionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/productions")
public class ProductionController {
    @Autowired
    private ProductionService productionService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduction(@RequestBody Map<String, Object> reqMap) {
        try {
            return productionService.addProduction(reqMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductionWrapper>> getProductions() {
        try {
            return productionService.getProductions();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionWrapper> getProduction(@PathVariable(name = "id") Integer id) {
        try {
            return productionService.getProduction(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    return new ResponseEntity<>(new ProductionWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeProduction(@PathVariable(name = "id") Integer id) {
        try {
            return productionService.deleteProduction(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduction(@PathVariable(name = "id") Integer id, @RequestBody Map<String, Object> reqMap) {
        try {
            return productionService.updateProduction(id, reqMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
