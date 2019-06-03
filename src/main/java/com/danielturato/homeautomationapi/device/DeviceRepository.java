package com.danielturato.homeautomationapi.device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

    @RestResource(rel = "name-contains", path = "containsName")
    Page<Device> findByNameContaining(@Param("name") String name, Pageable page);

    @PreAuthorize("#device?.room?.isAnAdmin(authentication.name)")
    <S extends Device> S save(@Param("device") S entity);

}
