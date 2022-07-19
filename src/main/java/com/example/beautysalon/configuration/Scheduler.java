package com.example.beautysalon.configuration;

import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.service.AppointmentService;
import com.example.beautysalon.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class Scheduler {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    EmailServiceImpl emailService;

    @Scheduled(cron = "0 0 8 * * *") // task executed everyday at 8 AM
    public void scheduleTaskUsingCronExpression() {

        LocalDate now = LocalDate.now();
        List<Appointment> list = appointmentService.getAppointmentsByDate(now.plusDays(1));

        Map<String, List<Appointment>> customerAppointments = new HashMap<>();

        for (Appointment appointment : list) {
            String currentEmail = appointment.getCustomer().getEmail();
            if(customerAppointments.containsKey(currentEmail)) {
                customerAppointments.get(currentEmail).add(appointment);
            }
            else {
                List<Appointment> appointments = new ArrayList<>();
                appointments.add(appointment);
                customerAppointments.put(currentEmail, appointments);
            }
        }

        for(Map.Entry<String, List<Appointment>> entry : customerAppointments.entrySet()) {
            String message = "";
            if(entry.getValue().size() > 1) {
                message = emailService.composeMultipleAppointmentsMessage(entry.getValue());
            }
            else {
                message = emailService.composeSimpleMessage(entry.getValue().get(0));
            }

            emailService.sendAppointmentIncomingNotification(entry.getKey(), message);
        }
    }
}
