package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipeRepository extends CrudRepository<User, UUID> {
}
