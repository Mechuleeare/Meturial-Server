package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;

import java.util.Optional;
import java.util.UUID;

public interface CustomRecipeRepository {
    Optional<QueryRecipeDetailVo> queryRecipeDetailByRecipeId(UUID recipeId);
}
