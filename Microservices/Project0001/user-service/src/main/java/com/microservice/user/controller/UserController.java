package com.microservice.user.controller;

import com.microservice.user.Entity.UserEntity;
import com.microservice.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<UserEntity>> getUsers() {
        log.info("Dentro de /all");
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }

    private  int attemptsNumbers = 1;

    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserEntity> getUser(@PathVariable(name = "id") String id) {
        log.info("Listando un solo usuario: userController");
        log.info("quantity of attempts: {}", attemptsNumbers);
        attemptsNumbers++;
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    public ResponseEntity<UserEntity> ratingHotelFallback(String idUser, Exception exception) {
        log.info("El respaldo se ejecuta porque el servicio esta inactivo:", exception);
        UserEntity user = UserEntity.builder()
                .email("root1@gmail.com")
                .name("root")
                .description("This user is create for default when some service is down")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
