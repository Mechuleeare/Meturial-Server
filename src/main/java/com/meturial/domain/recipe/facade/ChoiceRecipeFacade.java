package com.meturial.domain.recipe.facade;

import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.ChoiceRecipeRepository;
import com.meturial.domain.recipe.exception.ChoiceRecipeNotFoundException;
import com.meturial.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ChoiceRecipeFacade {

    private final ChoiceRecipeRepository choiceRecipeRepository;

    public boolean checkExistChoiceRecipe(User user, Recipe recipe) {
        return choiceRecipeRepository.existsByUserAndRecipe(user, recipe);
    }

    public ChoiceRecipe findByIdAndUserId(UUID choiceRecipeId, UUID userId) {
        return choiceRecipeRepository.findByIdAndUserId(choiceRecipeId, userId);
    }
}
