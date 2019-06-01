package com.danielturato.homeautomationapi.control;

import com.danielturato.homeautomationapi.core.BaseEntity;
import com.danielturato.homeautomationapi.device.Device;
import com.danielturato.homeautomationapi.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Control extends BaseEntity {
    @ManyToOne
    private Device device;
    private int value;
    @OneToOne
    private User lastModifiedBy;

    protected Control() {
        super();
    }

    public Control(String name, Device device, int value, User lastModifiedBy) {
        this.name = name;
        this.device = device;
        this.value = value;
        this.lastModifiedBy = lastModifiedBy;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
