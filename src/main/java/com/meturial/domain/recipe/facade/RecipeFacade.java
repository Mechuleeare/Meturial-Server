package com.meturial.domain.recipe.facade;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.RecipeRepository;
import com.meturial.domain.recipe.exception.RecipeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RecipeFacade {

    private final RecipeRepository recipeRepository;

    public Recipe findByRecipeId(UUID recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> RecipeNotFoundException.EXCEPTION);
    }
}
