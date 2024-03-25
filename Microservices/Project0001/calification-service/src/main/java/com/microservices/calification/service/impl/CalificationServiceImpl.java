package com.microservices.calification.service.impl;

import com.microservices.calification.DAO.CalificationDAO;
import com.microservices.calification.exception.ResourceNotFoundException;
import com.microservices.calification.model.CalificationEntity;
import com.microservices.calification.service.CalificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CalificationServiceImpl implements CalificationService {
    @Autowired
    private CalificationDAO calificationDAO;
    @Override
    public CalificationEntity saveCalification(CalificationEntity calification) {
        calification.setIdCalification(UUID.randomUUID().toString());
        return calificationDAO.save(calification);
    }

    @Override
    public CalificationEntity getCalification(String id) {
        return calificationDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Calification not found"));
    }

    @Override
    public Set<CalificationEntity> getCalifications() {
        return Set.copyOf(calificationDAO.findAll());
    }

    @Override
    public List<CalificationEntity> getByHotel(String id) {
        return calificationDAO.findByIdHotel(id);
    }

    @Override
    public List<CalificationEntity> getByUser(String id) {
        return calificationDAO.findByIdUser(id);
    }
}
