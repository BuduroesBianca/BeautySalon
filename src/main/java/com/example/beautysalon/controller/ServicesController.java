package com.example.beautysalon.controller;

import com.example.beautysalon.model.SalonService;
import com.example.beautysalon.service.SalonServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServicesController {


    @Autowired
    SalonServiceService salonServiceService;

    @PostMapping("/salon-services")
    @ResponseStatus(HttpStatus.CREATED)
    public SalonService addSalonService(@RequestBody SalonService salonService) {
        return salonServiceService.addSalonService(salonService);
    }

    @GetMapping("/salon-services")
    public ResponseEntity<List<SalonService>> getAllServices() {
        return new ResponseEntity<>(salonServiceService.getAllSalonServices(), HttpStatus.OK);
    }

    @GetMapping("/salon-services/{id}")
    public ResponseEntity<SalonService> getSalonServiceById(@PathVariable Long id) {
        return new ResponseEntity<>(salonServiceService.getSalonServiceById(id), HttpStatus.OK);
    }

    @DeleteMapping("/salon-services/{id}")
    public ResponseEntity<HttpStatus> deleteSalonService(@PathVariable Long id) {
        salonServiceService.deleteSalonService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/salon-services/{id}")
    public ResponseEntity<SalonService> updateSalonService(@PathVariable Long id, @RequestBody SalonService salonService) {
        return new ResponseEntity<>(salonServiceService.updateSalonService(id, salonService), HttpStatus.OK);
    }
}
