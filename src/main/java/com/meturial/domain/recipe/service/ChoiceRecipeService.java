package com.meturial.domain.recipe.service;

import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.ChoiceRecipeRepository;
import com.meturial.domain.recipe.exception.ChoiceRecipeExistException;
import com.meturial.domain.recipe.facade.ChoiceRecipeFacade;
import com.meturial.domain.recipe.facade.RecipeFacade;
import com.meturial.domain.user.domain.User;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChoiceRecipeService {

    private final ChoiceRecipeRepository choiceRecipeRepository;
    private final SecurityFacade securityFacade;
    private final RecipeFacade recipeFacade;
    private final ChoiceRecipeFacade choiceRecipeFacade;

    public void addChoice(UUID recipeId) {
        User user = securityFacade.getCurrentUser();
        Recipe recipe = recipeFacade.findRecipe(recipeId);

        if (choiceRecipeFacade.findExistedChoiceRecipe(user, recipe)) {
            throw ChoiceRecipeExistException.EXCEPTION;
        }

        choiceRecipeRepository.save(ChoiceRecipe.builder()
                .user(user)
                .recipe(recipe)
                .build());
    }
}
