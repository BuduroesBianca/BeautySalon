package com.example.beautysalon.exception;

import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.SalonService;

import java.util.Date;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long id) {
        super("Could not find appointment with ID " + id);
    }

    public AppointmentNotFoundException(SalonService salonService) {
        super("Could not find appointment linked to salon service: " + salonService.getName());
    }

    public AppointmentNotFoundException(Employee employee) {
        super("There are no appointments at stylist " + employee.getEmail());
    }

    public AppointmentNotFoundException(Date date) {
        super("Could not find appointments on " + date.toString());
    }
}
