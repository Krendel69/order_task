package com.etpgpb.task.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role extends AbstractPersistable<Long> {
    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
