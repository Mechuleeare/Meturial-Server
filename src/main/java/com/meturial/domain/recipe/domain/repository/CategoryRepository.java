package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
}
