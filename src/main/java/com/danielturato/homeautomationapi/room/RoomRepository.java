package com.danielturato.homeautomationapi.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @RestResource(rel = "name-contains", path = "containsName")
    Page<Room> findByNameContaining(@Param("name") String name, Pageable page);

    @RestResource(rel = "area-less-than", path = "areaIsBelow")
    Page<Room> findByAreaLessThan(@Param("area") int area, Pageable page);
}
