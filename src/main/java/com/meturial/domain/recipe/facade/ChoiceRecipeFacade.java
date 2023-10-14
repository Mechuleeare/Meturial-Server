package com.meturial.domain.recipe.facade;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.ChoiceRecipeRepository;
import com.meturial.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChoiceRecipeFacade {

    private final ChoiceRecipeRepository choiceRecipeRepository;

    public boolean checkChoiceRecipe(User user, Recipe recipe) {
        return choiceRecipeRepository.existsByUserAndRecipe(user, recipe);
    }
}
