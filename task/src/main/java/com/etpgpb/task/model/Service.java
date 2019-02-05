package com.etpgpb.task.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Services")
public class Service extends AbstractPersistable<Long> {

    public Service() {
    }

    private String name;

    private boolean isDeleted;

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
