package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QueryChoiceRecipeListVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomChoiceRecipeRepository {

    Optional<List<QueryChoiceRecipeListVo>> queryChoiceRecipeList(UUID userId);
}
