package com.etpgpb.task.services;

import com.etpgpb.task.model.Order;
import com.etpgpb.task.model.OrderStatus;
import com.etpgpb.task.model.Service;
import com.etpgpb.task.model.User;
import com.etpgpb.task.repositories.OrderRepository;
import com.etpgpb.task.repositories.ServiceRepository;
import com.etpgpb.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class OrderService {

    private OrderRepository orderRepository;

    private ServiceRepository serviceRepository;

    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ServiceRepository serviceRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public void addOrder(Long serviceId, String userName) {
        Optional<Service> service = serviceRepository.findById(serviceId);
        if (service.isPresent()) {
            User user = userRepository.findByLogin(userName);
            orderRepository.save(new Order(service.get(), user));
        }
    }

    public void updateOrder(Long orderId, String action) {
        Order order = orderRepository.getOne(orderId);
        if (action.equals("apply")){
            order.setStatus(OrderStatus.APPLIED);
        } else if (action.equals("decline")) {
            order.setStatus(OrderStatus.DECLINED);
        } else {
            throw new UnsupportedOperationException("Action not supported");
        }
        orderRepository.save(order);
    }
}
