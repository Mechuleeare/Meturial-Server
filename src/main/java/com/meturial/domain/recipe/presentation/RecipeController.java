package com.meturial.domain.recipe.presentation;

import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeDetailResponse;
import com.meturial.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{recipe-id}")
    public QueryRecipeDetailResponse queryRecipeDetailByRecipeId(@PathVariable("recipe-id") UUID recipeId) {
        return recipeService.queryRecipeDetailByRecipeId(recipeId);
    }
}
