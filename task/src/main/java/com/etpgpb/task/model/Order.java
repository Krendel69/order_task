package com.etpgpb.task.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order extends AbstractPersistable<Long> {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "Service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    public Order() {
    }

    public Order(Service service, User user) {
        this.status = OrderStatus.NEW;
        this.service = service;
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
