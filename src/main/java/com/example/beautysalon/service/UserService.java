package com.example.beautysalon.service;

import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public void saveCustomer(Customer customer);

    public void saveEmployee(Employee employee);
//    public ResponseEntity<User> updateUserInfo(User user);
}
