package com.microservice.hotel.service;

import com.microservice.hotel.model.HotelEntity;

import java.util.Set;

public interface HotelService {
    HotelEntity saveHotel(HotelEntity hotel);
    Set<HotelEntity> getAllHotels();
    HotelEntity getHotel(String id);
}
