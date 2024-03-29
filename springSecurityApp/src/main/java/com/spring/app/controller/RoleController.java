package com.spring.app.controller;


import com.spring.app.DAO.PermissionDAO;
import com.spring.app.DAO.RoleDAO;
import com.spring.app.entity.PermissionEntity;
import com.spring.app.entity.PermissionEnum;
import com.spring.app.entity.RoleEntity;
import com.spring.app.entity.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "/roles")
@Slf4j
public class RoleController {
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @PostMapping(path = "/add")
    public ResponseEntity<RoleEntity> addRole(@RequestBody Map<String, Object> role) {
        List<String> permissions = (List<String>) role.get("permissions");
        Set<PermissionEntity> permissionsEntity =  new HashSet<>();
        permissions.forEach(permission -> {
            PermissionEnum permissionEnum = PermissionEnum.valueOf(permission.toUpperCase());
            permissionsEntity.add(permissionDAO.findByName(permissionEnum));
        });
        RoleEntity roleEntity = RoleEntity.builder()
                .roleEnum(RoleEnum.valueOf(role.get("name").toString().toUpperCase()))
                .permissions(permissionsEntity)
                .build();
        RoleEntity roleSaved = roleDAO.save(roleEntity);
        return ResponseEntity.ok(roleSaved);
    }
}
