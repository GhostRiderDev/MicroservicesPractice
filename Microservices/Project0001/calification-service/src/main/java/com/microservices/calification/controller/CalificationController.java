package com.microservices.calification.controller;

import com.microservices.calification.model.CalificationEntity;
import com.microservices.calification.service.CalificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/califications")
public class CalificationController {
    @Autowired
    private CalificationService calificationService;

    @PostMapping
    public ResponseEntity<CalificationEntity> saveCalification(@RequestBody CalificationEntity calification) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calificationService.saveCalification(calification));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CalificationEntity> getCalification(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(calificationService.getCalification(id));
    }

    @GetMapping
    public ResponseEntity<Set<CalificationEntity>> getCalifications() {
        return ResponseEntity.status(HttpStatus.OK).body(calificationService.getCalifications());
    }

    @GetMapping(path = "/hotel/{idHotel}")
    public ResponseEntity<List<CalificationEntity>> getAllByHotel(@PathVariable(name = "idHotel") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(calificationService.getByHotel(id));
    }

    @GetMapping(path = "/user/{idUser}")
    public ResponseEntity<List<CalificationEntity>> getAllByUser(@PathVariable(name = "idUser") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(calificationService.getByUser(id));
    }

}
