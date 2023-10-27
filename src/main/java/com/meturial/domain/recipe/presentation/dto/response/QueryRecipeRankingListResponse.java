package com.meturial.domain.recipe.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryRecipeRankingListResponse {
    private final List<RecipeRankingElement> recipeRankingList;
}
