package com.meturial.domain.menu.domain.repository;

import com.meturial.domain.menu.domain.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MenuRepository extends CrudRepository<Menu, UUID>, CustomMenuRepository {
}
