package com.warframepda.www.controllers;


import com.warframepda.www.models.Order;
import com.warframepda.www.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllOrders() {
        List<Order> myOrders = orderServices.findAllOrders();
        return new ResponseEntity<>(myOrders, HttpStatus.OK);
    }


    @GetMapping(value = "/{orderid}", produces = "application/json")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderid) {
        Order o = orderServices.findOrderById(orderid);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
