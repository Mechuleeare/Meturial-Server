package com.meturial.domain.recipe.service;

import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.ChoiceRecipeRepository;
import com.meturial.domain.recipe.domain.repository.vo.QueryChoiceRecipeListVo;
import com.meturial.domain.recipe.exception.ChoiceRecipeExistException;
import com.meturial.domain.recipe.exception.ChoiceRecipeNotFoundException;
import com.meturial.domain.recipe.facade.ChoiceRecipeFacade;
import com.meturial.domain.recipe.facade.RecipeFacade;
import com.meturial.domain.recipe.presentation.dto.response.QueryChoiceRecipeListResponse;
import com.meturial.domain.user.domain.User;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Recipe recipe = recipeFacade.findByRecipeId(recipeId);

        if (choiceRecipeFacade.checkExistChoiceRecipe(user, recipe)) {
            throw ChoiceRecipeExistException.EXCEPTION;
        }

        choiceRecipeRepository.save(ChoiceRecipe.builder()
                .user(user)
                .recipe(recipe)
                .build());
    }

    @Transactional
    public void deleteChoice(UUID choiceRecipeId) {
        ChoiceRecipe choiceRecipe = choiceRecipeRepository.findById(choiceRecipeId)
                .orElseThrow(() -> ChoiceRecipeNotFoundException.EXCEPTION);

        choiceRecipe.checkChoiceRecipeIsMine(securityFacade.getCurrentUserId());

        choiceRecipeRepository.delete(choiceRecipe);
    }

    @Transactional(readOnly = true)
    public QueryChoiceRecipeListResponse queryChoiceRecipeList() {
        UUID userId = securityFacade.getCurrentUserId();
        List<QueryChoiceRecipeListVo> choiceRecipeList = choiceRecipeRepository.queryChoiceRecipeList(userId);

        return new QueryChoiceRecipeListResponse(choiceRecipeList);
    }
}
