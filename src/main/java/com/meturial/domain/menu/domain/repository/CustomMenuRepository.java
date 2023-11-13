package com.meturial.domain.menu.domain.repository;

import com.meturial.domain.menu.domain.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CustomMenuRepository {
    List<Menu> findAllByDateAndUserId(LocalDate date, UUID userId);
}