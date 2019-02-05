package com.etpgpb.task.services;

import com.etpgpb.task.dto.MainPageDto;
import com.etpgpb.task.model.Order;
import com.etpgpb.task.model.OrderStatus;
import com.etpgpb.task.model.RoleName;
import com.etpgpb.task.model.User;
import com.etpgpb.task.repositories.OrderRepository;
import com.etpgpb.task.repositories.ServiceRepository;
import com.etpgpb.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPageService {

    private ServiceRepository serviceRepository;

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    @Autowired
    public MainPageService(ServiceRepository serviceRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public MainPageDto processToDto(String userName){
        List<com.etpgpb.task.model.Service> services = serviceRepository.findActiveServices();
        User user = userRepository.findByLogin(userName);
        List<Order> orders;
        if (user.getRole().getName().equals(RoleName.ROLE_EXECUTOR)) {
            orders = orderRepository.findByStatus(OrderStatus.NEW);
        } else {
            orders = orderRepository.findByUser(user);
        }
        return new MainPageDto(services, orders);
    }
}
