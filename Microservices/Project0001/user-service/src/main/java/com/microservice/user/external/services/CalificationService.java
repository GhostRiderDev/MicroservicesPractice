package com.microservice.user.external.services;

import com.microservice.user.Entity.Calification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CALIFICATION-SERVICE")
public interface CalificationService {
    @GetMapping("/api/v1/califications/{id}")
    public Calification getCalification(@PathVariable(name = "id") String id);

    @PostMapping("/api/v1/califications")
    public ResponseEntity<Calification> saveCalification(Calification calification);
}
