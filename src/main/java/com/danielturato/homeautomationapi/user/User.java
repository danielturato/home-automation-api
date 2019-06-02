package com.danielturato.homeautomationapi.user;

import com.danielturato.homeautomationapi.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class User extends BaseEntity {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @JsonIgnore
    private String[] roles;
    @JsonIgnore
    private String password;

    protected User() {
        super();
    }

    public User(String name, String password, String[] roles) {
        this();
        this.name = name;
        this.roles = roles;
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Arrays.equals(roles, user.roles) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(password);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }
}
