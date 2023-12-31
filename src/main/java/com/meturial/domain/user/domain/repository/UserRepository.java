package com.meturial.domain.user.domain.repository;

import com.meturial.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID>, CustomUserRepository {

    Optional<User> findByEmail(String email);
}
