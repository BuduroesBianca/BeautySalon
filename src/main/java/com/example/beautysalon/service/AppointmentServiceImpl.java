package com.example.beautysalon.service;

import com.example.beautysalon.exception.AppointmentNotFoundException;
import com.example.beautysalon.exception.EmployeeNotFoundException;
import com.example.beautysalon.exception.SalonServiceNotFoundException;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.SalonService;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.repository.SalonServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final EmployeeRepository employeeRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, SalonServiceRepository salonServiceRepository, EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Appointment> getAppointmentsBySalonService(String name) {
        SalonService salonService = salonServiceRepository.findByName(name)
                .orElseThrow(() -> new SalonServiceNotFoundException(name));

        return appointmentRepository.findBySalonService(salonService)
                .orElseThrow(() -> new AppointmentNotFoundException(salonService));
    }

    @Override
    public List<Appointment> getAppointmentsByStylist(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return appointmentRepository.findByEmployee(employee)
                .orElseThrow(() -> new AppointmentNotFoundException(employee));
    }

    @Override
    public List<Appointment> getAppointmentsByDate(Date date) {
        return appointmentRepository.findByDate(date)
                .orElseThrow(() -> new AppointmentNotFoundException(date));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));

        appointment.setState("cancelled");
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        // TO DO
        return null;
    }
}
