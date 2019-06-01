package com.danielturato.homeautomationapi.control;

import com.danielturato.homeautomationapi.user.User;
import com.danielturato.homeautomationapi.user.UserRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Control.class)
public class ControlEventHandler {

    private final UserRepository users;

    public ControlEventHandler(UserRepository users) {
        this.users = users;
    }

    @HandleBeforeSave
    public void addLastModifiedBasedOnLoggedInUser(Control control) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        control.setLastModifiedBy(user);
    }
}
