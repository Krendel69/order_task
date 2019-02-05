package com.etpgpb.task.repositories;

import com.etpgpb.task.model.Order;
import com.etpgpb.task.model.OrderStatus;
import com.etpgpb.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByStatus(OrderStatus orderStatus);
}
