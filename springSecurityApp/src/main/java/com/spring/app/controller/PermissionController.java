package com.spring.app.controller;

import com.spring.app.DAO.PermissionDAO;
import com.spring.app.entity.PermissionEntity;
import com.spring.app.entity.PermissionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/permissions")
public class PermissionController {
    @Autowired
    private PermissionDAO permissionDAO;
    @PostMapping(path = "/add")
    public ResponseEntity<PermissionEntity> addPermission(@RequestBody Map<String, Object> permission) {
        PermissionEntity permissionEntity = PermissionEntity.builder()
                .name(PermissionEnum.valueOf(permission.get("name").toString()))
                .build();
        PermissionEntity permissionBBDD = permissionDAO.save(permissionEntity);
        return ResponseEntity.ok(permissionBBDD);
    }
}
