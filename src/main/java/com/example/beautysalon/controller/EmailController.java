package com.example.beautysalon.controller;

import com.example.beautysalon.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
