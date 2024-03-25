package com.api.production.service.impl;

import com.api.production.DAO.TypeDAO;
import com.api.production.constants.ResponseConstants;
import com.api.production.model.TypeEntity;
import com.api.production.service.TypeService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.TypeWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDAO typeDAO;
    @Override
    public ResponseEntity<String> saveType(Map<String, String> reqMap) {
        log.info("dentro de saveType");
        try {
            if (reqMap.containsKey("name")) {
                TypeEntity typeEntity = new TypeEntity();
                typeEntity.setType_name(reqMap.get("name"));
                typeDAO.save(typeEntity);
                return ResponseUtils.getResponseEntity("Type added success", HttpStatus.CREATED);
            }
            return ResponseUtils.getResponseEntity(ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<TypeWrapper> getType(Integer id) {
        log.info("dentro de getType");
        try {
            TypeWrapper typeWrapper =  typeDAO.getType(id);
            if(!Objects.isNull(typeWrapper)) {
                return new ResponseEntity<>(typeWrapper, HttpStatus.OK);
            }
            return new ResponseEntity<>(new TypeWrapper(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new TypeWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<TypeWrapper>> getAllTypes() {
        log.info("dentro  de getAllTypes");
        try {
            return new ResponseEntity<>(typeDAO.getAll(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateType(Integer id, Map<String, String> reqMap) {
        log.info("dentro  de updateType");
        try {
            Optional<TypeEntity> typeEntity = typeDAO.findById(id);
            if(typeEntity.isPresent()) {
                    if(reqMap.containsKey("name")) {
                        typeEntity.get().setType_name(reqMap.get("name"));
                        typeDAO.save(typeEntity.get());
                        return ResponseUtils.getResponseEntity("Type updated sucess", HttpStatus.OK);
                    } else {
                        return ResponseUtils.getResponseEntity(ResponseConstants.INVALID_DATA, HttpStatus.OK);
                    }
            } else {
                return ResponseUtils.getResponseEntity(ResponseConstants.INVALID_DATA, HttpStatus.OK);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTypeById(Integer id) {
        log.info("dentro de deleteTypeById");
        try {
            Optional<TypeEntity> typeEntity = typeDAO.findById(id);
            if(typeEntity.isPresent()) {
                typeDAO.deleteById(id);
                return new ResponseEntity<>("Type deleted success", HttpStatus.NO_CONTENT);
            } else {
                return ResponseUtils.getResponseEntity(ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.INVALID_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
