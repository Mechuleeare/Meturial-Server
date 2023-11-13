package com.meturial.domain.menu.domain.repository;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.type.MenuType;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface MenuRepository extends CrudRepository<Menu, UUID>, CustomMenuRepository {
    boolean existsByDateAndMenuType(LocalDate date, MenuType menuType);
}
