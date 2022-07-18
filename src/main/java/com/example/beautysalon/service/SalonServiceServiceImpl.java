package com.example.beautysalon.service;

import com.example.beautysalon.exception.SalonServiceNotFoundException;
import com.example.beautysalon.model.SalonService;
import com.example.beautysalon.repository.SalonServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceServiceImpl implements SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;

    public SalonServiceServiceImpl(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public SalonService addSalonService(SalonService salonService) {
        salonServiceRepository.save(salonService);
        return salonService;
    }

    @Override
    public List<SalonService> getAllSalonServices() {
            List<SalonService> salonServices = new ArrayList<>();
            salonServiceRepository.findAll().forEach(salonServices::add);

            return salonServices;
    }

    @Override
    public SalonService getSalonServiceById(Long serviceId) {
        return salonServiceRepository.findById(serviceId)
                .orElseThrow(() -> new SalonServiceNotFoundException(serviceId));
    }

    @Override
    public void deleteSalonService(Long serviceId) {
           SalonService salonService = salonServiceRepository.findById(serviceId)
                   .orElseThrow(() -> new SalonServiceNotFoundException(serviceId));
           salonServiceRepository.delete(salonService);
    }

    @Override
    public SalonService updateSalonService(Long serviceId, SalonService salonService) {
           SalonService updatedSalonService = salonServiceRepository.findById(serviceId).orElseThrow(() -> new SalonServiceNotFoundException(serviceId));

           updatedSalonService.setServiceId(serviceId);
           updatedSalonService.setName(salonService.getName() == null ? updatedSalonService.getName() : salonService.getName());
           updatedSalonService.setPrice(salonService.getPrice() == null ? updatedSalonService.getPrice() : salonService.getPrice());

           salonServiceRepository.save(updatedSalonService);

           return updatedSalonService;
       }

}

