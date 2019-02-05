package com.etpgpb.task.controller;

import com.etpgpb.task.model.*;
import com.etpgpb.task.services.MainPageService;
import com.etpgpb.task.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class ServiceController {

    private ServicesService servicesService;

    private MainPageService mainPageService;

    @Autowired
    public ServiceController(ServicesService servicesService, MainPageService mainPageService) {
        this.servicesService = servicesService;
        this.mainPageService = mainPageService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/services")
    public String getServices(Principal principal, Model model) {
        model.addAttribute("mainPageDto", mainPageService.processToDto(principal.getName()));
        model.addAttribute("service", new Service());
        return "homePage";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/service")
    public String createService(@ModelAttribute("service") Service service) {
        servicesService.addService(service);
        return "redirect:/services";
    }

    @RequestMapping(value = "/service/{serviceId}/remove", method = RequestMethod.GET)
    public String removeService(@PathVariable("serviceId") Long serviceId) {
        servicesService.removeService(serviceId);
        return "redirect:/services";
    }
}
