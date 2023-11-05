package com.meturial.domain.recipe.presentation;

import com.meturial.domain.recipe.presentation.dto.response.QueryChoiceRecipeListResponse;
import com.meturial.domain.recipe.service.ChoiceRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/choice")
@RestController
public class ChoiceRecipeController {

    private final ChoiceRecipeService choiceRecipeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recipe-id}")
    public void addChoice(@PathVariable("recipe-id") UUID recipeId) {
        choiceRecipeService.addChoice(recipeId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{choice-recipe-id}")
    public void deleteChoice(@PathVariable("choice-recipe-id") UUID choiceRecipeId) {
        choiceRecipeService.deleteChoice(choiceRecipeId);
    }

    @GetMapping
    public QueryChoiceRecipeListResponse queryChoiceRecipeList() {
        return choiceRecipeService.queryChoiceRecipeList();
    }
}
