package com.etpgpb.task.controller;

import com.etpgpb.task.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("service/{serviceId}/order/add")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String addOrder(@PathVariable("serviceId") Long serviceId, Principal principal){
        orderService.addOrder(serviceId, principal.getName());
        return "redirect:/services";
    }

    @RequestMapping(value = "/orders/process", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_EXECUTOR')")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId, @RequestParam("action") String action){
        orderService.updateOrder(orderId, action);
        return "redirect:/services";
    }
}
