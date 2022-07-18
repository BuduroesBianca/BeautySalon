package com.example.beautysalon.service;

import com.example.beautysalon.model.SalonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalonServiceService {

    public SalonService addSalonService(SalonService salonService);
    public List<SalonService> getAllSalonServices();
    public SalonService getSalonServiceById(Long serviceId);
    public void deleteSalonService(Long serviceId);
    public SalonService updateSalonService(Long serviceId, SalonService salonService);

}
