package com.example.beautysalon.service;

import com.example.beautysalon.exception.AppointmentNotFoundException;
import com.example.beautysalon.exception.CustomerNotFoundException;
import com.example.beautysalon.exception.EmployeeNotFoundException;
import com.example.beautysalon.exception.SalonServiceNotFoundException;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.SalonService;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.CustomerRepository;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.repository.SalonServiceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, SalonServiceRepository salonServiceRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
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
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByDate(date)
                .orElseThrow(() -> new AppointmentNotFoundException(date));
    }

    @Override
    public void createAppointment(String date, String hour, Integer duration, String state, Long customerId, Long employeeId, Long serviceId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        SalonService salonService = salonServiceRepository.findById(serviceId)
                .orElseThrow(() -> new SalonServiceNotFoundException(serviceId));

        Appointment appointment = new Appointment();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        long ms = 0;
        try {
            ms = sdf.parse(hour).getTime();
        } catch (Exception e) {
            e.getMessage();
        }

        Time appointmentHour = new Time(ms);
        appointment.setHour(appointmentHour);
        System.out.println(appointmentHour);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate appointmentDate = LocalDate.parse(date, formatter);

        appointment.setDate(appointmentDate);
        System.out.println(appointmentDate);

        appointment.setState("pending");
        appointment.setCustomer(customer);
        appointment.setEmployee(employee);
        appointment.setSalonService(salonService);
        appointment.setDuration(duration);

        appointmentRepository.save(appointment);


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
