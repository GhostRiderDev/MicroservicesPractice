package com.microservice.hotel.service.impl;

import com.microservice.hotel.DAO.HotelDAO;
import com.microservice.hotel.exception.ResourceNotFoundException;
import com.microservice.hotel.model.HotelEntity;
import com.microservice.hotel.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDAO hotelDAO;



    @Override
    public HotelEntity saveHotel(HotelEntity hotel) {
        hotel.setIdHotel(UUID.randomUUID().toString());
        return hotelDAO.save(hotel);
    }

    @Override
    public Set<HotelEntity> getAllHotels() {
        return Set.copyOf(hotelDAO.findAll());
    }

    @Override
    public HotelEntity getHotel(String id) {
        return hotelDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found exception"));
    }
}
