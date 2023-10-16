package com.meturial.domain.recipe.presentation;

import com.meturial.domain.recipe.service.ChoiceRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
