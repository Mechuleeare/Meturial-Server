package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.Category;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeReviewVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomRecipeRepository {
    List<Category> queryCategory();

    Optional<QueryRecipeDetailVo> queryRecipeDetailByRecipeId(UUID recipeId);

    List<QueryRecipeReviewVo> queryRecipeReviewList();
}
