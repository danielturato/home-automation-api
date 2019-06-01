package com.danielturato.homeautomationapi.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Room> S save(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Room> Iterable<S> saveAll(Iterable<S> entities);

    @RestResource(rel = "name-contains", path = "containsName")
    Page<Room> findByNameContaining(@Param("name") String name, Pageable page);
}
