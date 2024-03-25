package com.microservice.hotel.DAO;

import com.microservice.hotel.model.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDAO extends JpaRepository<HotelEntity, String> {
}
