package com.warframepda.www.services;

import com.warframepda.www.models.Order;
import com.warframepda.www.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrderRepository orderrepos;

    @Override
    public List<Order> findAllOrders() {
        List<Order> list = new ArrayList<>();

        orderrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Order findOrderById(long id) throws EntityNotFoundException {

        return orderrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order With Id " + id + " Not Found"));
    }
}
