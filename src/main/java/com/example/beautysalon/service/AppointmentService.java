package com.example.beautysalon.service;

import com.example.beautysalon.model.Appointment;

import java.util.Date;
import java.util.List;

public interface AppointmentService {


    public List<Appointment> getAppointmentsBySalonService(String name);
    public List<Appointment> getAppointmentsByStylist(Long id);
    public List<Appointment> getAppointmentsByDate(Date date);
    public List<Appointment> getAllAppointments();
    public Appointment getAppointmentById(Long id);
    public void deleteAppointment(Long id);
    public Appointment updateAppointment(Long id, Appointment appointment);
}
