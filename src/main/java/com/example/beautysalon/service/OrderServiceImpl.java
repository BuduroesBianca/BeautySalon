package com.example.beautysalon.service;

import com.example.beautysalon.exception.OrderNotFoundException;
import com.example.beautysalon.model.Orders;
import com.example.beautysalon.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(Orders order) {
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id, Orders newOrder) {
        Orders oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        oldOrder.setCustomer(newOrder.getCustomer() == null ? oldOrder.getCustomer() : newOrder.getCustomer());
        oldOrder.setServiceId(newOrder.getServiceId() == null ? oldOrder.getServiceId() : newOrder.getServiceId());
        oldOrder.setQuantity(newOrder.getQuantity() == null ? oldOrder.getQuantity() : newOrder.getQuantity());
        oldOrder.setState(newOrder.getState() == null ? oldOrder.getState() : newOrder.getState());

        orderRepository.save(oldOrder);
    }
}
