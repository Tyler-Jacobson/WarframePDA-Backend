package com.warframepda.www.services;

import com.warframepda.www.models.Order;

import java.util.List;

public interface OrderServices {

    List<Order> findAllOrders();

    Order findOrderById(long id);
}
