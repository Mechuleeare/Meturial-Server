package com.meturial.domain.recipe.presentation.dto.response;

import com.meturial.domain.recipe.domain.repository.vo.QueryChoiceRecipeListVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryChoiceRecipeListResponse {
    private final Integer choiceRecipeCount;
    private final List<QueryChoiceRecipeListVo> choiceRecipeList;
}
