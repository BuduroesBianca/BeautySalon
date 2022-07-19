package com.example.beautysalon.controller;

import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.SalonService;
import com.example.beautysalon.service.AppointmentService;
import com.example.beautysalon.service.AppointmentServiceImpl;
import com.example.beautysalon.service.SalonServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;


    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.getAllAppointments(), HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.getAppointmentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/appointments/date")
    public ResponseEntity<List<Appointment>> getAppointmentByDate(@Param("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate appointmentDate = LocalDate.parse(date, formatter);

        return new ResponseEntity<>(appointmentService.getAppointmentsByDate(appointmentDate),HttpStatus.OK);
    }
    @GetMapping("/appointments/create")
    public ResponseEntity<HttpStatus> createAppointment(@Param("date") String date,@Param("hour") String hour,@Param("duration") Integer duration, @Param("state") String state,
                                                        @Param("customerId") Long customerId, @Param("employeeId") Long employeeId,@Param("serviceId") Long serviceId) {
        appointmentService.createAppointment(date, hour, duration, state, customerId, employeeId, serviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/appointments/{id}")
//    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
//        return appointmentService.updateAppointment(id, appointment);
//    }
}
