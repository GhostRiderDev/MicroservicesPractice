package com.microservices.calification.DAO;

import com.microservices.calification.model.CalificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CalificationDAO extends MongoRepository<CalificationEntity, String> {

    List<CalificationEntity> findByIdUser(String idUser);
    List<CalificationEntity> findByIdHotel(String idHotel);
}
