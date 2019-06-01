package com.danielturato.homeautomationapi.device;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {
}
