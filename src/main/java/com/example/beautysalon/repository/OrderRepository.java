package com.example.beautysalon.repository;

import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    public List<Orders> findOrdersByCustomerAndState(Customer customer, String state);
}
