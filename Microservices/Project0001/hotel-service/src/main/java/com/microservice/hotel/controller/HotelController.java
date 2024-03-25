package com.microservice.hotel.controller;

import com.microservice.hotel.model.HotelEntity;
import com.microservice.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<HotelEntity> getHotel(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(id));
    }

    @PostMapping
    public ResponseEntity<HotelEntity> saveHotel(@RequestBody HotelEntity hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
    }

    @GetMapping
    public ResponseEntity<Set<HotelEntity>> getHotels() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }
}
