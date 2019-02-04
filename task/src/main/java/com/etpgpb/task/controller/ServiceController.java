package com.etpgpb.task.controller;

import com.etpgpb.task.model.Service;
import com.etpgpb.task.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/services")
    //@PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String getServices(Model model) {
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
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
