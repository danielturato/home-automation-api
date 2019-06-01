package com.danielturato.homeautomationapi.user;

import com.danielturato.homeautomationapi.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {
    private String[] roles;
    private String password;

    protected User() {
        super();
    }

    
}
