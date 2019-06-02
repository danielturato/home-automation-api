package com.danielturato.homeautomationapi.room;

import com.danielturato.homeautomationapi.core.BaseEntity;
import com.danielturato.homeautomationapi.device.Device;
import com.danielturato.homeautomationapi.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room extends BaseEntity {
    @Max(value = 1000, message = "The area can only go to {max} sq ft")
    @Min(value = 1, message = "The minimum area is {min}")
    @NotNull
    private int area;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Device> devices;
    @ManyToMany
    private List<User> administrators;

    protected Room() {
        super();
        devices = new ArrayList<>();
        administrators = new ArrayList<>();
    }

    public Room(String name, int area) {
        this();
        this.name = name;
        this.area = area;
    }


    public int getArea() {
        return area;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<User> getAdministrators() {
        return administrators;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void addAdmin(User user) {
        administrators.add(user);
    }

    public boolean isAnAdmin(String name) {
        for (User user: administrators) {
            if(user.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

}
