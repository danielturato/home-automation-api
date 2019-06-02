package com.danielturato.homeautomationapi.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @NotNull
    protected String name;

    protected BaseEntity() {
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
