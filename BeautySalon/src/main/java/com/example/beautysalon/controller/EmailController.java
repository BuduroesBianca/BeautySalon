package com.example.beautysalon.controller;

import com.example.beautysalon.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

//    @GetMapping("/send-email")
//    @ResponseStatus(HttpStatus.OK)
//    public void sendEmail() {
//        emailService.sendSimpleMessage("bianca.buduroes@tremend.com", "Hello", "Hello Bianca");
//    }
}
