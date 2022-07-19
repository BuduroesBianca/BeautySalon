package com.example.beautysalon.service;

import com.example.beautysalon.model.Appointment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentService {


    public List<Appointment> getAppointmentsBySalonService(String name);
    public List<Appointment> getAppointmentsByStylist(Long id);
    public List<Appointment> getAppointmentsByDate(LocalDate date);
    public void createAppointment(String date, String hour, Integer duration, String state, Long customerId, Long employeeId, Long serviceId);
    public List<Appointment> getAllAppointments();
    public Appointment getAppointmentById(Long id);
    public void deleteAppointment(Long id);

    public Appointment updateAppointment(Long id, Appointment appointment);
}
