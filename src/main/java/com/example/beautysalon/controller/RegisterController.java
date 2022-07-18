package com.example.beautysalon.controller;

import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.User;
import com.example.beautysalon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.http.HttpClient;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register-customer")
    public ResponseEntity<String> processRegisterCustomer(@RequestBody Customer customer) {
        userService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register-employee")
    public ResponseEntity<String> processRegisterEmployee(@RequestBody Employee employee) {
        userService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
