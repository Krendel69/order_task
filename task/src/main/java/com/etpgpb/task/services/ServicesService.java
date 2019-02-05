package com.etpgpb.task.services;

import com.etpgpb.task.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesService {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void addService(com.etpgpb.task.model.Service service){
        serviceRepository.save(service);
    }

    public void removeService(Long serviceId) {
        Optional<com.etpgpb.task.model.Service> service = serviceRepository.findById(serviceId);
        service.ifPresent(service1 -> {
            service1.setDeleted(true);
            serviceRepository.save(service1);
        });
    }
}
