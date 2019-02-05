package com.etpgpb.task.controller;

import com.etpgpb.task.model.Order;
import com.etpgpb.task.model.OrderStatus;
import com.etpgpb.task.model.Service;
import com.etpgpb.task.model.User;
import com.etpgpb.task.repositories.OrderRepository;
import com.etpgpb.task.repositories.ServiceRepository;
import com.etpgpb.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/services")
    public String getServices(Principal principal, Model model) {
        List<Service> services = serviceRepository.findAll();
        User user = userRepository.findByLogin(principal.getName());
        List<Order> orders;
        if (user.getRole().getName().toString().equals("ROLE_EXECUTOR")) {
            orders = orderRepository.findByStatus(OrderStatus.NEW);
        } else {
            orders = orderRepository.findByUser(user);
        }
        model.addAttribute("services", services);
        model.addAttribute("orders", orders);
        model.addAttribute("service", new Service());
        return "homePage";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/service")
    //@PreAuthorize("hasRole('ROLE_EXECUTOR')")
    public String createService(@ModelAttribute("service") Service service, BindingResult result, ModelMap model) {
        serviceRepository.save(service);
        return "redirect:/services";
    }
}
