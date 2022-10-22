package com.example.beautysalon.controller;

import com.example.beautysalon.exception.AccountNotFoundException;
import com.example.beautysalon.exception.CustomerNotFoundException;
import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.Orders;
import com.example.beautysalon.model.SalonService;
import com.example.beautysalon.repository.CustomerRepository;
import com.example.beautysalon.service.OrderService;
import com.example.beautysalon.service.SalonServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class OrderController {

    @Autowired
    SalonServiceService salonServiceService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderService orderService;

    @GetMapping("/shopping")
    public ModelAndView placeOrder(@RequestParam("products") Long[] cartProducts) {
        ModelAndView modelAndView = new ModelAndView("payment_terminal");

        Map<Long, Long> products = Stream.of(cartProducts)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        System.out.println(products);

        Customer currentCustomer = getCurrentSessionCustomer();
        Double totalPrice = 0D;

        for(Map.Entry<Long, Long> entry : products.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            Orders pendingOrder = new Orders(entry.getKey(), "pending", currentCustomer, entry.getValue());
            SalonService salonService = salonServiceService.getSalonServiceById(entry.getKey());
            totalPrice += salonService.getPrice() * entry.getValue();
            orderService.createOrder(pendingOrder);
        }

        modelAndView.addObject("totalPrice", totalPrice);

        return modelAndView;
    }

    private Customer getCurrentSessionCustomer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        else {
            username = principal.toString();
        }

        Customer currentCustomer = customerRepository.findCustomerByEmail(username)
                .orElseThrow(() -> new CustomerNotFoundException(username));

        return currentCustomer;
    }

    public static <Integer> Map<Integer, Long>  frequencyMap(Stream<Integer> elements) {
        return elements.collect(
                Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new,
                        Collectors.counting()
                )
        );
    }
}
