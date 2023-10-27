package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QueryChoiceRecipeListVo;

import java.util.Optional;
import java.util.UUID;

public interface CustomChoiceRecipeRepository {

    Optional<QueryChoiceRecipeListVo> queryChoiceRecipeList(UUID userId);
}
