package com.auth.jwt.service;

import com.auth.jwt.DAO.AuthUserDAO;
import com.auth.jwt.DTO.NewUserDTO;
import com.auth.jwt.DTO.RequestDTO;
import com.auth.jwt.DTO.TokenDTO;
import com.auth.jwt.DTO.AuthUserDTO;
import com.auth.jwt.entity.AuthUser;
import com.auth.jwt.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthUserDAO authUserDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthUser save(NewUserDTO dto) {
        Optional<AuthUser> user = authUserDAO.findByUsername(dto.getUsername());
        if(user.isPresent()) {
            return null;
        } else  {
            String password = passwordEncoder.encode(dto.getPassword());
            AuthUser authUser = AuthUser.builder()
                    .username(dto.getUsername())
                    .password(password)
                    .role(dto.getRole())
                    .build();
            return authUserDAO.save(authUser);
        }
    }

    public TokenDTO login(AuthUserDTO dto) {
        Optional<AuthUser> user = authUserDAO.findByUsername(dto.getUsername());
        if (user.isEmpty()) {
            return null;
        }
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            return new TokenDTO(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDTO validate(String token, RequestDTO requestDTO) {
        if(!jwtProvider.validate(token, requestDTO)) {
            return null;
        }
        String username = jwtProvider.getUsernameFromToken(token);
        if(!authUserDAO.findByUsername(username).isPresent()) {
            return null;
        }
        return new TokenDTO(token);
    }
}
