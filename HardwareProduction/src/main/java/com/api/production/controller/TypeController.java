package com.api.production.controller;

import com.api.production.constants.ResponseConstants;
import com.api.production.service.TypeService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.TypeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/types")
@CrossOrigin(origins = "http://localhost:5173")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @PostMapping("/add")
    public ResponseEntity<String> addType(@RequestBody Map<String, String> reqMap) {
        try {
            return typeService.saveType(reqMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeWrapper> getTypeById(@PathVariable Integer id) {
        try {
            return typeService.getType(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new TypeWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TypeWrapper>> listAllTypes() {
        try {
            return typeService.getAllTypes();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateType(@PathVariable(name = "id") Integer id, @RequestBody Map<String, String> reqMap) {
        try {
            return typeService.updateType(id, reqMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteType(@PathVariable(name = "id") Integer id) {
        try {
            return typeService.deleteTypeById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
