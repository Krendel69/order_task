package com.etpgpb.task.repositories;

import com.etpgpb.task.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("select s from Service s where s.isDeleted=false")
    List<Service> findActiveServices();

}
