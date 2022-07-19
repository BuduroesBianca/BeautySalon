package com.example.beautysalon.service;

import com.example.beautysalon.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendAppointmentIncomingNotification(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Beauty Salon Appointment Reminder");
        message.setText(text);
        emailSender.send(message);
    }

    public String composeSimpleMessage(Appointment appointment) {
        String service = appointment.getSalonService().getName();
        String hour = appointment.getHour().toString();
        String customerName = appointment.getCustomer().getFirstName();
        String message = "Hello " + customerName + "! \n\nYou have an incoming appointment tomorrow, " + hour + " at " + service + ". ";

        message += "\n\n We are waiting for you! :)\n";

        return message;
    }

    public String composeMultipleAppointmentsMessage(List<Appointment> appointments) {
        String message = "Hello " + appointments.get(0).getCustomer().getFirstName() + "!\n\n";
        message += "Tomorrow you have the following appointments:\n";

        for(Appointment appointment : appointments) {
            message += "- " + appointment.getSalonService().getName() + " at " + appointment.getHour().toString() + "\n";
        }

        message += "\n\n We are waiting for you! :)\n";

        return message;
    }
}