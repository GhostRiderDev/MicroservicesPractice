package com.microservice.user.service.impl;

import com.microservice.user.DAO.UserDAO;
import com.microservice.user.Entity.Calification;
import com.microservice.user.Entity.Hotel;
import com.microservice.user.Entity.UserEntity;
import com.microservice.user.exception.UserNotFoundException;
import com.microservice.user.external.services.HotelService;
import com.microservice.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public UserEntity saveUser(UserEntity user) {
        user.setUserId(UUID.randomUUID().toString());
        return userDAO.save(user);
    }

    @Override
    public Set<UserEntity> listAllUsers() {
        return Set.copyOf(userDAO.findAll());
    }

    @Override
    public UserEntity getUserById(String idUser) {
        UserEntity user = userDAO.findById(idUser).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el id: ".concat(idUser)));
        Calification[] calificationsUser = restTemplate.getForObject("http://CALIFICATION-SERVICE/api/v1/califications/user/".concat(user.getUserId()), Calification[].class);

        List<Calification> userCalifications = Arrays.stream(calificationsUser).collect(Collectors.toList());

        List<Calification> calificationList = userCalifications.stream().map(calification -> {
            System.out.println(calification.getIdCalification());

            //!! With RestTemplate
//*           ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/api/v1/hotel/".concat(calification.getIdHotel()), Hotel.class);
//*            Hotel hotel = forEntity.getBody();
//*            logger.info("Respuesta con estado de codigo en: {}", forEntity.getStatusCode());

            //!! With FeignClient
            Hotel hotel = hotelService.getHotel(calification.getIdHotel());

            calification.setHotel(hotel);
            return calification;
        }).collect(Collectors.toList());


        user.setCalifications(calificationList);
        return user;
    }
}
