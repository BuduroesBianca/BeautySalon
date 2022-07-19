package com.example.beautysalon.repository;

import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<List<Appointment>> findByEmployee(Employee employee);
    Optional<List<Appointment>> findByDate(LocalDate date);
    Optional<List<Appointment>> findBySalonService(SalonService salonService);

    List<Appointment> findByDateAndSalonService(Date date, SalonService salonService);
    List<Appointment> findByDateAndEmployee(Date date, Employee employee);


}
