package com.microservice.user.external.services;

import com.microservice.user.Entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/v1/hotels/{hotel}")
    Hotel getHotel(@PathVariable String hotel);
}
