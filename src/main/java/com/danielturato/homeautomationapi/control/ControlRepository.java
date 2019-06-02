package com.danielturato.homeautomationapi.control;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;


public interface ControlRepository extends PagingAndSortingRepository<Control, Long> {

    @PreAuthorize("#control.device?.room?.isAnAdmin(authentication.name)")
    <S extends Control> S save(@Param("control") S entity);
}
