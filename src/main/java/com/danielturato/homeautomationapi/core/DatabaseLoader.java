package com.danielturato.homeautomationapi.core;

import com.danielturato.homeautomationapi.control.Control;
import com.danielturato.homeautomationapi.control.ControlRepository;
import com.danielturato.homeautomationapi.device.Device;
import com.danielturato.homeautomationapi.device.DeviceRepository;
import com.danielturato.homeautomationapi.room.Room;
import com.danielturato.homeautomationapi.room.RoomRepository;
import com.danielturato.homeautomationapi.user.User;
import com.danielturato.homeautomationapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private final RoomRepository rooms;
    private final UserRepository users;
    private final DeviceRepository devices;
    private final ControlRepository controls;

    @Autowired
    public DatabaseLoader(RoomRepository rooms, UserRepository users, DeviceRepository devices, ControlRepository controls) {
        this.rooms = rooms;
        this.users = users;
        this.devices = devices;
        this.controls = controls;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("admin", "password", new String[]{"ROLE_ADMIN", "ROLE_USER"});
        List<User> newUsers = Arrays.asList(admin, new User("test", "password", new String[]{"ROLE_USER"}));

        users.saveAll(newUsers);
        Room room = new Room("kitchen", 50);
        room.addAdmin(admin);
        Device device = new Device("Microwave", room);
        room.addDevice(device);
        Control control = new Control("Power Button", device, 0, admin);
        rooms.save(room);
        devices.save(device);
        controls.save(control);
    }
}
