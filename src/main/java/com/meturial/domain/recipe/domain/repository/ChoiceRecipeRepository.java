package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.ChoiceRecipe;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChoiceRecipeRepository extends CrudRepository<ChoiceRecipe, UUID> {
}
