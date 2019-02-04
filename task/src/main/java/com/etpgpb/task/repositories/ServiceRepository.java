package com.etpgpb.task.repositories;

import com.etpgpb.task.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Long> {

}
