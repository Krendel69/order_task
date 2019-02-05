package com.etpgpb.task.dto;

import com.etpgpb.task.model.Order;
import com.etpgpb.task.model.Service;

import java.util.List;

public class MainPageDto {
    private List<Service> services;
    private List<Order> orders;

    public MainPageDto(List<Service> services, List<Order> orders) {
        this.services = services;
        this.orders = orders;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
