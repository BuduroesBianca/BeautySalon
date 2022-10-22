package com.example.beautysalon.repository;

import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findCustomerByEmail(String email);

}
