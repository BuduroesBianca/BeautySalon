package com.example.beautysalon.controller;

import com.example.beautysalon.exception.*;
import com.example.beautysalon.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(SalonServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String salonServiceNotFoundHandler(SalonServiceNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String usernameAlreadyExistsHandler(UsernameAlreadyExistsException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String roleNotFoundHandler(RoleNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AppointmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String appointmentNotFoundHandler(AppointmentNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String customerNotFoundHandler(CustomerNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(OrderNotFoundException e) {
        return e.getMessage();
    }
}
