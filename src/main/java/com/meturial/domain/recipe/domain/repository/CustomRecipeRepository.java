package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeRankingVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomRecipeRepository {
    Optional<QueryRecipeDetailVo> queryRecipeDetailByRecipeId(UUID recipeId);

    List<QueryRecipeRankingVo> queryRecipeRankingListOrderByStarRating();

    List<QueryRecipeRankingVo> queryRecipeRankingListOrderByStarCount();
}
