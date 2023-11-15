package com.meturial.domain.user.domain.repository;

import java.util.List;
import java.util.UUID;

public interface CustomUserRepository {

    List<String> findAllDeviceTokenInUserIdList(List<UUID> userIdList);
}
