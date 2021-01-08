package com.warframepda.www.repositories;

import com.warframepda.www.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
