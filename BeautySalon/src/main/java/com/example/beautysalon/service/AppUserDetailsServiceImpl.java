package com.example.beautysalon.service;

import com.example.beautysalon.configuration.AppUserDetails;
import com.example.beautysalon.model.User;
import com.example.beautysalon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AppUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);
        if(user.isPresent()) {
            return new AppUserDetails(user.get());
        }

        throw new UsernameNotFoundException("This email doesn't exist in database");
    }
}
