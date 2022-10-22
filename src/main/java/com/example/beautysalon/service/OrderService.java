package com.example.beautysalon.service;

import com.example.beautysalon.model.Orders;

public interface OrderService {

    public void createOrder(Orders order);
    public void updateOrder(Long id, Orders order);
}
