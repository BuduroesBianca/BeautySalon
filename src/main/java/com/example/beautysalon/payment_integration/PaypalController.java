package com.example.beautysalon.payment_integration;

import com.example.beautysalon.exception.AccountNotFoundException;
import com.example.beautysalon.exception.CustomerNotFoundException;
import com.example.beautysalon.model.Customer;
import com.example.beautysalon.model.Orders;
import com.example.beautysalon.model.User;
import com.example.beautysalon.repository.CustomerRepository;
import com.example.beautysalon.repository.OrderRepository;
import com.example.beautysalon.service.EmailServiceImpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PaypalController {

    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("/pre-pay")
    public String prePay() {
        return "payment_terminal";
    }


    @PostMapping("/pay")
    public String payment() {
        try {
            Payment payment = service.createPayment(100D, "EUR", "PAYPAL",
                    "http://localhost:8081/" + CANCEL_URL,
                    "http://localhost:8081/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/pay/cancel")
    public String cancelPay() {
        changeOrdersStatusForSessionCustomer("cancelled");
        Customer customer = getCurrentSessionCustomer();
        emailService.sendCustomEmail(customer.getEmail(), ":(", "Payment cancelled - Beauty Salon");
        return "cancel";
    }

    @GetMapping("/pay/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());

            Customer customer = getCurrentSessionCustomer();
            emailService.sendPaymentInfo(customer.getEmail(), emailService.composePaymentMessage(payment, customer.getFirstName()));

            if (payment.getState().equals("approved")) {
                changeOrdersStatusForSessionCustomer("completed");
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
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

    private void changeOrdersStatusForSessionCustomer(String state) {
       Customer currentCustomer = getCurrentSessionCustomer();

        List<Orders> pendingOrders = orderRepository.findOrdersByCustomerAndState(currentCustomer, "pending");

        for(Orders o : pendingOrders) {
            o.setState(state);
            orderRepository.save(o);
        }
    }

}
