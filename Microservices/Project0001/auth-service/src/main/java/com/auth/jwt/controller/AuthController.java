package com.auth.jwt.controller;

import com.auth.jwt.DTO.NewUserDTO;
import com.auth.jwt.DTO.RequestDTO;
import com.auth.jwt.DTO.TokenDTO;
import com.auth.jwt.DTO.AuthUserDTO;
import com.auth.jwt.entity.AuthUser;
import com.auth.jwt.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO authUserDTO) {
        log.info("{}", authUserDTO);
        if (authUserDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        TokenDTO tokenDTO = authService.login(authUserDTO);
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token, @RequestBody RequestDTO requestDTO) {
        TokenDTO tokenDTO = authService.validate(token, requestDTO);
        if(tokenDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody NewUserDTO dto) {
        AuthUser authUser = authService.save((dto));
        if(authUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}
