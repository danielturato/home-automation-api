package com.danielturato.homeautomationapi.device;

import com.danielturato.homeautomationapi.control.Control;
import com.danielturato.homeautomationapi.core.BaseEntity;
import com.danielturato.homeautomationapi.room.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Device extends BaseEntity {
    @ManyToOne
    private Room room;
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Control> controls;

    protected Device() {
        super();
        controls = new ArrayList<>();
    }

    public Device(String name, Room room) {
        this();
        this.name = name;
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Control> getControls() {
        return controls;
    }

    public void addControl(Control control) {
        controls.add(control);
    }
}
