package com.spring.app.service;

import com.spring.app.DAO.UserDAO;
import com.spring.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleEnum().getRoleName()));
        });
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> {
                    authorities.add(new SimpleGrantedAuthority(permission.getName().name()));
                });
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsEnable(),
                userEntity.getAccountNoExpired(),
                userEntity.getCredentialNoExpired(),
                userEntity.getAccountNoLocked(),
                authorities); // permissions
    }
}
