package com.example.beautysalon.controller;

import com.example.beautysalon.configuration.AppUserDetails;
import com.example.beautysalon.model.User;
import com.example.beautysalon.repository.UserRepository;
import com.example.beautysalon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public ModelAndView testController() {
        ModelAndView model = new ModelAndView("test");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        else {
            username = principal.toString();
        }

        Optional<User> currentUser = userRepository.findUserByEmail(username);

        model.addObject("welcomeMessage", "Hello " + currentUser.get().getRole().getName());
        return model;
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

}
