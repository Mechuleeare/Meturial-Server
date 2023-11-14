package com.meturial.domain.recipe.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryElement {
    private final String categoryName;
    private final String categoryImageUrl;
}
