package com.meturial.domain.recipe.presentation;

import com.meturial.domain.recipe.presentation.dto.response.QueryCategoryResponse;
import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeDetailResponse;
import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeRankingListResponse;
import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeStarRatingCountResponse;
import com.meturial.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/category")
    public QueryCategoryResponse queryCategory() {
        return recipeService.queryCategory();
    }

    @GetMapping
    public QueryRecipeStarRatingCountResponse queryStarRatingCountByRecipeName(@RequestParam("name") String name) {
        return recipeService.queryStarRatingCountByRecipeName(name);
    }

    @GetMapping("/{recipe-id}")
    public QueryRecipeDetailResponse queryRecipeDetailByRecipeId(@PathVariable("recipe-id") UUID recipeId) {
        return recipeService.queryRecipeDetailByRecipeId(recipeId);
    }

    @GetMapping("/ranking")
    public QueryRecipeRankingListResponse queryRecipeRankingList(@RequestParam("type") String type) {
        return recipeService.queryRecipeRankingList(type);
    }
}
