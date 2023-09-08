package com.meturial.domain.notification.domain.repository;

import com.meturial.domain.notification.domain.MenuNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MenuNotificationRepository extends CrudRepository<MenuNotification, UUID> {
}
