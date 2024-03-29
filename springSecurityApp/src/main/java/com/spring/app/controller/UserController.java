package com.spring.app.controller;

import com.spring.app.DAO.RoleDAO;
import com.spring.app.DAO.UserDAO;
import com.spring.app.entity.RoleEntity;
import com.spring.app.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.spring.app.config.SecurityConfig;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/auth")
@Slf4j
public class UserController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping(path = "/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody Map<String, Object> user) {
        List<RoleEntity> rolesEntity = roleDAO.findAll();
        log.info("Roles: {}", rolesEntity);
        List<String> roles = (List<String>) user.get("roles");
        // RoleEntity contains propeties id, name. I want to filter rolesEntity by name
        Set<RoleEntity> rolesUser = rolesEntity.stream().filter(roleEntity -> roles.contains(roleEntity.getRoleEnum().toString().toUpperCase()))
                .collect(Collectors.toSet());
        UserEntity userEntity = UserEntity.builder()
                .username(user.get("username").toString())
                .password(passwordEncoder.encode(user.get("password").toString()))
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .isEnable(true)
                .roles(rolesUser)
                .build();
        log.info("User to save: {}", userEntity);
        return ResponseEntity.ok(userDAO.save(userEntity));
    }
}
