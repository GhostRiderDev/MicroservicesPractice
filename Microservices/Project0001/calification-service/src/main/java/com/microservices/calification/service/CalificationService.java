package com.microservices.calification.service;

import com.microservices.calification.model.CalificationEntity;

import java.util.List;
import java.util.Set;

public interface CalificationService {
    CalificationEntity saveCalification(CalificationEntity calification);
    CalificationEntity getCalification(String id);
    Set<CalificationEntity> getCalifications();
    List<CalificationEntity> getByHotel(String id);
    List<CalificationEntity> getByUser(String id);
}
