package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ChoiceRecipeRepository extends CrudRepository<ChoiceRecipe, UUID>, CustomChoiceRecipeRepository {

    boolean existsByUserAndRecipe(User user, Recipe recipe);

    List<ChoiceRecipe> findAllByUserId(UUID userId);
}
