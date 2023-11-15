package com.meturial.domain.recipe.presentation.dto.response;

import com.meturial.domain.recipe.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryElement {
    private final String categoryName;
    private final String categoryImageUrl;

    public static CategoryElement of(Category category) {
        return CategoryElement.builder()
                .categoryName(category.getName())
                .categoryImageUrl(category.getCategoryImageUrl())
                .build();
    }
}
