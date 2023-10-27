package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.RecipeSequence;

import java.util.List;
import java.util.UUID;

public interface CustomRecipeSequenceRepository {

    public List<RecipeSequence> queryRecipeSequenceListByRecipeId(UUID recipeId);
}
