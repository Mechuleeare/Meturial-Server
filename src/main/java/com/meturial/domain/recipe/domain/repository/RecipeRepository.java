package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID>, CustomRecipeRepository {

    List<Recipe> findAll();
}
